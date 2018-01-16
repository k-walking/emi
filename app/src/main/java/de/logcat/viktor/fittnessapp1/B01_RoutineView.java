package de.logcat.viktor.fittnessapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by 0 on 06.01.2018.
 */

public class B01_RoutineView extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);

        SportCategory.initCategories();

        ListView routinesList = (ListView) findViewById(R.id.listViewRoutines);
        Button addRoutines = (Button) findViewById(R.id.btn_addRoutine);

        addRoutines.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(B01_RoutineView.this, B02_TargetView.class);
                startActivity(intent);
            }
        });

        //set the Adapter
        routinesList.setAdapter(new RoutinesAdapter(this, Routine.getAllRoutines()));

    }
}
