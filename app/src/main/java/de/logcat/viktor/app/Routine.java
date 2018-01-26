package de.logcat.viktor.app;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Routine{
    private static ArrayList<Routine> routines;

    private String name;
    private final ArrayList<Target> routineTargets = new ArrayList<>();
    private final int id;

    public static Routine findRoutine(int id) {
        Routine routine;
        for(int i = 0; i < Routine.getAllRoutines().size(); i++)
            if((routine = Routine.getAllRoutines().get(i)).getId() == id)
                return routine;
        return null;
    }

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

    public Routine(String s) {
        String[] properties = s.split("\\\\,");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+properties[0]);
        id  = Integer.parseInt(properties[0]);
        name = properties[1];
    }

    @Override
    public String toString() {
        String targets = "";
        for(int i = 0; i < routineTargets.size(); i++) targets += (i > 0 ? ";" : "")+routineTargets.get(i).getId();
        return id+","+name+",("+targets+")";
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

    public static void loadAllRoutines(Context context){
        routines = new Persistence(context).loadRoutines();
    }
}
