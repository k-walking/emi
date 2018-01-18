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

    final Context mContext = this;
    List<String> categoryNames;
    Routine routine;
    String routineName;

    EditText ed_RoutineName;
    ListView lv_SportCategories;
    Button btn_save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        routine = new Routine();

        //get layout resources: save button,  routine name text, category list, targets list
        final ListView TargetsList = (ListView)findViewById(R.id.lv_Targets);
        btn_save = findViewById(R.id.btn_save);
        ed_RoutineName = (EditText)findViewById(R.id.ed_RoutineName);
        lv_SportCategories = (ListView) findViewById(R.id.listViewSportCategories);
        categoryNames = Arrays.asList(SportCategory.getAllCategoryNames());

        //Create sport categories adapter and set to list view
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1, Arrays.asList(SportCategory.getAllCategoryNames()));
        lv_SportCategories.setAdapter(arrayAdapter);

        //register onClickListener to handle click events on each item
        lv_SportCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

            //get clicked sport category and make new targets to store input data
            final SportCategory currentCategory = SportCategory.getAllCategories().get(position);
            final Target target1 = new Target(currentCategory, "", 0 ,0);
            final Target target2 = new Target(currentCategory, "", 0 ,0);
            final Target target3 = new Target(currentCategory, "", 0 ,0);

            //show specific dialog for different unit-cases
            if ("km" == SportCategory.getAllCategories().get(position).getUnit()) {
                TargetQuestionDialog.displayMessageTime_Kilometer(mContext, "Zielsetzung", "",
                        new TargetQuestionDialog.TargetKilometerQuestionDialogListener(){
                            @Override
                            public void onClosed(String time) {
                                target1.setDuration(time);
                            }

                            public void onClosed3(double quantity) {
                                target1.setQuantity(quantity);
                                routine.addTarget(target1);
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
                                routine.addTarget(target2);
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
                                routine.addTarget(target3);
                            }
                        });
            }
            }
        });

        //set the targets view adapter
        TargetsList.setAdapter(new TargetListAdapter(this, routine.getAllTargets()));

        //save routine and finish activity TODO routine class, store data
        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                routineName = ed_RoutineName.getText().toString();
                routine.setName(routineName);

                Persistence.save(routine);
                finish();
            }
        });
    }
}
