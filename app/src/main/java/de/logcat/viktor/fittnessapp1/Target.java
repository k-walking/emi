package de.logcat.viktor.fittnessapp1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


/**
 * Created by 0 on 20.12.2017.
 */

public class Target implements Parcelable {

    private final SportCategory category = null; //TODO parcelable constructor
    private final double duration;
    private final double quantity;
    private final int id;

    public int describeContents() {
        return 0;
    }


    /**Constructor of target**/
    public Target(SportCategory category, double mDuration, double mQuantity, int id) {
        //this.category = category;
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

    public double getDuration() {
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
       // wrtie Sportcaetegory
        dest.writeDouble(duration);
        dest.writeDouble(quantity);
        dest.writeInt(id);

    }

    /***
     * Retriving Target Data from Parcel object
     * This constructor is invoked by th emethod createFromParcel(Parcel source) of
     * the object CREATOR
     * */
    private Target(Parcel in) {
        //this.category = in.readString();
        this.duration = in.readDouble();
        this.quantity = in.readDouble();
        this.id = in.readInt();
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

