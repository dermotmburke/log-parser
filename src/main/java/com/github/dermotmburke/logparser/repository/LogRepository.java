package com.github.dermotmburke.logparser.repository;

import com.github.dermotmburke.logparser.model.Log;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogRepository extends CrudRepository<Log, String> {
    List<Log> findAllByEventIdOrderByTimestamp(String eventId);

    void deleteAllByCacheId(String cacheId);
}
