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
    ListView lv_Targets;
    ArrayList<String> targetnames = new ArrayList<String>();
    ArrayList<Target> targets = new ArrayList<Target>();
    final Context mContext = this;

    Button btn_save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);



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
                new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1, Arrays.asList(SportCategory.getAllCategoryNames()) );
        //set the Adapter
        lv_SportCategories.setAdapter(arrayAdapter);





        // register onClickListener to handle click events on each item
        lv_SportCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

                //Target
                final SportCategory i = SportCategory.getAllCategories().get(position);

                final Target target1 = new Target(i, "13234", 0 ,0);
                //show soecific dialog for different units in Target
                if ("km" == SportCategory.getAllCategories().get(position).getUnit()) {
                    TargetQuestionDialog.displayMessageTime_Kilometer(mContext, "Zielsetzung", "",
                            new TargetQuestionDialog.TargetKilometerQuestionDialogListener(){

                                @Override
                                public void onClosed(String time) {
                                    String categoryName = target1.getCategory().getName();
                                    target1.setDuration(time);
                                    TextView tv_target = (TextView)findViewById(R.id.tv_target1);
                                    tv_target.setText(time);
                                    targetnames.add(categoryName);
                                }

                                public void onClosed2(double kilometer) {
                                    TextView target2 = (TextView)findViewById(R.id.tv_target2);
                                    target2.setText(Double.toString(kilometer));
                                    target1.setQuantity(kilometer);
                                    targets.add(target1);
                                }
                            });
                } else if("Stück" == SportCategory.getAllCategories().get(position).getUnit()) {
                    TargetQuestionDialog.displayMessageTime_Amount(mContext, "Zielsetzung", "",
                            new TargetQuestionDialog.TargetAmountQuestionDialogListener(){
                                @Override
                                public void onClosed(String time) {

                                }

                                public void onClosed2(double amount) {

                                }
                            });
                } else {
                    TargetQuestionDialog.displayMessageTime(mContext, "Zielsetzung", "",
                            new TargetQuestionDialog.TargetQuestionDialogListener() {
                                @Override
                                public void onClosed(String time) {

                                }
                            });
                }



            }
        });
        //ListView Targets
        SportCategory cat = new SportCategory("Laufen", "kilometer");
        Target target2 = new Target(cat, "dadw", 44, 2);

        targets.add(target2);
        //get th reference of Targets
        final ListView TargetsList = (ListView)findViewById(R.id.listViewTargets);

        //Create Targets adapter
        ArrayAdapter<String> arrayAdapter2 =
                new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1, targetnames );
        //set the Adapter
        TargetsList.setAdapter(new TargetListAdapter(this, targets));
    }
}
