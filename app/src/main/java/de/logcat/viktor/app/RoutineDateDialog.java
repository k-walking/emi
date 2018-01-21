package de.logcat.viktor.app;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class RoutineDateDialog {
    private static AlertDialog.Builder builder ;
    private static LayoutInflater inflater;

    public interface Listener {
        void onClosed(Routine routine);
    }

    public static void openDialog(Context context, String title, String message, final RoutineDateDialog.Listener listener) {
        builder = new AlertDialog.Builder(context);
        inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.pick_execution_dialog, null);

        ListView listView = dialogView.findViewById(R.id.listViewRoutines);
        listView.setAdapter(new RoutinesAdapter(context,  false));

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setView(dialogView);
        builder.setCancelable(false);

        builder.show();
        final AlertDialog dialog = builder.create();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                dialog.dismiss();
                dialog.cancel();
                listener.onClosed(Routine.getAllRoutines().get(position));
            }
        });
    }





}
