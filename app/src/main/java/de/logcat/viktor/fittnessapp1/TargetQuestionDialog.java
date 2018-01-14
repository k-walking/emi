package de.logcat.viktor.fittnessapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by 0 on 13.01.2018.
 */

public class TargetQuestionDialog {

    public interface TargetQuestionDialogListener{
        public void onClosed(String time);


    }

    public interface TargetKilometerQuestionDialogListener{
        public void onClosed(String time);
        public void onClosed2(double kilometer);



    }

    public interface TargetAmountQuestionDialogListener{
        public void onClosed(String time);
        public void onClosed2(double amount);
    }

    //Dialog questioning for amount unit
    public static AlertDialog displayMessageTime_Amount(Context context, String title, String message, final TargetAmountQuestionDialogListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final LayoutInflater inflater = LayoutInflater.from(context);

        builder.setTitle(title);
        builder.setMessage(message);

        final View dialogViewTime = inflater.inflate(R.layout.dialoglayout_timetarget, null);
        final View dialogViewAmount = inflater.inflate(R.layout.dialoglayout_amounttarget, null);

        builder.setView(dialogViewTime);
        builder.
                setCancelable(false).
                setPositiveButton("weiter", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(listener != null) {
                            //get UserInput
                            final EditText ed_time1 = (EditText)dialogViewTime.findViewById(R.id.ed_time);
                            final EditText ed_amount1 = (EditText)dialogViewTime.findViewById(R.id.ed_amount);

                            listener.onClosed(ed_time1.getText().toString());




                            builder.setView(dialogViewAmount);
                            builder.
                                    setCancelable(false).
                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if(listener != null) {
                                                //TODO get user input

                                                listener.onClosed2(Double.parseDouble(ed_amount1.getText().toString()));
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

    //Dialog questioning for kilometer
    public static AlertDialog displayMessageTime_Kilometer(Context context, String title, String message, final TargetKilometerQuestionDialogListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final LayoutInflater inflater = LayoutInflater.from(context);

        builder.setTitle(title);
        builder.setMessage(message);

        final View dialogViewTime = inflater.inflate(R.layout.dialoglayout_timetarget, null);
        final View dialogViewKilometer = inflater.inflate(R.layout.dialoglayout_kilometertarget, null);


        builder.setView(dialogViewTime);
        builder.
                setCancelable(false).
                setPositiveButton("Weiter", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(listener != null) {
                            //get UserInput
                            final EditText ed_time2 = (EditText)dialogViewTime.findViewById(R.id.ed_time);

                            listener.onClosed(ed_time2.getText().toString());

                            //Next question for specific unit
                            builder.setView(dialogViewKilometer);
                            builder.
                                    setCancelable(false).
                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if(listener != null) {
                                                //TODO get user input
                                                final EditText ed_kilometer = (EditText)dialogViewKilometer.findViewById(R.id.ed_kilometer);
                                                listener.onClosed2(Double.parseDouble(ed_kilometer.getText().toString()));
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

    //Dialog questioning only for time unit
    public static AlertDialog displayMessageTime(Context context, String title, String message, final TargetQuestionDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        builder.setTitle(title);
        builder.setMessage(message);

        final View dialogViewTime = inflater.inflate(R.layout.dialoglayout_timetarget, null);


        builder.setView(dialogViewTime);
        builder.
                setCancelable(false).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    if(listener != null) {
                        final EditText ed_time3 = (EditText)dialogViewTime.findViewById(R.id.ed_time);

                        listener.onClosed(ed_time3.getText().toString());
                    }

                    dialog.cancel();
                        ////TODO get user input and set it to output
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