package de.logcat.viktor.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class RoutinesAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private final SlideMenu view;
    private final boolean hasDelete;
    private final Persistence persistence;

    public RoutinesAdapter(Context context, boolean hasDelete) {
        inflater = LayoutInflater.from(context);
        this.view = (SlideMenu) context;
        this.hasDelete = hasDelete;
        persistence = new Persistence(context);
    }

    @Override
    public int getCount() {
        return Routine.getAllRoutines().size();
    }

    @Override
    public Routine getItem(int position) {
        return Routine.getAllRoutines().get(position);
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
                holder.routineDeleteBtn = (Button) convertView.findViewById(R.id.btn_delete);
                holder.routineDeleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Routine.getAllRoutines().remove(position);
                        view.updateRoutineList();
                        persistence.saveRoutines();
                    }
                });
            }
            convertView.setTag(holder);
        } else {
            holder = (RoutinesAdapter.ViewHolder) convertView.getTag();
        }

        holder.routineNameView.setText(getItem(position).getName());
        return convertView;
    }

    static class ViewHolder {
        TextView routineNameView;
        Button routineDeleteBtn;
    }
}