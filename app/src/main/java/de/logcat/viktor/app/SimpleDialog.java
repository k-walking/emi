package de.logcat.viktor.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SimpleDialog {

    public interface Listener {
        void submitAnswer(String answer);
    }

    public static void openDialog(Context context, String question, String predefinedAnswer, final Listener listener) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.simple_dialog, null);
        final TextView tv_question = (TextView) dialogView.findViewById(R.id.tv_question);
        final EditText ed_answer = (EditText) dialogView.findViewById(R.id.ed_answer);

        tv_question.setText(question);
        ed_answer.setText(predefinedAnswer);

        builder.setView(dialogView);
        builder.
                setCancelable(false).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if(listener != null) {
                            dialog.dismiss();
                            listener.submitAnswer(ed_answer.getText().toString());
                        }
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE);
    }
}
