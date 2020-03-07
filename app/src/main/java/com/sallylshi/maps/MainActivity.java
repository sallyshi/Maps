package com.sallylshi.maps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 897897;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getJson();
    }

    public String parse(JsonReader reader) throws IOException {
        String output = "";
        String indent = "";
        int counter = 0;
        while (reader.peek() == JsonToken.END_ARRAY || reader.peek() == JsonToken.END_OBJECT || reader.hasNext()) {
            Log.i("parse", "" + reader.peek());
            switch (reader.peek()) {
                case BEGIN_ARRAY: {
                    reader.beginArray();
                    output += "[\n" + indent;
                    break;
                }
                case BEGIN_OBJECT: {
                    indent += "" + counter++;
                    reader.beginObject();
                    output += "{\n" + indent;
                    break;
                }
                case BOOLEAN: {
                    output += reader.nextBoolean() + "\n" + indent;
                    break;
                }
                case END_ARRAY: {
                    reader.endArray();
                    output += "]\n" + indent;
                    break;
                }
                case END_DOCUMENT: {
                    reader.close();
                    return output;
                }
                case END_OBJECT: {
                    counter--;
                    if (indent.length() > 0) {
                        indent = indent.substring(0, indent.length() - 1);
                    }
                    reader.endObject();
                    output += "}\n" + indent;
                    break;
                }
                case NAME: {
                    output += "\"" + reader.nextName() + "\": ";
                    break;
                }
                case NULL: {
                    reader.nextNull();
                    output += "null,\n" + indent;
                    break;
                }
                case NUMBER: {
                    output += reader.nextDouble() + ",\n" + indent;
                    break;
                }
                case STRING: {
                    output += reader.nextString() + ",\n" + indent;
                    break;
                }
            }
        }
        return output;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getJson() {
        new Thread( ()-> {
            try {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (shouldShowRequestPermissionRationale(
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // Explain to the user why we need to read the contacts
                    }

                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                    return;
                }

                FileInputStream Fin = new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/2020_MARCH.json");


                JsonReader reader = new JsonReader(new InputStreamReader(Fin));
                reader.setLenient(true);

                final String thisIsreallytheoutput = parse(reader);
                TextView view = findViewById(R.id.test);

                runOnUiThread(() -> view.setText(thisIsreallytheoutput));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
