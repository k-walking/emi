package de.logcat.viktor.fittnessapp1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 0 on 05.01.2018.
 */

public class B02_TargetView extends Activity {

    EditText ed_RoutineName;
    TextView tv_target1;
    ListView lv_SportCategories;
    List<String> categorynames;
    ArrayList<Target> targets = new ArrayList<Target>();
    Routine routine;
    final Context mContext = this;
    String routineName;

    Button btn_save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        btn_save = findViewById(R.id.btn_save);

        //getting a references to EditText of the layout file activity_main
        ed_RoutineName = (EditText)findViewById(R.id.ed_category_id);

        //ListView Sportcategories
        SportCategory.initCategories();
        lv_SportCategories = (ListView) findViewById(R.id.listViewSportCategories);
        categorynames = Arrays.asList(SportCategory.getAllCategoryNames());

        //get th reference of SportCategory
        ListView sportCategoryList = (ListView)findViewById(R.id.listViewSportCategories);

        //Create Sportcategories adapter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1, Arrays.asList(SportCategory.getAllCategoryNames()));

        //set the Adapter
        lv_SportCategories.setAdapter(arrayAdapter);

        //get th reference of Targets
        final ListView TargetsList = (ListView)findViewById(R.id.listViewTargets);



        // register onClickListener to handle click events on each item
        lv_SportCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

            final SportCategory i = SportCategory.getAllCategories().get(position);
            final Target target1 = new Target(i, "", 0 ,0);
            final Target target2 = new Target(i, "", 0 ,0);
            final Target target3 = new Target(i, "", 0 ,0);

            //show soecific dialog for different units in Target
            if ("km" == SportCategory.getAllCategories().get(position).getUnit()) {
                TargetQuestionDialog.displayMessageTime_Kilometer(mContext, "Zielsetzung", "",
                        new TargetQuestionDialog.TargetKilometerQuestionDialogListener(){

                            @Override
                            public void onClosed(String time) {
                                target1.setDuration(time);
                            }

                            public void onClosed3(double quantity) {
                                target1.setQuantity(quantity);
                                targets.add(target1);
                                //routine.addTarget(target1);
                            }
                        });
            } else if("Stück" == SportCategory.getAllCategories().get(position).getUnit()) {
                TargetQuestionDialog.displayMessageTime_Kilometer(mContext, "Zielsetzung", "",
                        new TargetQuestionDialog.TargetKilometerQuestionDialogListener(){
                            @Override
                            public void onClosed(String time) {
                                target2.setDuration(time);
                            }

                            public void onClosed3(double quantity) {
                                target2.setQuantity(quantity);
                                targets.add(target2);
                                //routine.addTarget(target2);
                            }
                        });
            } else {
                TargetQuestionDialog.displayMessageTime(mContext, "Zielsetzung", "",
                        new TargetQuestionDialog.TargetQuestionDialogListener() {
                            @Override
                            public void onClosed(String time) {
                                target3.setDuration(time);
                            }

                            public void onClosed4(double quantity) {
                                target3.setQuantity(quantity);
                                targets.add(target3);
                                //routine.addTarget(target3);
                            }
                        });
            }
            }
        });



        //set the Adapter
        TargetsList.setAdapter(new TargetListAdapter(this, targets));

        //save routine TODO routine class, store data
        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                routineName = ed_RoutineName.getText().toString();


            }
        });

    }
}
