package de.logcat.viktor.fittnessapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;


/**
 * Created by 0 on 18.01.2018.
 */

public class RoutineDateDialog {
    private static AlertDialog.Builder builder ;
    private static LayoutInflater inflater;
    private static View dialogViewRoutines;

    public interface RoutinesDialogListener{
        public void onClosed(Routine routine);
    }

    public static AlertDialog displayMessageRoutinesList(Context context, String title, String message, final RoutineDateDialog.RoutinesDialogListener listener) {
        builder = new AlertDialog.Builder(context);
        inflater = LayoutInflater.from(context);
        dialogViewRoutines = inflater.inflate(R.layout.dialoglayout_routinedate, null);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setView(dialogViewRoutines);
        builder.
                setCancelable(false).
                setPositiveButton("weiter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(listener != null) {
                            //get UserInput

                        }
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        builder.show();
        return builder.create();
    }
}
