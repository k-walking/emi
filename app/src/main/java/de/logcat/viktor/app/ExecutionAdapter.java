package de.logcat.viktor.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ExecutionAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private final B03_CalendarView calendarView;

    public ExecutionAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.calendarView = (B03_CalendarView) context;
    }

    @Override
    public int getCount() {
        return Execution.getAllExecutions().size();
    }

    @Override
    public Execution getItem(int position) {
        return Execution.getAllExecutions().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ExecutionAdapter.ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.execution_list_row, null);
            holder = new ExecutionAdapter.ViewHolder();
            holder.executionNameView = (TextView) convertView.findViewById(R.id.executionName);

                holder.executionDeleteBtn = (Button) convertView.findViewById(R.id.btn_delete_execution);
                holder.executionDeleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Routine.getAllRoutines().remove(position);
                        calendarView.updateRoutineList();
                    }
                });

            convertView.setTag(holder);
        } else {
            holder = (ExecutionAdapter.ViewHolder) convertView.getTag();
        }

        holder.executionNameView.setText(getItem(position).getRoutine().getName());
        return convertView;
    }

    static class ViewHolder {
        TextView executionNameView;
        Button executionDeleteBtn;
    }
}
