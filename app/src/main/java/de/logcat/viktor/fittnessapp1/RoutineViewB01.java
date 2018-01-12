package de.logcat.viktor.fittnessapp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by 0 on 06.01.2018.
 */

public class RoutineViewB01 extends AppCompatActivity {

    ArrayList<String> routineNamesList; //TODO create getRoutineNames in Routine

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);

        ListView routinesList=(ListView)findViewById(R.id.listViewRoutines);

        //get the reference of routines
        routineNamesList = new ArrayList<String>();
        getRoutineNames();

        //create Adapter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, routineNamesList);
        // Set The Adapter
        routinesList.setAdapter(arrayAdapter);

    }

    void getRoutineNames(){


    }
}
