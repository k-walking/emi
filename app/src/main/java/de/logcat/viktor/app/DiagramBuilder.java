package de.logcat.viktor.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.widget.ImageView;

import java.util.ArrayList;

public class DiagramBuilder {

    private static final int WIDTH = 400, HEIGHT = 400;

    public static Image buildRunningMap(TimeBindedCoordinate[] coords) {
        return null; // TODO
    }

    public static Image buildRunningSpeedDiagram(TimeBindedCoordinate[] coords) {
        return null; // TODO
    }

    public static Image buildDurationProgressDiagram(Execution[] execs) {
        return null; // TODO
    }

    public static void buildQuantityProgressDiagram(SportCategory category, ImageView imageView) {
        
        double[][] dp = execsToDataPoints(findMeasurementsByCategory(category));

        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        canvas.drawRGB(255, 255, 255);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        for(int i = 0; i < dp.length-1; i++) {
            float xA = (float)(dp[i][0] * WIDTH);
            float yA = (float)(dp[i][1] * HEIGHT);
            float xB = (float)(dp[i+1][0] * WIDTH);
            float yB = (float)(dp[i+1][1] * HEIGHT);
            canvas.drawLine(xA, yA, xB, yB, paint);
        }

        imageView.setImageBitmap(bitmap);
    }

    private static double[][] execsToDataPoints(ArrayList<Meassurement> meassurements) {
        long min_x = meassurements.get(0).getDuration(), max_x = min_x;
        double min_y = meassurements.get(0).getQuantity(), max_y = min_y;

        for(int i = 0; i < meassurements.size(); i++) {
            Meassurement m = meassurements.get(i);
            min_x = Math.min(min_x, m.getDuration());
            max_x = Math.max(max_x, m.getDuration());
            min_y = Math.min(min_y, m.getQuantity());
            max_y = Math.max(max_y, m.getQuantity());
        }

        double[][] dataPoints = new double[meassurements.size()][2];
        for(int i = 0; i < meassurements.size(); i++) {
            Meassurement m = meassurements.get(i);
            dataPoints[i][1] = (m.getDuration() - min_x)/(max_x-min_x);
            dataPoints[i][0] = (m.getQuantity() - min_y)/(max_y-min_y);
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
