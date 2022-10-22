package com.example.my_application.data;

import java.util.Calendar;

public class Task {
    private String description;
    private String [] path;
    private Calendar start_Time;
    private Calendar end_Time;
    public Task (Calendar start_Time, Calendar end_Time, String description, String[] path ) {
        this.start_Time = start_Time;
        this.end_Time = end_Time;
        this.path = path;
        this.description = description;
    }
}
