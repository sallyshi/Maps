package com.sallylshi.maps;

import java.time.Duration;
import java.util.ArrayList;

public class PlaceVisit {
    enum EditConfirmationStatus {
        NOT_CONFIRMED,
        CONFIRMED;
    }

    private Location location;
    private Duration duration;
    private String placeConfidence;
    private String centerLatE7;
    private String centerLngE7;
    private double visitConfidence;
    private ArrayList<Location> otherCandidateLocations;
    private EditConfirmationStatus editConfirmationStatus;

    PlaceVisit(Location location, Duration duration, String placeConfidence, String centerLatE7, String centerLngE7, double visitConfidence, ArrayList<Location> otherCandidateLocations, EditConfirmationStatus editConfirmationStatus) {
        this.location = location;
        this.duration = duration;
        this.placeConfidence = placeConfidence;
        this.centerLatE7 = centerLatE7;
        this.centerLngE7 = centerLngE7;
        this.visitConfidence = visitConfidence;
        this.otherCandidateLocations = otherCandidateLocations;
        this.editConfirmationStatus = editConfirmationStatus;
    }

}
