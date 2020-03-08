package com.sallylshi.maps;

import java.time.Duration;
import java.util.ArrayList;

public class ActivitySegment {
    enum ActivityType {
        WALKING,
        IN_SUBWAY,
        IN_BUS,
        STILL,
        IN_PASSENGER_VEHICLE,
        IN_TRAIN,
        IN_TRAM,
        CYCLING,
        RUNNING,
        MOTORCYCLING,
        IN_VEHICLE,
        FLYING,
        IN_FERRY,
        SKIING,
        SAILING;
    }

    enum Confidence {
        LOW, MEDIUM, HIGH;
    }

    private Location startLocation;
    private Location endLocation;
    private Duration duration;
    private long distance;
    private ActivityType activityType;
    private Confidence confidence;
    private ArrayList<Activity> activities;
    private ArrayList<Waypoint> waypointPath;
    private ArrayList<Point> simplifiedRawPath;
}
