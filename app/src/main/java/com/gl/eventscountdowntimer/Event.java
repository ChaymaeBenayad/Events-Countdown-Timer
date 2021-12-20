package com.gl.eventscountdowntimer;

import java.io.Serializable;

public class Event implements Serializable {

    private int eventId;
    private String eventTitle;
    private String eventDate;

    public Event()  {

    }
    public Event(String eventTitle, String eventDate) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
    }

    public Event(int eventId, String eventTitle, String eventDate) {
        this.eventId= eventId;
        this.eventTitle= eventTitle;
        this.eventDate= eventDate;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }


    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }


    @Override
    public String toString()  {
        return this.eventTitle;
    }

}
