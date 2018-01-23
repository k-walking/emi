package de.logcat.viktor.app;

public class Meassurement {
    private double duration = 0, quantity = 0;
    private final Target target;

    public Meassurement(Target target) {
        this.target = target;
    }

    public double getDuration() {
        return duration;
    }

    public double getQuantity() {
        return quantity;
    }

    public Target getTarget() {
        return target;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}

