package com.example.my_application.data;

import com.example.my_application.data.model.LoggedInUser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

import android.content.Context;

public class Schedule {
    private LoggedInUser user;
    Context context;
    public Schedule (LoggedInUser user, Context context) {
        this.user = user;
        this.context = context;
    }
    public ArrayList<Task> Get_Tasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            FileInputStream fin =  context.openFileInput("drivers_chedule.csv");
            InputStreamReader reader = new InputStreamReader(fin);
            BufferedReader bR = new BufferedReader(reader);
            String line;
            while((line = bR.readLine()) != null) {
                String [] Task_info = line.split(",");
                if (Task_info[0].equals(user.getUserId())) {
                    tasks.add(new Task(Task_info));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
