package com.sallylshi.maps;

public class Activity {
    private ActivitySegment.ActivityType activityType;
    private double probability;

    public Activity(ActivitySegment.ActivityType activityType, double probability) {
        this.activityType = activityType;
        this.probability = probability;
    }
}
