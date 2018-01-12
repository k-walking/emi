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



    ArrayList<String> routineNamesList; //TODO create getRoutineNames in Routine

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);

        SportCategory.initCategories();

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

    public static

    void getRoutineNames(){


    }
}
