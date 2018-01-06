package de.logcat.viktor.fittnessapp1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by 0 on 05.01.2018.
 */

public class TargetViewActivity extends Activity {

    TextView tvCategoryName;
    TextView tvDuration;
    TextView tvQuantity;
    TextView tvId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        //fetching data from a parcelable object passed from MainActivity
        Target target = getIntent().getParcelableExtra("target");

        //get th reference of SportCategory
        ListView sportCategoryList = (ListView)findViewById(R.id.listViewSportCategories);

        //Create adapter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1, target.getCategoryName());
        //set the Adapter
        sportCategoryList.setAdapter(arrayAdapter);

        // getting references to Textview  of the layout file activitxy_target
        tvCategoryName = (TextView)findViewById(R.id.d_category_name);
        tvDuration = (TextView)findViewById(R.id.d_duration);
        tvQuantity = (TextView)findViewById(R.id.d_quantity);
        tvId = (TextView)findViewById(R.id.d_id);

        if(target != null) {
            tvCategoryName.setText("Name: "+target.getCategoryName());
            tvDuration.setText("Name: "+target.getDuration());
            tvQuantity.setText("Name: "+target.getQuantity());
            tvId.setText("Name: "+target.getId());
        }
    }



}
