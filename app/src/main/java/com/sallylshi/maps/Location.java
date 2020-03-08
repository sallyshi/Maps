package com.sallylshi.maps;

public class Location {
    private long latitudeE7;
    private long longitudeE7;
    private String placeId;
    private String address;
    private String name;
    private SourceInfo sourceInfo;
    private double locationConfidence;

    public Location(long latitudeE7, long longitudeE7, String placeId, String address, String name, SourceInfo sourceInfo, double locationConfidence) {
        this.latitudeE7 = latitudeE7;
        this.longitudeE7 = longitudeE7;
        this.placeId = placeId;
        this.address = address;
        this.name = name;
        this.sourceInfo = sourceInfo;
        this.locationConfidence = locationConfidence;
    }
}
