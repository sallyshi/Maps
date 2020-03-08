package com.sallylshi.maps;

import java.time.Duration;
import java.util.ArrayList;

class PlaceVisit {
    enum EditConfirmationStatus {
        NOT_CONFIRMED,
        CONFIRMED;
    }

    enum PlaceConfidence {
        LOW_CONFIDENCE,
        MEDIUM_CONFIDENCE,
        HIGH_CONFIDENCE;
    }

    Location location;
    Duration duration;
    PlaceConfidence placeConfidence;
    String centerLatE7;
    String centerLngE7;
    double visitConfidence;
    ArrayList<Location> otherCandidateLocations;
    EditConfirmationStatus editConfirmationStatus;

    PlaceVisit(Location location, Duration duration, PlaceConfidence placeConfidence, String centerLatE7,
               String centerLngE7, double visitConfidence,
               ArrayList<Location> otherCandidateLocations,
               EditConfirmationStatus editConfirmationStatus) {
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
