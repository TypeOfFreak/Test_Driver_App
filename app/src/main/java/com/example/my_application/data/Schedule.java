package com.example.my_application.data;

import com.example.my_application.data.model.LoggedInUser;

import java.io.FileInputStream;
import java.io.*;

public class Schedule {
    private LoggedInUser user;
    public Schedule (LoggedInUser user) {
        this.user = user;
    }
}
