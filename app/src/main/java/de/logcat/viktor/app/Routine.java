package de.logcat.viktor.app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by 0 on 05.01.2018.
 */

public class Routine implements Parcelable {
    private String name;
    private final ArrayList<Target> routineTargets = new ArrayList<>();
    private static ArrayList<Routine> routines = Persistence.loadRoutines();;
    private final int id;

    public void addTarget(Target target) {
        routineTargets.add(target);
    }

    public void removeTarget(Target target){
        routineTargets.remove(target);
    }

    public Routine() {
        int highestId = 0;

        for(int i = 0; i < routines.size(); i++)
            highestId = Math.max(highestId, routines.get(i).getId());

        id = highestId+1;

        routines.add(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Target> getAllTargets(){
        return routineTargets;
    }

    static ArrayList<Routine> getAllRoutines() {
        return routines;
    }


    //static void initCategories()
    protected Routine(Parcel in) {
        String[] data = new String[1];
        in.readStringArray(data);

        this.id = Integer.parseInt(data[0]);
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
        dest.writeStringArray(new String[]  {this.id+""});
    }
}
