package de.logcat.viktor.app;

public class Meassurement {
    private long duration = 0;
    private double quantity = 0;
    private final Target target;
    private long timeStarted = 0;

    public Meassurement(Target target) {
        this.target = target;
    }

    public long getDuration() {
        return duration;
    }

    public double getQuantity() {
        return quantity;
    }

    public long getTimeStarted() {
        return timeStarted;
    }

    public Target getTarget() {
        return target;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setTimeStarted(long timeStarted) {
        this.timeStarted = timeStarted;
    }
}

