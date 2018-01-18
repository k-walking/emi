package de.logcat.viktor.fittnessapp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;

/**
 * Created by 0 on 06.01.2018.
 */

public class B03_CalendarView extends SlideMenu {
    CalendarView simpleCalendarView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        simpleCalendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView

        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });


    }
}
