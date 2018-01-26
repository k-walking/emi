package de.logcat.viktor.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class B03_CalendarView extends SlideMenu {

    private CalendarView simpleCalendarView;
    private Button addRoutine;
    private ListView routinesList, executionList;
    private Date selectedDate = new Date(System.currentTimeMillis());
    private ExecutionAdapter executionAdapter;
    private final Persistence persistence = new Persistence(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Execution.loadAllExecutions(this);
        setContentView(R.layout.pick_execution_dialog);

        routinesList = findViewById(R.id.listViewRoutines);

        executionAdapter = new ExecutionAdapter(B03_CalendarView.this);

        setContentView(R.layout.b03);
        executionList = findViewById(R.id.lv_executions);

        executionAdapter.setDate(selectedDate);
        executionList.setAdapter(executionAdapter);

        afterCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addRoutine = findViewById(R.id.btn_add);
        simpleCalendarView = findViewById(R.id.calendar_view); // get the reference of CalendarView

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = new Date(year-1900, month, dayOfMonth);
                executionAdapter.setDate(selectedDate);
            }
        });

        addRoutine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            RoutineDateDialog.openDialog(B03_CalendarView.this, "Zielsetzung", "",
                new RoutineDateDialog.Listener() {
                    @Override
                    public void onClosed(Routine routine) {
                        new Execution(routine.getId(), selectedDate);
                        persistence.saveExecutions();
                        updateExecutionList();
                    }
                });
            }
        });
    }

    @Override
    public void updateRoutineList() {
        routinesList.invalidateViews();
    }

    public void updateExecutionList() {
        executionAdapter.update();
        executionList.invalidateViews();
    }
}
