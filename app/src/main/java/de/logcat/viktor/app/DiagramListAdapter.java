package de.logcat.viktor.app;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DiagramListAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private final B05_StatisticsView statisticsView;

    public DiagramListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.statisticsView = (B05_StatisticsView) context;
    }

    @Override
    public int getCount() {
        return SportCategory.getAllCategories().size();
    }

    @Override
    public SportCategory getItem(int position) {
        return SportCategory.getAllCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DiagramListAdapter.ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.diagram_list_row, null);
            holder = new DiagramListAdapter.ViewHolder();
            holder.diagramNameView = (TextView) convertView.findViewById(R.id.category_name);
            holder.diagramImage = (ImageView) convertView.findViewById(R.id.diagram_image);

            convertView.setTag(holder);
        } else {
            holder = (DiagramListAdapter.ViewHolder) convertView.getTag();
        }

        holder.diagramNameView.setText(getItem(position).getName());


        holder.diagramImage.setImageResource(R.drawable.ic_launcher_background);
        DiagramBuilder.buildQuantityProgressDiagram(getItem(position), holder.diagramImage);
        return convertView;
    }

    static class ViewHolder {
        TextView diagramNameView;
        ImageView diagramImage;
    }
}
