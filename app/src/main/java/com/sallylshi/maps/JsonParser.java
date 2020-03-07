package com.sallylshi.maps;

import android.util.JsonReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

class JsonParser {

    private PlaceVisit parsePlaceVisit(JsonReader reader) {

        return new PlaceVisit();
    }

    List<PlaceVisit> read(JsonReader reader) throws IOException, ParseException {

        List<PlaceVisit> placeVisitList = new ArrayList<>();

        reader.beginObject();
        while (reader.hasNext()) {
            reader.nextName();
            reader.beginArray();

            while (reader.hasNext()) {
                reader.beginObject();
                placeVisitList.add(parsePlaceVisit(reader));
                reader.endObject();
            }
            reader.endArray();
        }
        return placeVisitList;
    }
}