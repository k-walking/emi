package de.logcat.viktor.fittnessapp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by 0 on 06.01.2018.
 */

public class RoutineViewB01 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);

        ListView RoutinesList=(ListView)findViewById(R.id.listViewRoutines);

        routinesNameList = new ArrayList<String>();
    }
}
