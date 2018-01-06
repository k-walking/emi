package de.logcat.viktor.fittnessapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String PAR_KEY = "package de.logcat.viktor.testpassdata.par";

    EditText etCategoryName;
    EditText etDuration;
    EditText etQantity;
    EditText etId;




    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SportCategory.initCategories();

        //getting a references to EditText of the layout file activity_main
        etCategoryName = (EditText)findViewById(R.id.ed_category_name);
        etDuration = (EditText)findViewById(R.id.ed_duration);
        etQantity = (EditText)findViewById(R.id.ed_quantity);
        etId = (EditText)findViewById(R.id.ed_id);

        //getting reference to Button
        btnOk = (Button)findViewById(R.id.btn_ok);

        //Setting onClickListener for the ok-btn
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating an instance of Target class with user input data
                Target target = new Target(
                        Double.parseDouble(etDuration.getText().toString()),
                        Double.parseDouble(etQantity.getText().toString()),
                        Integer.parseInt(etId.getText().toString()));

                //ceating an Intent to opne theTargetViewActivity
                Intent intent = new Intent(getBaseContext(), TargetViewB02.class);

                //passing data as a parcelable objact to StudentViewActivity
                intent.putExtra("target", target);

                //opening the activity
                startActivity(intent);

            }
        });
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
