package de.logcat.viktor.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.Image;
import android.widget.ImageView;

import java.util.ArrayList;

public class DiagramBuilder {

    public static Image buildRunningMap(TimeBindedCoordinate[] coords) {
        return null; // TODO
    }

    public static Image buildRunningSpeedDiagram(TimeBindedCoordinate[] coords) {
        return null; // TODO
    }

    public static Image buildDurationProgressDiagram(Execution[] execs) {
        return null; // TODO
    }

    public static void buildProgressDiagram(SportCategory category, ImageView imageView, int width, int height, boolean quantityNotDuration) {

        ArrayList<Meassurement> meassurements = findMeasurementsByCategory(category);
        double[][] dp = meassurementsToDataPoints(meassurements, quantityNotDuration);


        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        canvas.drawRGB(255, 255, 255);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);


        if(dp == null) {
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(Math.min(width, height) / 10);
            Typeface currentTypeFace = paint.getTypeface();
            Typeface bold = Typeface.create(currentTypeFace, Typeface.BOLD);
            paint.setTypeface(bold);

            canvas.drawText(meassurements.size()  == 0 ?"no data": "not enough data", width/10, height/10, paint);
        } else {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);

            for (int i = 0; i < dp.length - 1; i++) {
                float xA = (float) (dp[i][0] * width);
                float yA = (float) ((1-dp[i][1]) * height);
                float xB = (float) (dp[i + 1][0] * width);
                float yB = (float) ((1-dp[i + 1][1]) * height);
                canvas.drawLine(xA, yA, xB, yB, paint);
            }
        }

        imageView.setImageBitmap(bitmap);
    }

    private static double[][] meassurementsToDataPoints(ArrayList<Meassurement> meassurements, boolean quantityNotDuration) {
        if (meassurements.size() <= 1 )
            return null;

        long min_x = 0, max_x = 0;
        double min_y = 0, max_y = 0;

        double[][] dataPoints = new double[meassurements.size()][2];

        for(int i = 0; i < meassurements.size(); i++) {
            Meassurement m = meassurements.get(i);

            dataPoints[i][0] = m.getExecution().getExecutiontime().getTime();
            dataPoints[i][1] = quantityNotDuration ? m.getQuantity() : m.getDuration();

            min_x = i == 0 ? (long)dataPoints[0][0] : Math.min(min_x, (long)dataPoints[i][0]);
            max_x = i == 0 ? (long)dataPoints[0][0] : Math.max(max_x, (long)dataPoints[i][0]);
            min_y = i == 0 ? dataPoints[0][1] : Math.min(min_y, dataPoints[i][1]);
            max_y = i == 0 ? dataPoints[0][1] : Math.max(max_y, dataPoints[i][1]);
        }

        for(int i = 0; i < meassurements.size(); i++) {
            dataPoints[i][0] = (dataPoints[i][0] - min_x)/(max_x-min_x);
            dataPoints[i][1] = (dataPoints[i][1] - min_y)/(max_y-min_y);
        }

        dataPoints = quicksort(0, dataPoints.length-1, dataPoints);
        return dataPoints;
    }

    private static double[][] quicksort(int low, int high, double[][] numbers) {
        int i = low, j = high;
        double pivot = numbers[low + (high-low)/2][0];
        while (i <= j) {
            while (numbers[i][0] < pivot) i++;
            while (numbers[j][0] > pivot) j--;
            if (i <= j) numbers = exchange(i++, j--, numbers);
        }
        if (low < j) numbers = quicksort(low, j, numbers);
        if (i < high) numbers = quicksort(i, high, numbers);
        return numbers;
    }

    private static double[][] exchange(int i, int j, double[][] numbers) {
        double[] temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
        return numbers;
    }

    private static ArrayList<Meassurement> findMeasurementsByCategory(SportCategory category) {
        ArrayList<Meassurement> meassurements = new ArrayList<Meassurement>();
        for(int i = 0; i < Execution.getAllExecutions().size(); i++) {
            Meassurement[] execMeassurements = Execution.getAllExecutions().get(i).getAllMeassurements();
            for(int j = 0; j < execMeassurements.length; j++) {
                Meassurement m = execMeassurements[j];
                if(m.getTarget().getCategory() == category)
                    meassurements.add(m);
            }
        }
        return meassurements;
    }
}
