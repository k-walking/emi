package de.logcat.viktor.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by 0 on 14.01.2018.
 */

public class TargetListAdapter extends BaseAdapter {
    private final Routine routine;
    private LayoutInflater layoutInflater;

    public TargetListAdapter(Context aContext, Routine routine) {
        this.routine = routine;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return routine.getAllTargets().size();
    }

    @Override
    public Object getItem(int position) {
        return routine.getAllTargets().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.target_list_row, null);
            holder = new ViewHolder();
            holder.targetCategoryView = (TextView) convertView.findViewById(R.id.targetCategoryName);
            holder.targetQuantityView = (TextView) convertView.findViewById(R.id.targetQuantity);
            holder.targetDurationView = (TextView) convertView.findViewById(R.id.targetDuration);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.targetCategoryView.setText(routine.getAllTargets().get(position).getCategory().getName());
        holder.targetDurationView.setText(Double.toString(routine.getAllTargets().get(position).getDuration()) + " min");
        holder.targetQuantityView.setText(routine.getAllTargets().get(position).getCategory().getUnit() == null ? "" : Double.toString(routine.getAllTargets().get(position).getQuantity()) + " " + routine.getAllTargets().get(position).getCategory().getUnit());
        return convertView;
    }

    static class ViewHolder {
        TextView targetCategoryView;
        TextView targetDurationView;
        TextView targetQuantityView;
    }

}
