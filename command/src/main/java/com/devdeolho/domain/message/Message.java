package com.devdeolho.domain.message;

import com.devdeolho.domain.enums.Event;

public class Message<T> {

    private Event event;
    private T payload;


    public Message() {
    }

    public Message(Event event, T payload) {
        this.event = event;
        this.payload = payload;
    }

    public Event getEvent() {
        return event;
    }

    public T getPayload() {
        return payload;
    }


}
