package de.logcat.viktor.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

public class B03_CalendarView extends SlideMenu {
    final Context mContext = this;
    CalendarView simpleCalendarView;
    Button addRoutine;
    ListView routinesList, executionList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialoglayout_routinedate);
        routinesList = (ListView) findViewById(R.id.listViewRoutines);

        setContentView(R.layout.b03);
        executionList = (ListView) findViewById(R.id.lv_executions);

        afterCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addRoutine = (Button) findViewById(R.id.btn_add);
        simpleCalendarView = (CalendarView) findViewById(R.id.calendar_view); // get the reference of CalendarView

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "." + (month+1) + "." + year, Toast.LENGTH_LONG).show();
            }
        });


        addRoutine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            RoutineDateDialog.displayMessageRoutinesList(mContext, "Zielsetzung", "",
                new RoutineDateDialog.RoutinesDialogListener(){
                    @Override
                    public void onClosed(Routine routine) {
                        new Execution(routine.getId(), null);
                        executionList.setAdapter(new ExecutionAdatpter(B03_CalendarView.this));
                    }
                });
            }
        });


    }
}
