package de.logcat.viktor.app;

import android.os.Parcel;
import android.os.Parcelable;

public class Target {

    private final SportCategory category;
    private double duration = 0;
    private double quantity = 0;
    private final int id;

    /**Constructor of target**/
    public Target(SportCategory category, double duration, double quantity, int id) {
        this.category = category;
        duration = duration;
        quantity = quantity;
        this.id = id;
    }

    public SportCategory getCategory(){
        return category;
    }

    public double getQuantity() {
        return quantity;
    }

    public void  setQuantity(double quantity) {
        this.quantity =  quantity;
    }

    public void  setDuration(double duration) {
        this.duration =  duration;
    }

    public double getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }
}

