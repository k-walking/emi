package de.logcat.viktor.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MeassurementsAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private final B04_ExecutionView executionView;
    private final Execution execution;

    public MeassurementsAdapter(Context context, Execution execution) {
        inflater = LayoutInflater.from(context);
        this.execution = execution;
        this.executionView = (B04_ExecutionView) context;
    }

    @Override
    public int getCount() {
        return execution.getAllMeassurements().length;
    }

    @Override
    public Meassurement getItem(int position) {
        return execution.getAllMeassurements()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MeassurementsAdapter.ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.meassurements_list_row, null);
            holder = new MeassurementsAdapter.ViewHolder();
            holder.categoryNameView = (TextView) convertView.findViewById(R.id.category_name);

            convertView.setTag(holder);
        } else {
            holder = (MeassurementsAdapter.ViewHolder) convertView.getTag();
        }
        holder.timeActionBtn = (Button) convertView.findViewById(R.id.btn_start);
        holder.timeActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
                SimpleDialog.openDialog(executionView, getItem(position).getTarget().getCategory().getQuantityQuestion(), "0", new SimpleDialog.Listener() {
                    @Override
                    public void submitAnswer(String answer) {
                        getItem(position).setQuantity(Double.parseDouble(answer));
                        executionView.updateMeassurementsList();
                    }
                });

            }
        });

        holder.categoryNameView.setText(getItem(position).getTarget().getCategory().getName());
        return convertView;
    }

    static class ViewHolder {
        TextView categoryNameView;
        Button timeActionBtn;
    }
}
