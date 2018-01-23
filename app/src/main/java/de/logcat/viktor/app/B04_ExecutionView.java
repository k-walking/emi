package de.logcat.viktor.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class B04_ExecutionView extends Activity {
    private ListView lv_meassurements;
    private static Execution execution;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b04);

        lv_meassurements = findViewById(R.id.lv_meassurements);
        final MeassurementsAdapter meassurementsAdapter = new MeassurementsAdapter(this, execution);
        lv_meassurements.setAdapter(meassurementsAdapter);
    }

     public static void setExecution(Execution execution) {
        B04_ExecutionView.execution = execution;
     }

    public void updateMeassurementsList() {
        lv_meassurements.invalidateViews();
    }
}
