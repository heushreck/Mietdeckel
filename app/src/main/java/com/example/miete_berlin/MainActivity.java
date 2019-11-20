package com.example.miete_berlin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button angaben;
    private Button pdf;
    private TextView intro;
    private TextView titel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        if(height < 1300){
            setContentView(R.layout.activity_main_small);
        } else {
            setContentView(R.layout.activity_main);
        }
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");

        start = findViewById(R.id.start);
        angaben = findViewById(R.id.angaben);
        intro = findViewById(R.id.intro);
        titel = findViewById(R.id.titel);
        pdf = findViewById(R.id.pdf);
        start.setTypeface(custom_font);
        angaben.setTypeface(custom_font);
        intro.setTypeface(custom_font);
        titel.setTypeface(custom_font);
        pdf.setTypeface(custom_font);

        String text = "Ab Herbst 2020 sollen in Berlin „Wuchermieten“ abgesenkt werden können. Berechne jetzt, ob du Anspruch auf eine Mietabsenkung hättest und wie viel du monatlich sparen könntest.\n" +
                "Das Ergebnis dient dir als Orientierung und ist ohne Gewähr.";
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

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent theIntent = new Intent(MainActivity.this, pdf.class);
                startActivity(theIntent);
            }
        });

        angaben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent theIntent = new Intent(MainActivity.this, finish.class);
                startActivity(theIntent);
            }
        });

    }
}
