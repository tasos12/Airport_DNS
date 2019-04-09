package com.Monk.Server;

public class Route {

    private String id, status, datetime;

    public Route(String id, String status, String datetime) {
        this.id = id;
        this.status = status;
        this.datetime = datetime;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        String text = id + " " + status + " " + datetime;
        return text;
    }
}
