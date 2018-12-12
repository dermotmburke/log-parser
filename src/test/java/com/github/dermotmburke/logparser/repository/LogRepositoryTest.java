/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.dermotmburke.logparser.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import com.github.dermotmburke.logparser.model.Log;
import com.github.dermotmburke.logparser.model.LogEventState;
import com.github.dermotmburke.logparser.model.LogType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LogRepositoryTest {

    @Autowired
    private LogRepository logRepository;

    @Test
    @DisplayName("should find all by event id and order by timestamp")
    public void testFindAllByEventIdOrderByTimestamp() {
        Log start = new Log();
        start.setEventId("scsmbstgra");
        start.setHost("12345");
        start.setTimestamp(1491377495212L);
        start.setType(LogType.APPLICATION_LOG);
        start.setState(LogEventState.STARTED);

        logRepository.save(start);

        Log end = new Log();
        end.setEventId("scsmbstgra");
        end.setHost("12345");
        end.setTimestamp(1491377495217L);
        end.setType(LogType.APPLICATION_LOG);
        end.setState(LogEventState.FINISHED);

        logRepository.save(end);

        List<Log> results = logRepository.findAllByEventIdOrderByTimestamp(start.getEventId());

        assertThat(results).extracting("eventId", "state", "timestamp")
                .containsExactly(tuple("scsmbstgra", LogEventState.STARTED, 1491377495212L),
                        tuple("scsmbstgra", LogEventState.FINISHED, 1491377495217L));
    }
}