package com.github.dermotmburke.logparser.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    @DisplayName("Should generate correct event details")
    public void shouldGenerateCorrectEventDetails(){
        Log start = new Log();
        start.setEventId("scsmbstgra");
        start.setHost("12345");
        start.setTimestamp(1491377495212L);
        start.setType(LogType.APPLICATION_LOG);
        start.setState(LogEventState.STARTED);

        Log end = new Log();
        end.setEventId("scsmbstgra");
        end.setHost("12345");
        end.setTimestamp(1491377495217L);
        end.setType(LogType.APPLICATION_LOG);
        end.setState(LogEventState.STARTED);

        Event event = new Event(start, end);

        assertThat(event.getId()).isEqualTo(start.getEventId());
        assertThat(event.getHost()).isEqualTo(start.getHost());
        assertThat(event.getDuration()).isEqualTo(end.getTimestamp() - start.getTimestamp());
        assertThat(event.isAlert()).isEqualTo(true);
        assertThat(event.getType()).isEqualTo(LogType.APPLICATION_LOG);
    }

}