package de.logcat.viktor.fittnessapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 0 on 06.01.2018.
 */

public class B03_CalendarView extends SlideMenu {
    final Context mContext = this;
    CalendarView simpleCalendarView;
    Button addRoutine;
    ListView routinesList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialoglayout_routinedate);
        routinesList = (ListView) findViewById(R.id.listViewRoutines);
        setContentView(R.layout.activity_calendar);

        afterCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addRoutine = (Button) findViewById(R.id.btn_addRoutine);


        simpleCalendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView

        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });

        addRoutine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RoutineDateDialog.displayMessageRoutinesList(mContext, "Zielsetzung", "",
                        new RoutineDateDialog.RoutinesDialogListener(){
                            @Override
                            public void onClosed(Routine routine) {

                            }

                        });
            }
        });

        routinesList.setAdapter(new RoutinesAdapter(this, Routine.getAllRoutines(), true));
    }
}
