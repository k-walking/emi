package de.logcat.viktor.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;

public class B02_TargetView extends Activity {

    private List<String> categoryNames;
    private static Routine routine;
    private String routineName;
    private final Persistence persistence = new Persistence(this);

    private EditText ed_routine_name;
    private ListView lv_SportCategories;
    private Button btn_save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b02);

        final ListView targetsList = (ListView)findViewById(R.id.lv_targets);

        targetsList.setAdapter(new TargetListAdapter(this, routine));

        //get layout resources: save button,  routine name text, category list, targets list
        btn_save = findViewById(R.id.btn_save);
        ed_routine_name = (EditText)findViewById(R.id.ed_routine_name);
        ed_routine_name.setText("Übung #" + routine.getId());
        lv_SportCategories = (ListView) findViewById(R.id.lv_categories);
        categoryNames = Arrays.asList(SportCategory.getAllCategoryNames());

        //Create sport categories adapter and set to list view
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1, Arrays.asList(SportCategory.getAllCategoryNames()));
        lv_SportCategories.setAdapter(arrayAdapter);

        //register onClickListener to handle click events on each item
        lv_SportCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
            onClickedCategory(SportCategory.getAllCategories().get(position));
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            if(routine.getAllTargets().size() > 0) {
                routineName = ed_routine_name.getText().toString();
                routine.setName(routineName);
                persistence.saveRoutines();
                finish();
            }
            }
        });
    }

    private void onClickedCategory(final SportCategory category) {

        final ListView targetsList = (ListView)findViewById(R.id.lv_targets);
        final Target target = new Target(category);
        routine.addTarget(target);

        final SimpleDialog.Listener listenerQuantity = new SimpleDialog.Listener() {
            @Override
            public void submitAnswer(String answer) {
                target.setQuantity(Double.parseDouble(answer));
                targetsList.invalidateViews();
            }
        };

        SimpleDialog.Listener listenerDuration = new SimpleDialog.Listener() {
            @Override
            public void submitAnswer(String answer) {
                target.setDuration(Double.parseDouble(answer));
                if(category.getUnit() == null)
                    targetsList.invalidateViews();
                else
                    SimpleDialog.openDialog(B02_TargetView.this, category.getQuantityQuestion(), "5", listenerQuantity);
                targetsList.invalidateViews();
            }
        };

        SimpleDialog.openDialog(this, "Wie lange möchtest du '"+category.getName()+"' ausführen?", "60", listenerDuration);
    }

    public static void setRoutine(Routine routine) {
        B02_TargetView.routine = routine;
    }
}
