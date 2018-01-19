package de.logcat.viktor.fittnessapp1;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import java.util.ArrayList;

/**
 * Created by 0 on 06.01.2018.
 */

public class B01_RoutineView extends SlideMenu {
    ListView routinesList;
    Button addRoutine;
    Button deleteRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);

        afterCreate();

        //Hamburger menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get layout resources: routines list view, add routine button, delete routine button
        routinesList = (ListView) findViewById(R.id.lv_Routines);
        addRoutine = (Button) findViewById(R.id.btn_addRoutine);
        deleteRoutine = (Button) findViewById(R.id.btn_delete_routine);

        addRoutine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(B01_RoutineView.this, B02_TargetView.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRoutineList();
    }

    public void updateRoutineList() {
        routinesList.setAdapter(new RoutinesAdapter(this, Routine.getAllRoutines(), true));
    }
}



