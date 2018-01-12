package de.logcat.viktor.fittnessapp1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    ListView lv_SportCategories;
    List<String> categorynames;

    Button btn_save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        SportCategory.initCategories();

        lv_SportCategories = (ListView) findViewById(R.id.listViewSportCategories);
        categorynames = Arrays.asList(SportCategory.getAllCategoryNames());

        //getting a references to EditText of the layout file activity_main
        ed_RoutineName = (EditText)findViewById(R.id.ed_category_id);

        //get th reference of SportCategory
        ListView sportCategoryList = (ListView)findViewById(R.id.listViewSportCategories);

        //Create adapter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1, Arrays.asList(SportCategory.getAllCategoryNames()) );
        //set the Adapter
        lv_SportCategories.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        lv_SportCategories.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
            {
                final Dialog dlg = new Dialog(B02_TargetView.this);
                dlg.setContentView(R.layout.dialoglayout_target);
                dlg.setTitle("Überschrift des Dialogs");

                Button dlgButton = (Button) dlg.findViewById(R.id.buttonDlg);
                dlgButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dlg.hide();
                    }
                });
                dlg.show();
                String selectedmovie=categorynames.get(position);
                Toast.makeText(getApplicationContext(), "Category Selected : "+selectedmovie,   Toast.LENGTH_LONG).show();
            }
        });


    }



}
