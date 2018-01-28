package de.logcat.viktor.app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
            holder.diagramQuantityImage = (ImageView) convertView.findViewById(R.id.diagram_quantity_image);
            holder.diagramDurationImage = (ImageView) convertView.findViewById(R.id.diagram_duration_image);
            holder.shareDurationDiagram = (Button) convertView.findViewById(R.id.btn_share_duration);
            holder.shareQuantityDiagram = (Button) convertView.findViewById(R.id.btn_share_quantity);


            convertView.setTag(holder);
        } else {
            holder = (DiagramListAdapter.ViewHolder) convertView.getTag();
        }

        holder.diagramNameView.setText(getItem(position).getName());

        //holder.diagramImage.setImageResource(R.drawable.ic_launcher_background);
        DiagramBuilder.buildProgressDiagram(getItem(position), holder.diagramQuantityImage, true);
        DiagramBuilder.buildProgressDiagram(getItem(position), holder.diagramDurationImage, false);

        holder.shareQuantityDiagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/png");
                Bitmap bmp = getItem(position).getQuantityProgressDiagram();

                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(statisticsView.getContentResolver(), bmp, "title", null)));
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                statisticsView.startActivity(Intent.createChooser(share,"Share via"));

            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView diagramNameView;
        ImageView diagramQuantityImage;
        ImageView diagramDurationImage;
        Button shareDurationDiagram;
        Button shareQuantityDiagram;
    }
}
