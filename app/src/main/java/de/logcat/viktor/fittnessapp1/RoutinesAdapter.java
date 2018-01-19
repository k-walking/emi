package de.logcat.viktor.fittnessapp1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 0 on 06.01.2018.
 */

public class RoutinesAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Routine> addedRoutinesList;
    private static LayoutInflater inflater = null;
    private final SlideMenu routineView;
    private final boolean hasDelete;

    public RoutinesAdapter(Context context, ArrayList<Routine> addedRoutinesList, boolean hasDelete) {
        this.addedRoutinesList = addedRoutinesList;
        inflater = LayoutInflater.from(context);
        this.routineView = (SlideMenu) context;
        this.hasDelete = hasDelete;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(hasDelete ? R.layout.routine_list_row : R.layout.routinedate_list_row, null);
            holder = new RoutinesAdapter.ViewHolder();
            holder.routineNameView = (TextView) convertView.findViewById(R.id.routineName);
            if(hasDelete){
                holder.routineDeleteBtn = (Button) convertView.findViewById(R.id.btn_delete_routine);
                holder.routineDeleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Routine.getAllRoutines().remove(position);
                        routineView.updateRoutineList();
                    }
                });
            }
            convertView.setTag(holder);
            routineView.updateRoutineList();

        } else {
            holder = (RoutinesAdapter.ViewHolder) convertView.getTag();
        }

        holder.routineNameView.setText(addedRoutinesList.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        TextView routineNameView;
        Button routineDeleteBtn;
    }
}