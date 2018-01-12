package de.logcat.viktor.fittnessapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String PAR_KEY = "package de.logcat.viktor.testpassdata.par";








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    /**private void setupViews() {
        View targetsView = findViewById(R.id.targetsView);
        targetsView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Target mTarget = new Target("","", "", 0);
                mTarget.setCategoryNameName("ProgrammJava");
                mTarget.setQuantity("23");
                mTarget.setDuration("Die Bitch");
                mTarget.setId(12);
                Intent mIntent = new Intent(view.getContext(), TargetPass.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(PAR_KEY, mTarget);
                mIntent.putExtras(mBundle);

                view.getContext().startActivity(mIntent);
            }
        });

        Button pButton = (Button) findViewById(R.id.ParButton);
        pButton.setOnClickListener(this);
    }**/
}
