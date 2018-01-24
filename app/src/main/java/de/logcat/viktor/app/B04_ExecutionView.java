package de.logcat.viktor.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ListView;

import java.util.Timer;
import java.util.TimerTask;

public class B04_ExecutionView extends Activity {
    private ListView lv_meassurements;
    private static Execution execution;
    private Thread thread;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b04);

        lv_meassurements = findViewById(R.id.lv_meassurements);
        final MeassurementsAdapter meassurementsAdapter = new MeassurementsAdapter(this, execution);
        lv_meassurements.setAdapter(meassurementsAdapter);

        thread = new Thread(){
            @Override
            public void run() {
                try {
                    while(true)synchronized (this) {
                        wait(1000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>asd");
                                updateMeassurementsList();
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        };
        thread.start();
    }

    public static void setExecution(Execution execution) {
        B04_ExecutionView.execution = execution;
    }

    public void updateMeassurementsList() {
        lv_meassurements.invalidateViews();
    }

    @Override
    protected void onDestroy() {
        //thread.destroy(); TODO
        super.onDestroy();
    }
}
