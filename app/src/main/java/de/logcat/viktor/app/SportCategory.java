package de.logcat.viktor.app;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by 0 on 05.01.2018.
 */

public class SportCategory{
    private final String name;
    private final int id;
    private static ArrayList<SportCategory> categories = new ArrayList<SportCategory>();
    private final String unit;
    private Bitmap durationProgressDiagram, quantityProgressDiagram;

    static {
        initCategories();
    }

    public SportCategory(String name, String unit) {
        this.name = name;
        this.unit = unit;
        this.id = categories.size();
        categories.add(this);
    }

    public String getName(){
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getId(){
        return id;
    }

    public static SportCategory getCategoryById(int id) {
        for(int i = 0; i < categories.size(); i++)
            if(categories.get(i).getId() == id) return categories.get(i);
        return null;
    }

    public Bitmap getDurationProgressDiagram() {
        return durationProgressDiagram;
    }

    public Bitmap getQuantityProgressDiagram() {
        return quantityProgressDiagram;
    }

    static ArrayList<SportCategory> getAllCategories() {
        return categories;
    }

    static String[] getAllCategoryNames() {
        String[] categoryNames = new String[categories.size()];
        for(int i = 0; i < categories.size(); i++) {
            categoryNames[i] = categories.get(i).getName();
        }

        return categoryNames;
    }

    static void initCategories(){
        new SportCategory("Laufen", "km");
        new SportCategory("Liegestütze", "Stück");
        new SportCategory("Wandsitzen", null);
    }

    public String getQuantityQuestion() {
        return "Wie viele "+getUnit()+"?";
    }

    public boolean hasQuanitityParameter() {
        return unit != null;
    }

    public void setDurationProgressDiagram(Bitmap durationProgressDiagram) {
        this.durationProgressDiagram = durationProgressDiagram;
    }

    public void setQuantityProgressDiagram(Bitmap quantityProgressDiagram) {
        this.quantityProgressDiagram = quantityProgressDiagram;
    }
}
