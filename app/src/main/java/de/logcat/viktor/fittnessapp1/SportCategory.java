package de.logcat.viktor.fittnessapp1;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by 0 on 05.01.2018.
 */

public class SportCategory {
    private final String name;
    private final int id;
    private static ArrayList<SportCategory> categories = new ArrayList<SportCategory>();
    private final String unit;

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

    static ArrayList getAllCategories() {
        return categories;
    }

    static void initCategories(){
        new SportCategory("Laufen", "km");
        new SportCategory("Liegestütze", "Stück");
        new SportCategory("Wandsitzen", null);
    }
}
