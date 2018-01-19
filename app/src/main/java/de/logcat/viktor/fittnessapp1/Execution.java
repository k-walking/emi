package de.logcat.viktor.fittnessapp1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 0 on 06.01.2018.
 */

public class Execution implements Parcelable{
    private static final ArrayList<Execution> allExecutions = new ArrayList<Execution>();
    private boolean isAlreadyExecutet;
    private final Routine routine;
    //private final Date executionTime; TODO
    private double mDuration;
    private double mQuantity;
    private final int id;


    public Execution(int routineId, String executionTime){
        Routine foundRountine = null;

        for(int i = 0; i < Routine.getAllRoutines().size(); i++) {
            if(Routine.getAllRoutines().get(i).getId() == routineId ) {
                foundRountine = Routine.getAllRoutines().get(i);

                break;
            }
        }
        int highestId = -1;

        for(int i = 0; i < allExecutions.size(); i++)
            highestId = Math.max(highestId, allExecutions.get(i).getId());

        id = highestId+1;
        allExecutions.add(this);
        routine = foundRountine;
        //this.executionTime = null;//TODO make Date
    }

    protected Execution(Parcel in) {
        isAlreadyExecutet = in.readByte() != 0;
        routine = in.readParcelable(Routine.class.getClassLoader());
        mDuration = in.readDouble();
        mQuantity = in.readDouble();
        id = in.readInt();
    }

    public static final Creator<Execution> CREATOR = new Creator<Execution>() {
        @Override
        public Execution createFromParcel(Parcel in) {
            return new Execution(in);
        }

        @Override
        public Execution[] newArray(int size) {
            return new Execution[size];
        }
    };

    public int getId(){
        return id;
    }

    public double getDuration() {
        return mDuration;
    }

    public double getmQuantity() {
        return mQuantity;
    }

    //public Date getExecutiontime() {
        //return executionTime;
    //}

    public Routine getRoutine() {
        return routine;
    }

    public void setDuration(double mDuration) {
        this.mDuration = mDuration;
    }

    public void setQuantity(double mQuantity) {
        this.mQuantity = mQuantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isAlreadyExecutet ? 1 : 0));
        dest.writeParcelable(routine, flags);
        dest.writeDouble(mDuration);
        dest.writeDouble(mQuantity);
        dest.writeInt(id);
    }
}
