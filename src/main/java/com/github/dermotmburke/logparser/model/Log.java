package com.github.dermotmburke.logparser.model;

import com.github.dermotmburke.logparser.model.LogEventState;
import com.github.dermotmburke.logparser.model.LogType;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Log {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long key;
    @SerializedName("id")
    private String eventId;
    private LogEventState state;
    private LogType type;
    private String host;
    private String cacheId;
    private long timestamp;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public LogEventState getState() {
        return state;
    }

    public void setState(LogEventState state) {
        this.state = state;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCacheId() {
        return cacheId;
    }

    public void setCacheId(String cacheId) {
        this.cacheId = cacheId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Log{" +
                "key=" + key +
                ", eventId='" + eventId + '\'' +
                ", state=" + state +
                ", type=" + type +
                ", host='" + host + '\'' +
                ", cacheId='" + cacheId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

