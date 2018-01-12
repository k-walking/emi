package de.logcat.viktor.fittnessapp1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by 0 on 05.01.2018.
 */

public class SportCategory implements Parcelable{
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

    protected SportCategory(Parcel in) {
        name = in.readString();
        id = in.readInt();
        unit = in.readString();


    }

    public static final Creator<SportCategory> CREATOR = new Creator<SportCategory>() {
        @Override
        public SportCategory createFromParcel(Parcel in) {
            return new SportCategory(in);
        }

        @Override
        public SportCategory[] newArray(int size) {
            return new SportCategory[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeString(unit);
    }
}
