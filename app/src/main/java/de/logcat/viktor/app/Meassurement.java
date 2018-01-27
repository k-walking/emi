package de.logcat.viktor.app;

public class Meassurement {
    private long duration = 0;
    private double quantity = 0;
    private final Target target;
    private long timeStarted = 0;
    private final Execution execution;

    public Meassurement(Execution execution, Target target) {
        this.execution = execution;
        this.target = target;
    }

    public Meassurement(String s) {
        String[] properties = s.split("\\,");
        execution  = Execution.findExecution(Integer.parseInt(properties[0]));
        target  = Target.findTarget(Integer.parseInt(properties[1]));
        quantity = Double.parseDouble(properties[2]);
        duration = Long.parseLong(properties[3]);
        System.out.println(">>>>>>>>>>>>>>>>>>>M"+properties[1]);

        execution.initializeMeasurement(this);
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

    public Execution getExecution() { return execution; }

    @Override
    public String toString() {
        return execution.getId()+","+target.getId()+","+quantity+","+duration;
    }
}

