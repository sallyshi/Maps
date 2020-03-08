package com.sallylshi.maps;

public class Point {
    private long latE7;
    private long lngE7;
    private long timestampMs;
    private int accuracyMeters;

    Point(long latE7, long lngE7, long timestampMs, int accuracyMeters) {
        this.latE7 = latE7;
        this.lngE7 = lngE7;
        this.timestampMs = timestampMs;
        this.accuracyMeters = accuracyMeters;
    }
}
