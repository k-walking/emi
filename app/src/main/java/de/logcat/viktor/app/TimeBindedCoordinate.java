package de.logcat.viktor.app;

public class TimeBindedCoordinate {

    private final double longitude, latitude;
    private final int timestamp;

    public TimeBindedCoordinate(double longitude, double latitude, int timestamp) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getTimestamp() {
        return timestamp;
    }
}

