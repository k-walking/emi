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

    public Routine(String s, ArrayList<Target> allTargets) {
        String[] properties = s.split("\\,");
        id  = Integer.parseInt(properties[0]);
        name = properties[1];
        String[] targetStrings = properties[2].split("\\|");
        for(int i = 0; i < targetStrings.length; i++) {
            for(int j = 0; j < allTargets.size(); j++) {
                if(Integer.parseInt(targetStrings[i]) == allTargets.get(j).getId()) {
                    addTarget(allTargets.get(j));
                }
            }
        }
    }

    @Override
    public String toString() {
        String targets = "";
        for(int i = 0; i < routineTargets.size(); i++) targets += (i > 0 ? "|" : "")+routineTargets.get(i).getId();
        return id+","+name+","+targets;
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
