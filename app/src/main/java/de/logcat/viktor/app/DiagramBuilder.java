package de.logcat.viktor.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;

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

    public static Image buildQuantityProgressDiagram(Execution[] execs) {
        double[][] dp = execsToDataPoints(execs);

        Canvas canvas = new Canvas();
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

        return null; // TODO
    }

    private static double[][] execsToDataPoints(Execution[] execs) {
        //long min_x = execs[0].getExecutiontime().getTime(), max_x = min_x;
        double min_y = execs[0].getQuantity(), max_y = min_y;

        double[][] dataPoints = new double[execs.length][2];
        int i = 0;
        for(Execution exec : execs) {
            //dataPoints[i][1] = (exec.getExecutiontime().getTime() - min_x)/(max_x-min_x);
            dataPoints[i][0] = (exec.getQuantity() - min_y)/(max_y-min_y);
            i++;
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
}
