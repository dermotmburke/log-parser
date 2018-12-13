package com.github.dermotmburke.logparser;

import com.github.dermotmburke.logparser.model.Event;
import com.github.dermotmburke.logparser.model.Log;
import com.github.dermotmburke.logparser.repository.EventRepository;
import com.github.dermotmburke.logparser.repository.LogRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private EventRepository eventRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		if(args.length == 0){
			LOGGER.error("Log file path not provided");
			return;
		}
		String cacheId = generateCacheId();
		LOGGER.info("Parsing log file {} ", args[0]);
		JsonReader reader = new JsonReader(new FileReader(args[0]));
		Gson gson = new GsonBuilder().create();
		reader.beginArray();
		while (reader.hasNext()) {
			Log logEvent = gson.fromJson(reader, Log.class);
			logEvent.setCacheId(cacheId);
			LOGGER.debug("Read log {} ", logEvent);
			logRepository.save(logEvent);
			generateEvent(logEvent);
		}
		LOGGER.info("Log parsing complete");
		reader.close();
		LOGGER.info("Clearing cache");
		logRepository.deleteAllByCacheId(cacheId);
	}

	private void generateEvent(Log logEvent) {
		List<Log> allByEventId = logRepository.findAllByEventIdOrderByTimestamp(logEvent.getEventId());
		if(allByEventId.size() == 2){
			Event event = new Event(allByEventId.get(0), allByEventId.get(1));
			LOGGER.debug("Event created {} ", event);
			eventRepository.save(event);
		}
	}

	private String generateCacheId() {
		return UUID.randomUUID().toString();
	}
}
