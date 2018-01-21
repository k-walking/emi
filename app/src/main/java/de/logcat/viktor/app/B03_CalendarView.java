package de.logcat.viktor.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

public class B03_CalendarView extends SlideMenu {

    private CalendarView simpleCalendarView;
    private Button addRoutine;
    private ListView routinesList, executionList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_execution_dialog);
        routinesList = findViewById(R.id.listViewRoutines);

        setContentView(R.layout.b03);
        executionList = findViewById(R.id.lv_executions);
        executionList.setAdapter(new ExecutionAdapter(B03_CalendarView.this));

        afterCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addRoutine = findViewById(R.id.btn_add);
        simpleCalendarView = findViewById(R.id.calendar_view); // get the reference of CalendarView

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "." + (month+1) + "." + year, Toast.LENGTH_LONG).show();
            }
        });


        addRoutine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            RoutineDateDialog.openDialog(B03_CalendarView.this, "Zielsetzung", "",
                new RoutineDateDialog.Listener() {
                    @Override
                    public void onClosed(Routine routine) {
                        new Execution(routine.getId(), null);
                        executionList.invalidateViews();
                    }
                });
            }
        });
    }

    @Override
    public void updateRoutineList() {
        routinesList.invalidateViews();
    }
}
