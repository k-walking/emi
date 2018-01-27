package de.logcat.viktor.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MeassurementsAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private final B04_ExecutionView executionView;
    private final Execution execution;
    private final Persistence persistence;

    public MeassurementsAdapter(Context context, Execution execution) {
        inflater = LayoutInflater.from(context);
        this.execution = execution;
        this.executionView = (B04_ExecutionView) context;
        persistence = new Persistence(context);
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
        final Meassurement meassurement = getItem(position);
        final SportCategory category = meassurement.getTarget().getCategory();

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.meassurements_list_row, null);
            holder = new MeassurementsAdapter.ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (MeassurementsAdapter.ViewHolder) convertView.getTag();
        }

        holder.categoryNameView = (TextView) convertView.findViewById(R.id.category_name);
        holder.quantityView = (TextView) convertView.findViewById(R.id.quantity);
        holder.durationView = (TextView) convertView.findViewById(R.id.duration);
        holder.timeActionBtn = (Button) convertView.findViewById(R.id.btn_start);
        holder.timeActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(meassurement.getTimeStarted() > 0){

                meassurement.setDuration(meassurement.getDuration()+System.currentTimeMillis() - meassurement.getTimeStarted());
                if(category.hasQuanitityParameter()) {
                    SimpleDialog.openDialog(executionView, category.getQuantityQuestion(), "0", new SimpleDialog.Listener() {
                        @Override
                        public void submitAnswer(String answer) {
                            meassurement.setQuantity(Double.parseDouble(answer));
                            persistence.saveMeasurements();
                            meassurement.getTarget().getCategory().setQuantityProgressDiagram(null);
                            meassurement.getTarget().getCategory().setDurationProgressDiagram(null);
                            DiagramBuilder.updateDiagram(category,true);

                        }
                    });
                } else persistence.saveMeasurements();
                meassurement.setTimeStarted(0);
            } else {
                meassurement.setTimeStarted(System.currentTimeMillis());
            }
            executionView.updateMeassurementsList();
            }
        });

        int quantityProgress = (int) (100.0 * getItem(position).getQuantity()/getItem(position).getTarget().getQuantity());
        int durationProgress = (int) (0.1 / 60 * getItem(position).getDuration()/getItem(position).getTarget().getDuration());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(1970,0,1);
        date = new Date(date.getTime()+meassurement.getDuration()+(meassurement.getTimeStarted() == 0 ? 0 : System.currentTimeMillis() - meassurement.getTimeStarted()));

        holder.categoryNameView.setText(category.getName());
        holder.quantityView.setText(category.hasQuanitityParameter()?getItem(position).getQuantity()+" "+category.getUnit()+" ("+quantityProgress +"%)": "");
        holder.durationView.setText("("+durationProgress +"%) "+sdf.format(date));
        return convertView;
    }

    static class ViewHolder {
        TextView categoryNameView;
        TextView durationView;
        TextView quantityView;
        Button timeActionBtn;
    }
}
