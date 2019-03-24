package com.example.projecttemp.Model;

public class Happenings {

    public String event, date, description;

    Happenings() {

    }

    public Happenings(String event, String date, String description) {

        this.event = event;
        this.date = date;
        this.description = description;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return event + date + description + "----";
    }
}
