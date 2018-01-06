package de.logcat.viktor.fittnessapp1;

import android.media.Image;

import java.util.ArrayList;

public class Persistence {

    // === save ===

    public static void save(Routine routine) {
        // TODO
    }

    public static void save(Execution execution) {
        // TODO
    }

    public static void saveProgressDiagrams(SportCategory category, Image durationProgressDiagram, Image quantityProgressDiagram) {
        // TODO
    }



    // === load ====

    public static ArrayList<Routine> loadRoutines() {
        return null; // TODO
    }

    public static ArrayList<Execution> loadExecutions() {
        return null; // TODO
    }



    // === delete ===

    public static void deleteRoutine(int id) {
        // TODO
    }

    public static void deleteTarget(int id) {
        // TODO
    }
}