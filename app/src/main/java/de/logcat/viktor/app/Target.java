package de.logcat.viktor.app;

import android.os.Parcel;
import android.os.Parcelable;

public class Target {

    private final SportCategory category;
    private double duration = 0;
    private double quantity = 0;
    private final int id;

    /**Constructor of target**/
    public Target(SportCategory category) {
        this.category = category;
        int highestId = 0;

        for(int i = 0; i < Routine.getAllRoutines().size(); i++){
            for(int j = 0; j < Routine.getAllRoutines().get(i).getAllTargets().size(); j++)
                highestId = Math.max(highestId, Routine.getAllRoutines().get(i).getAllTargets().get(j).getId());
        }

        id = highestId+1;
    }

    public Target(String s) {
        String[] properties = s.split("\\,");
        id  = Integer.parseInt(properties[0]);
        category = SportCategory.getAllCategories().get(Integer.parseInt(properties[1]));
        duration = Double.parseDouble(properties[2]);
        quantity = Double.parseDouble(properties[3]);
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

    @Override
    public String toString() {
        return id+","+getCategory().getId()+","+getDuration()+","+getQuantity();
    }
}

