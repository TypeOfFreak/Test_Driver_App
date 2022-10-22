package com.example.my_application.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Task {
    private String description;
    private String [] path;
    private Date start_Time, end_Time;
    public Task (String [] Task_info) {
        this.description = Task_info[3];
        this.path = Task_info[5].split("-");

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy HH:mm:ss");
        try {
            this.start_Time = format.parse(Task_info[4] + " " + Task_info[1]);
            this.end_Time = format.parse(Task_info[4] + " " + Task_info[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
