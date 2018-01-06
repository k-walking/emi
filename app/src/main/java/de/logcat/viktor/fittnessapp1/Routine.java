package de.logcat.viktor.fittnessapp1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by 0 on 05.01.2018.
 */

public class Routine implements Parcelable {
    private final String name = null;
    private final ArrayList<Target> routineTargets = new ArrayList<>();
    private static ArrayList<Routine> routines = new ArrayList<>();
    private final int id = 0;


    public void addTarget(Target target) {
        routineTargets.add(target);
    }

    public void removeTarget(Target target){
        routineTargets.remove(target);
    }

    public Routine(String routineName, int id) {
        //this.routineName = routineName;
        //this.id = id;
        routines.add(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Target> getAllTargets(){
        return routineTargets;
    }

    static ArrayList<Routine> getAllRoutines() {
        return routines;
    }


    //static void initCategories()
    protected Routine(Parcel in) {
    }

    public static final Creator<Routine> CREATOR = new Creator<Routine>() {
        @Override
        public Routine createFromParcel(Parcel in) {
            return new Routine(in);
        }

        @Override
        public Routine[] newArray(int size) {
            return new Routine[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
