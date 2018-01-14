package de.logcat.viktor.fittnessapp1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


/**
 * Created by 0 on 20.12.2017.
 */

public class Target implements Parcelable {

    private final SportCategory category;
    private String duration;
    private double quantity;
    private final int id;

    public int describeContents() {
        return 0;
    }


    /**Constructor of target**/
    public Target(SportCategory category, String mDuration, double mQuantity, int id) {
        this.category = category;
        duration = mDuration;
        quantity = mQuantity;
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

    public void  setDuration(String duration) {
        this.duration =  duration;
    }


    public String getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }

    /**
     * Storing the targetdata to Parcel object
     * **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]  {this.id+"", this.category.getId()+"", this.duration+"", this.quantity+"" });
    }

    /**
     * Retriving Target Data from Parcel object
     * This constructor is invoked by th emethod createFromParcel(Parcel source) of
     * the object CREATOR
     * */
    private Target(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);

        this.id = Integer.parseInt(data[0]);
        this.category = SportCategory.getCategoryById(Integer.parseInt(data[1]));
        this.duration = data[2];
        this.quantity = Double.parseDouble(data[3]);
    }

    public static final Parcelable.Creator<Target> CREATOR = new Parcelable.Creator<Target>() {
        @Override
        public Target createFromParcel(Parcel source) {
            return new Target(source);
        }

        @Override
        public Target[] newArray(int size) {
            return new Target[size];
        }
    };




}

