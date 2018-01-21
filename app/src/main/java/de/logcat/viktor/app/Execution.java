package de.logcat.viktor.app;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Execution implements Parcelable {

    private static final ArrayList<Execution> allExecutions = new ArrayList<Execution>();

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

    private boolean isAlreadyExecuted;
    private final Routine routine;
    private final Date executionTime; //TODO
    private double duration;
    private double quantity;
    private final int id;

    public Execution(int routineId, Date executionTime){
        id = getNewId();
        routine = Routine.findRoutine(routineId);
        allExecutions.add(this);
        this.executionTime = executionTime;
    }

    Execution(Parcel in) {
        id = in.readInt();
        isAlreadyExecuted = in.readByte() != 0;
        routine = in.readParcelable(Routine.class.getClassLoader());
        duration = in.readDouble();
        quantity = in.readDouble();
        executionTime = new Date(in.readLong());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (isAlreadyExecuted ? 1 : 0));
        dest.writeParcelable(routine, flags);
        dest.writeDouble(duration);
        dest.writeDouble(quantity);
        dest.writeLong(executionTime.getTime());
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
