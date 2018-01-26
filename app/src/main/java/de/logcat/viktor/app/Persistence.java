package de.logcat.viktor.app;

import android.content.Context;
import android.media.Image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Persistence {
    private final Context context;
    private final ArrayList<Target> allTargets = new ArrayList<Target>();
    private static final String FILE_ROUTINES = "routines.txt";
    private static final String FILE_TARGETS = "targets.txt";
    private static final String FILE_EXECUTIONS = "executions.txt";

    public Persistence(Context context){
        this.context = context;
    }
    // === save ===

    public void saveRoutines() {
        saveTargets();
        String data = "";
        for(int i = 0; i < Routine.getAllRoutines().size(); i++) {
            data += (i > 0 ? ";" : "")+Routine.getAllRoutines().get(i).toString();
        }
        writeToFile( FILE_ROUTINES, data);

    }

    private void saveTargets() {
        String data = "";
        for(int i = 0; i < Routine.getAllRoutines().size(); i++) {
            Routine routine = Routine.getAllRoutines().get(i);
            for(int j = 0; j < routine.getAllTargets().size(); j++) {
                data += (j > 0 ? ";" : "")+routine.getAllTargets().get(j).toString();
            }

        }
        writeToFile( FILE_TARGETS, data);
    }

    public void saveExecutions() {
        saveMeassurements();
        String data = "";
        for(int i = 0; i < Execution.getAllExecutions().size(); i++) {
            data += (i > 0 ? ";" : "")+Execution.getAllExecutions().get(i).toString();
        }
        writeToFile( FILE_EXECUTIONS, data);
    }

    private void saveMeassurements() {
        //TODO
    }

    public void saveProgressDiagrams(SportCategory category, Image durationProgressDiagram, Image quantityProgressDiagram) {
        // TODO
    }

    // === load ====

    public ArrayList<Routine> loadRoutines() {
        loadTargets();
        ArrayList<Routine> allRoutines = new ArrayList<Routine>();
        String data = readFromFile(FILE_ROUTINES);
        String[] routineStrings = data.split("\\;");
        for(int i = 0; i < routineStrings.length; i++) {
            if(routineStrings[i].length() > 0) {
                Routine routine = new Routine(routineStrings[i], allTargets);
                allRoutines.add(routine);
            }
        }
        return allRoutines;
    }

    public void loadTargets() {
        String data = readFromFile(FILE_TARGETS);
        String[] targetStrings = data.split("\\;");
        for(int i = 0; i < targetStrings.length; i++) if(targetStrings[i].length() > 0) allTargets.add(new Target (targetStrings[i]));
    }

    public ArrayList<Execution> loadExecutions() {
        ArrayList<Execution> allExecutions = new ArrayList<Execution>();
        String data = readFromFile(FILE_EXECUTIONS);
        String[] executionStrings = data.split("\\;");
        for(int i = 0; i < executionStrings.length; i++) {
            if(executionStrings[i].length() > 0) {
                Execution execution = new Execution(executionStrings[i]);
                allExecutions.add(execution);
            }
        }
        return allExecutions;
    }

    private void writeToFile(String filename, String data){
        try {
            FileOutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            out.write(data.getBytes());
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String readFromFile(String filename){
        //if(!new File(filename).exists()) return "";
        StringBuilder sb = new StringBuilder();

        try {
            FileInputStream in = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine()) != null) sb.append(line);
        } catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }
}