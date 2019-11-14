package com.example.miete_berlin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class finish extends AppCompatActivity {

    private TextView hoch;
    private TextView titel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");

        titel = findViewById(R.id.titel);
        hoch = findViewById(R.id.hoch);
        titel.setTypeface(custom_font);
        hoch.setTypeface(custom_font);

        double max_miete = ((MietDaten) getApplication()).getMaxMietkosten();
        String text = "";
        if(max_miete == -1){
            text = "Wir konnten noch nicht bestimmen, ob ihre Miete zu hoch ist, da uns noch Angaben fehlen.";
        } else {
            double miete = ((MietDaten) getApplication()).getMietkosten();
            if((max_miete * 1.2) < miete){
                double sparen = (miete - max_miete);
                sparen -= sparen % 0.01;
                sparen *= 100d;
                sparen = ((int)sparen) / 100d;
                text = "Ihr Miete ist zu hoch!\nDie Maximale obermiete für ihre Wohnung beträgt " +
                        max_miete + "€ und ihr Miete von " + miete + "€ überschreitet diese mit über 20%.\n" +
                        "Das heißt sie können klagen und sich monatlich bis zu " + sparen + "€ sparen.";
            } else {
                text = "Ihr Miete ist nicht zu hoch!\nDie Maximale obermiete für ihre Wohnung beträgt " +
                        max_miete + "€ und ihr Miete von " + miete + "€ überschreitet diese nicht mit über 20%.\n";
            }
        }
        hoch.setText(text);
    }
}
