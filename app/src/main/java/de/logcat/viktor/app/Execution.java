package de.logcat.viktor.app;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Execution {

    private static final ArrayList<Execution> allExecutions = new ArrayList<Execution>();

    private boolean isAlreadyExecuted;
    private final Routine routine;
    private final Date executionTime; //TODO
    private Meassurement[] meassurements;
    private final int id;

    public Execution(int routineId, Date executionTime){
        id = getNewId();
        routine = Routine.findRoutine(routineId);
        allExecutions.add(this);
        this.executionTime = executionTime;
        meassurements = new Meassurement[routine.getAllTargets().size()];

        for(int i = 0; i < meassurements.length; i++)
            meassurements[i] = new Meassurement(routine.getAllTargets().get(i));
    }

    public static ArrayList<Execution> getAllExecutions() {
        return allExecutions;
    }

    private static int getNewId() {
        int highestId = -1;
        for(int i = 0; i < allExecutions.size(); i++)
            highestId = Math.max(highestId, allExecutions.get(i).getId());
        return highestId+1;
    }

    public int getId() {
        return id;
    }

    public Date getExecutiontime() {
        return executionTime;
    }

    public boolean isOnDate(Date date){
        return date.getYear() == executionTime.getYear() &&
                date.getMonth() == executionTime.getMonth() &&
                date.getDate() == executionTime.getDate();
    }

    public Routine getRoutine() {
        return routine;
    }

   public Meassurement[] getAllMeassurements(){
        return meassurements ;
   }
}
