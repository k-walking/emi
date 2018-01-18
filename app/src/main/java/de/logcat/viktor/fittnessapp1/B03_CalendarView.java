package de.logcat.viktor.fittnessapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

/**
 * Created by 0 on 06.01.2018.
 */

public class B03_CalendarView extends SlideMenu {
    CalendarView simpleCalendarView;
    Button addRoutine;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Intent intent = new Intent(B03_CalendarView.this, B02_TargetView.class);
                startActivity(intent);
            }
        });


    }
}
