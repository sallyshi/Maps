package com.sallylshi.maps;

public class Activity {
    ActivitySegment.ActivityType activityType;
    double probability;

    public Activity(ActivitySegment.ActivityType activityType, double probability) {
        this.activityType = activityType;
        this.probability = probability;
    }
}
