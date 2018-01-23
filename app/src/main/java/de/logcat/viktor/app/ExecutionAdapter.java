package de.logcat.viktor.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class ExecutionAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private final B03_CalendarView calendarView;
    private Date date;
    private ArrayList<Execution> currentExecutions;

    public ExecutionAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.calendarView = (B03_CalendarView) context;
    }

    public void setDate(Date date) {
        this.date = date;
        calendarView.updateExecutionList();
    }

    @Override
    public int getCount() {
        return currentExecutions.size();
    }

    @Override
    public Execution getItem(int position) {
        return currentExecutions.get(position);
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



            convertView.setTag(holder);
        } else {
            holder = (ExecutionAdapter.ViewHolder) convertView.getTag();
        }
        holder.executionDeleteBtn = (Button) convertView.findViewById(R.id.btn_delete_execution);
        holder.executionPlayBtn = (Button) convertView.findViewById(R.id.btn_start);

        holder.executionDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Execution.getAllExecutions().remove(getItem(position));

                calendarView.updateExecutionList();
            }
        });
        holder.executionPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(calendarView, B04_ExecutionView.class);
                calendarView.startActivity(intent);
            }
        });

        holder.executionNameView.setText(getItem(position).getRoutine().getName());
        return convertView;
    }

    public void update(){
        currentExecutions = new ArrayList<Execution>();
        for(int i = 0; i < Execution.getAllExecutions().size(); i++){
            if(Execution.getAllExecutions().get(i).isOnDate(date)){
                currentExecutions.add(Execution.getAllExecutions().get(i));
            }
        }
    }

    static class ViewHolder {
        TextView executionNameView;
        Button executionDeleteBtn;
        Button executionPlayBtn;
    }
}
