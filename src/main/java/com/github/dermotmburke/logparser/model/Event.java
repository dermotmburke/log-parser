package com.github.dermotmburke.logparser.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event {

    @Id
    private String id;
    private LogType type;
    private String host;
    private long duration;
    private boolean alert;

    public Event(){}

    public Event(Log startLog, Log endLog) {
        this.setId(startLog.getEventId());
        this.setType(startLog.getType() != null ? startLog.getType() : endLog.getType());
        this.setDuration(endLog.getTimestamp() - startLog.getTimestamp());
        this.setHost(startLog.getHost());
        this.setAlert(this.getDuration() > 4);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", host='" + host + '\'' +
                ", duration=" + duration +
                ", alert=" + alert +
                '}';
    }
}

