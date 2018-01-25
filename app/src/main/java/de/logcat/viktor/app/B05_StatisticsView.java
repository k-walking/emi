package de.logcat.viktor.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class B05_StatisticsView extends SlideMenu {
    private ListView lv_diagrams;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b05);
        afterCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv_diagrams = (ListView) findViewById(R.id.lv_diagrams);

        lv_diagrams.setAdapter(new DiagramListAdapter(this));
    }



}
