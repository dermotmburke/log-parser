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

package com.github.dermotmburke.logparser;

import com.github.dermotmburke.logparser.model.Event;
import com.github.dermotmburke.logparser.model.LogType;
import com.github.dermotmburke.logparser.repository.EventRepository;
import com.github.dermotmburke.logparser.repository.LogRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import static java.util.stream.StreamSupport.stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationTest {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private Application application;

    @Test
    @DisplayName("should insert the correct data from the log file")
    public void testApplicationInsertsCorrectDataFromLog() throws Exception {
        String path = ResourceUtils.getFile(this.getClass().getResource("/log.json")).getAbsolutePath();
        application.run(new String[]{path});
        Iterable<Event> results = eventRepository.findAll();
        assertThat(stream(results.spliterator(), false).count()).isEqualTo(3);

        assertThat(results).extracting("id", "type", "duration")
                .containsOnly(tuple("scsmbstgra", LogType.APPLICATION_LOG, 5L),
                        tuple("scsmbstgrc", null, 8L),
                        tuple("scsmbstgrb", null, 3L));
    }
}