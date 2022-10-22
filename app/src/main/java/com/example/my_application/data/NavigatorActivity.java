package com.example.my_application.data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.my_application.R;
import com.example.my_application.data.model.LoggedInUser;

public class NavigatorActivity extends AppCompatActivity {

    static private LoggedInUser Driver;
    TextView drText;

    public static void init_Driver(LoggedInUser driver)  {
        Driver = driver;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        Schedule sch = new Schedule(Driver, getApplicationContext());

        drText = findViewById(R.id.DrText);
        drText.setText(Driver.getUserId());
    }
}