package de.logcat.viktor.fittnessapp1;

import java.util.Date;

/**
 * Created by 0 on 06.01.2018.
 */

public class Execution {
    private boolean isAlreadyExecutet;
    private final Routine routine;
    private final Date executionTime;
    private double mDuration;
    private double mQuantity;


    public Execution(int routineId, String executionTime){
        Routine foundRountine = null;

        for(int i = 0; i < Routine.getAllRoutines().size(); i++) {
            if(Routine.getAllRoutines().get(i).getId() == routineId ) {
                foundRountine = Routine.getAllRoutines().get(i);

                break;
            }
        }

        routine = foundRountine;

        this.executionTime = null;//TODO make Date
    }

    public double getDuration() {
        return mDuration;
    }

    public double getmQuantity() {
        return mQuantity;
    }

    public Date getExecutiontime() {
        return executionTime;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setDuration(double mDuration) {
        this.mDuration = mDuration;
    }

    public void setQuantity(double mQuantity) {
        this.mQuantity = mQuantity;
    }



}
