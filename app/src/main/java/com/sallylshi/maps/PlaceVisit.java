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

}
