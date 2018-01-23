package de.logcat.viktor.app;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class B01_RoutineView extends SlideMenu {
    ListView lv_routines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        setContentView(R.layout.b01);

        afterCreate();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get layout resources: routines list view, add routine button, delete routine button
        lv_routines = (ListView) findViewById(R.id.lv_routines);
        lv_routines.setAdapter(new RoutinesAdapter(this, true));

        Button btn_add = (Button) findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
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
        lv_routines.invalidateViews();
    }
}


