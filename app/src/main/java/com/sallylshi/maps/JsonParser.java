package com.sallylshi.maps;

import android.os.Build;
import android.util.JsonReader;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class JsonParser {
    Location location;
    Duration duration;
    String placeConfidence;
    String centerLatE7;
    String centerLngE7;
    double visitConfidence;
    ArrayList<Location> otherCandidateLocations;
    PlaceVisit.EditConfirmationStatus editConfirmationStatus;

    @RequiresApi(api = Build.VERSION_CODES.O)
    String read(JsonReader reader) throws IOException, ParseException {

        reader.beginObject();
        reader.nextName();
        reader.beginArray();
        reader.beginObject();
        reader.nextName();

        //parse PlaceVisit
        PlaceVisit.PlaceConfidence placeConfidence = null;
        long centerLatE7 = 0;
        long centerLngE7 = 0;
        int visitConfidence = 0;
        ArrayList<Location> otherCandidateLocations = new ArrayList<>();
        PlaceVisit.EditConfirmationStatus editConfirmationStatus = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String n = reader.nextName();
            switch (n) {
                case "location":
                    parseLocation(reader);
                    break;
                case "duration":
                    parseDuration(reader);
                    break;
                case "placeConfidence":
                    placeConfidence = Enum.valueOf(PlaceVisit.PlaceConfidence.class,
                            reader.nextString());
                    break;
                case "centerLatE7":
                    centerLatE7 = reader.nextLong();
                    break;
                case "centerLngE7":
                    centerLngE7 = reader.nextLong();
                    break;
                case "visitConfidence":
                    visitConfidence = reader.nextInt();
                    break;
                case "otherCandidateLocations":
                    reader.beginArray();
                    while (reader.hasNext()) {
                        otherCandidateLocations.add(parseLocation(reader));
                    }
                    reader.endArray();
                    break;
                case "editConfirmationStatus":
                    Enum.valueOf(PlaceVisit.EditConfirmationStatus.class,
                            reader.nextString());
                    break;
                default:
                    Log.e("JsonParser", "Parsing Visit couldn't find name. Went into default.");
                    break;
            }
        }
        return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Duration parseDuration(JsonReader reader) throws IOException {
        reader.beginObject();
        long startTimestampMs = 0;
        long endTimestampMs = 0;
        while (reader.hasNext()) {
            String n = reader.nextName();
            switch (n) {
                case "startTimestampMs":
                    startTimestampMs = reader.nextLong();
                    break;
                case "endTimestampMs":
                    endTimestampMs = reader.nextLong();
                    break;
                default:
                    Log.e("JsonParser", "Parsing Event couldn't find name. Went into default.");
                    break;
            }
        }
        reader.endObject();
        return Duration.between(Instant.ofEpochMilli(startTimestampMs),
                Instant.ofEpochMilli(endTimestampMs));
    }

    private Location parseLocation(JsonReader reader) throws IOException {
        reader.beginObject();
        long latitudeE7 = 0;
        long longitudeE7 = 0;
        String placeId = "";
        String address = "";
        String name = "";
        SourceInfo sourceInfo = null;
        double locationConfidence = 0;

        while (reader.hasNext()) {
            String n = reader.nextName();
            switch (n) {
                case "latitudeE7":
                    latitudeE7 = reader.nextLong();
                    break;
                case "longitudeE7":
                    longitudeE7 = reader.nextLong();
                    break;
                case "placeId":
                    placeId = reader.nextString();
                    break;
                case "address":
                    address = reader.nextString();
                    break;
                case "name":
                    name = reader.nextString();
                    break;
                case "sourceInfo":
                    sourceInfo = parseSourceInfo(reader);
                    break;
                case "locationConfidence":
                    locationConfidence = reader.nextDouble();
                    break;
                default:
                    Log.e("JsonParser", "Parsing Event couldn't find name. Went into default.");
                    break;
            }
        }
        reader.endObject();
        return new Location(latitudeE7, longitudeE7, placeId, address, name, sourceInfo,
                locationConfidence);
    }

    private SourceInfo parseSourceInfo(JsonReader reader) throws IOException {
        SourceInfo sourceInfo = null;
        reader.beginObject();
        reader.nextName();
        sourceInfo = new SourceInfo(reader.nextLong());
        reader.endObject();
        return sourceInfo;
    }
}