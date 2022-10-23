package com.example.my_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.my_application.data.PathAdapter;
import com.example.my_application.data.Task;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.my_application.data.Schedule;
import com.example.my_application.data.model.LoggedInUser;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static private LoggedInUser Driver;
    private Task CurrentTask;
    public static void init_Driver(LoggedInUser driver)  {
        Driver = driver;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateTask();

        findViewById(R.id.buttonAcceptTask).setOnClickListener(v -> {
            v.setEnabled(false);
            showDrivingLayout(false);
        });

        findViewById(R.id.buttonFinish).setOnClickListener(v -> {
            v.setEnabled(false);
            updateTask();
        });
    }

    private void updateTask(){
        long now = new Date().getTime();
        CurrentTask = getCurrentTask(now);
        formLayoutFromTask(now);
    }

    private Task getCurrentTask(long now){

        Schedule bus_sqed = new Schedule(Driver, getApplicationContext());
        Object[] tasks = bus_sqed.Get_Tasks().toArray();

        int ind_cur_task = 0;
        for(int i = 0; i < tasks.length; i++){
            if(((Task)tasks[i]).GetEndTime() > now){
                ind_cur_task = i;
                break;
            }
        }
        return (Task)tasks[ind_cur_task];
    }

    private void showButton(@NonNull View v){
        v.setVisibility(View.VISIBLE);
        v.setEnabled(true);
    }

    final int T_SEC_IN_HOUR = 3600;
    final int T_SEC_IN_MIN = 60;
    private void showWaitingLayout(){

        findViewById(R.id.driveLayout).setVisibility(View.INVISIBLE);
        findViewById(R.id.boardLayout).setVisibility(View.INVISIBLE);
        findViewById(R.id.waitingLayout).setVisibility(View.VISIBLE);

        long t = CurrentTask.GetStartTime();
        int hours = (int)t/T_SEC_IN_HOUR;
        t %= T_SEC_IN_HOUR;
        int minutes = (int)t/T_SEC_IN_MIN;

        ((TextView)findViewById(R.id.indicatorTimeOfNext)).setText(getString(R.string.prompt_hh_mm, hours, minutes));
    }

    private void showDrivingLayout(boolean isLoaded){
        findViewById(R.id.boardLayout).setVisibility(View.INVISIBLE);
        findViewById(R.id.waitingLayout).setVisibility(View.INVISIBLE);
        findViewById(R.id.driveLayout).setVisibility(View.VISIBLE);

//        RecyclerView resView = findViewById(R.id.recycler_path);
//        resView.setAdapter(new PathAdapter(CurrentTask.GetPath()));

        ((TextView)findViewById(R.id.indicatorPlaceOfDeparture))
                .setText(getString(R.string.place, "Gate", CurrentTask.GetStartPoint()));
        ((TextView)findViewById(R.id.indicatorPlaceOfArrival))
                .setText(getString(R.string.place, "Gate", CurrentTask.GetEndPoint()));

        long time_s, time_f;
        time_s = isLoaded ? CurrentTask.GetBoardingEndTime() : CurrentTask.GetStartTime();
        time_f = isLoaded ? CurrentTask.GetArrivalTime() : CurrentTask.GetBoardingStartTime();

        int hours = (int)time_s/T_SEC_IN_HOUR;
        time_s %= T_SEC_IN_HOUR;
        int minutes = (int)time_s/T_SEC_IN_MIN;

        ((TextView) findViewById(R.id.indicatorTimeOfDeparture))
                .setText(getString(R.string.prompt_hh_mm, hours, minutes));

        hours = (int)time_f/T_SEC_IN_HOUR;
        time_f %= T_SEC_IN_HOUR;
        minutes = (int)time_f/T_SEC_IN_MIN;

        ((TextView) findViewById(R.id.indicatorTimeOfArrival))
                .setText(getString(R.string.prompt_hh_mm, hours, minutes));

    }

    private void showBoardingLayout(boolean isBoarding){
        findViewById(R.id.waitingLayout).setVisibility(View.INVISIBLE);
        findViewById(R.id.driveLayout).setVisibility(View.INVISIBLE);
        findViewById(R.id.boardLayout).setVisibility(View.VISIBLE);

        long time = isBoarding ? CurrentTask.GetBoardingEndTime() : CurrentTask.GetEndTime();

        int hours = (int)time/T_SEC_IN_HOUR;
        time %= T_SEC_IN_HOUR;
        int minutes = (int)time/T_SEC_IN_MIN;

        if(isBoarding) {
            ((TextView) findViewById(R.id.indicatorAction)).setText(getString(R.string.current_action, getString(R.string.boarding)));
            ((TextView) findViewById(R.id.indicatorPlaceOfArrival))
                    .setText(getString(R.string.place, "Gate", CurrentTask.GetStopPoint()));
            ((TextView)findViewById(R.id.indicatorTimeOfEnd)).setText(getString(R.string.prompt_hh_mm, hours, minutes));
        }else{
            ((TextView) findViewById(R.id.indicatorAction)).setText(getString(R.string.current_action, getString(R.string.unboarding)));
            ((TextView) findViewById(R.id.indicatorPlaceOfArrival))
                    .setText(getString(R.string.place, "Gate", CurrentTask.GetEndPoint()));
            ((TextView)findViewById(R.id.indicatorTimeOfEnd)).setText(getString(R.string.prompt_hh_mm, hours, minutes));
        }
    }

    private void formLayoutFromTask(long now){

        if(CurrentTask.GetStartTime() > now){
            showWaitingLayout();
        } else if (CurrentTask.GetBoardingStartTime() - T_SEC_IN_MIN > now){
            showButton(findViewById(R.id.buttonAcceptTask));
        } else if (CurrentTask.GetBoardingEndTime() > now){
            showBoardingLayout(true);
        } else if (CurrentTask.GetArrivalTime() > now){
            showDrivingLayout(true);
        } else if (CurrentTask.GetEndTime() > now){
            showBoardingLayout(false);
        } else {
            showButton(findViewById(R.id.buttonFinish));
        }
    }
}