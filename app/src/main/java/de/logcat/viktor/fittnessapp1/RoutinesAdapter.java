package de.logcat.viktor.fittnessapp1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 0 on 06.01.2018.
 */

public class RoutinesAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Routine> addedRoutinesList;
    private static LayoutInflater inflater = null;


    public RoutinesAdapter(Activity context, ArrayList<Routine> addedBooksList) {
        this.context = context;
        this.addedRoutinesList = addedRoutinesList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return addedRoutinesList.size();
    }

    @Override
    public Routine getItem(int position) {
        return addedRoutinesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /** View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.activity_routines, null): itemView;
        TextView textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        TextView textViewAuthor = (TextView) itemView.findViewById(R.id.textViewAuthor);
        TextView textViewPublish = (TextView) itemView.findViewById(R.id.textViewPublish);
        Book selectedBook = addedBooksList.get(position);
        textViewName.setText(selectedBook.getBookName());
        textViewAuthor.setText(selectedBook.getAuthor());
        textViewPublish.setText(selectedBook.getPublishTime());*/
        return null;
    }
}
