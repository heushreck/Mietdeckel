package com.example.miete_berlin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button angaben;
    private TextView intro;
    private TextView titel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");

        start = findViewById(R.id.start);
        angaben = findViewById(R.id.angaben);
        intro = findViewById(R.id.intro);
        titel = findViewById(R.id.titel);
        start.setTypeface(custom_font);
        angaben.setTypeface(custom_font);
        intro.setTypeface(custom_font);
        titel.setTypeface(custom_font);

        String text = "Ab Herbst 2020 sollen in Berlin „Wuchermieten“ abgesenkt werden können. Berechne jetzt, ob du Anspruch auf eine Mietabsenkung hättest und wie viel du monatlich sparen könntest.\n" +
                "Das Ergebnis dient dir als Orientierung und ist ohne Gewähr. Deine eingegebenen Daten werden nicht gespeichert.";
        intro.setText(text);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent theIntent = new Intent(MainActivity.this, decide.class);
                theIntent.putExtra("stage", 0);
                theIntent.putExtra("gleichweiter", false);
                startActivity(theIntent);
            }
        });

        angaben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent theIntent = new Intent(MainActivity.this, pdf.class);
                startActivity(theIntent);
            }
        });

    }
}
