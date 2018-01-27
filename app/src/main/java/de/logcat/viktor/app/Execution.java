package de.logcat.viktor.app;

import android.content.Context;
import java.util.ArrayList;
import java.util.Date;

public class Execution {

    private static ArrayList<Execution> allExecutions = new ArrayList<Execution>();

    private final Routine routine;
    private final Date executionTime;
    private Meassurement[] meassurements;
    private final int id;

    public Execution(int routineId, Date executionTime){
        id = getNewId();
        routine = Routine.findRoutine(routineId);
        allExecutions.add(this);
        this.executionTime = executionTime;
        meassurements = new Meassurement[routine.getAllTargets().size()];

        for(int i = 0; i < meassurements.length; i++)
            meassurements[i] = new Meassurement(this, routine.getAllTargets().get(i));
    }

    public Execution(String s) {
        String[] properties = s.split("\\,");
        id  = Integer.parseInt(properties[0]);
        routine  = Routine.findRoutine(Integer.parseInt(properties[1]));
        executionTime  = new Date(Long.parseLong(properties[2]));
        meassurements = new Meassurement[Integer.parseInt(properties[3])];
    }

    public static Execution findExecution(int id) {
        Execution execution;
        for(int i = 0; i < Execution.getAllExecutions().size(); i++)
            if((execution = Execution.getAllExecutions().get(i)).getId() == id)
                return execution;
        return null;
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

    @Override
    public String toString() {
        return id+","+routine.getId()+","+executionTime.getTime()+","+getAllMeassurements().length;
    }

    public static void loadAllExecutions(Context context){
        Persistence persistence = new Persistence(context);
        allExecutions = persistence.loadExecutions();
        persistence.loadMeasuremets();
    }

    public void initializeMeasurement(Meassurement meassurement) {
        for(int i = 0; i < meassurements.length; i++)
            if(meassurements[i] == null) {
                meassurements[i] = meassurement;
                return;
            }
    }
}
