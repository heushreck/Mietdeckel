package com.example.miete_berlin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class decide extends AppCompatActivity {

    private int stage = 0;
    private boolean gleichweiter = false;

    private Button ja;
    private Button nein;
    private Button wohnlage;
    private Button help;
    private EditText antwort;
    private TextView frage;
    private TextView titel;
    private TextView step;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b!=null)
        {
            stage = (int) b.get("stage");
            gleichweiter = (boolean) b.get("gleichweiter");
        }
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");


        ja = findViewById(R.id.ja);
        nein = findViewById(R.id.nein);
        antwort = findViewById(R.id.antwort_feld);
        frage = findViewById(R.id.frage);
        wohnlage = findViewById(R.id.wohnlage);
        finish = findViewById(R.id.finish);
        titel = findViewById(R.id.titel);
        step = findViewById(R.id.step);
        help = findViewById(R.id.help);
        ja.setTypeface(custom_font);
        nein.setTypeface(custom_font);
        antwort.setTypeface(custom_font);
        frage.setTypeface(custom_font);
        wohnlage.setTypeface(custom_font);
        titel.setTypeface(custom_font);
        step.setTypeface(custom_font);
        help.setTypeface(custom_font);

        setUp();

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text;
                int duration = Toast.LENGTH_LONG;
                Toast toast;
                switch (stage) {
                    case 0:
                        Uri uri = Uri.parse("http://fbinter.stadt-berlin.de/fb/index.jsp?loginkey=showAreaSelection&mapId=k_wohnlagenadr2019@senstadt&areaSelection=address"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 5:
                        text = "Nach dem Berliner Mietspiegel sind unter einer Sammelheizung alle Heizungsarten zu verstehen, bei denen die Wärme- und Energieerzeugung von einer zentralen Stelle aus geschieht. Eine Etagen- oder Wohnungsheizung (Gas, Öl, Elektro), die sämtliche Wohnräume angemessen erwärmt, ist einer Sammelheizung gleichzusetzen.";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        break;
                    case 10:
                        text = "Als Sanitäteinrichtung bezeichnet man eine Toilette.";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        break;
                    case 12:
                        text = "Der Energieverbrauchskennwert bzw. Stromverbrauchskennwert gibt den gemessenen spezifischen Energieverbrauch in Kilowattstunden pro Jahr und m² Fläche (Abk. kWh/(a m²)) eines Gebäudes wieder und soll das Gebäude energetisch bewerten. Der Kennwert gilt immer für das Gebäude als Ganzes.";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        break;

                }
            }
        });

        antwort.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if(stage == 3){
                        View view = decide.this.getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                    finish_event();
                    return true;
                }
                return false;
            }
        });

        ja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (stage){
                    case 0:
                        ((MietDaten) getApplication()).setWohnlage(1);
                        break;
                    case 4:
                        ((MietDaten) getApplication()).setSozialwohnung(true);
                        break;
                    case 5:
                        ((MietDaten) getApplication()).setSammelheizung(true);
                        break;
                    case 6:
                        ((MietDaten) getApplication()).setBad(true);
                        break;
                    case 7:
                        ((MietDaten) getApplication()).setMehr_als_zwei_wohnungen(true);
                        break;
                    case 8:
                        ((MietDaten) getApplication()).setAufzug(true);
                        break;
                    case 9:
                        ((MietDaten) getApplication()).setEinbauküche(true);
                        break;
                    case 10:
                        ((MietDaten) getApplication()).setSanitaereinrichtung(true);
                        break;
                    case 11:
                        ((MietDaten) getApplication()).setBodenbelag(true);
                        break;
                    case 12:
                        ((MietDaten) getApplication()).setEnergiekennwert(true);
                        break;
                }
                if(stage == 12 || gleichweiter){
                    Intent theIntent = new Intent(decide.this, finish.class);
                    startActivity(theIntent);
                } else {
                    stage++;
                    setUp();
                }
            }
        });

        nein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (stage){
                    case 0:
                        ((MietDaten) getApplication()).setWohnlage(3);
                        break;
                    case 4:
                        ((MietDaten) getApplication()).setSozialwohnung(false);
                        break;
                    case 5:
                        ((MietDaten) getApplication()).setSammelheizung(false);
                        break;
                    case 6:
                        ((MietDaten) getApplication()).setBad(false);
                        break;
                    case 7:
                        ((MietDaten) getApplication()).setMehr_als_zwei_wohnungen(false);
                        break;
                    case 8:
                        ((MietDaten) getApplication()).setAufzug(false);
                        break;
                    case 9:
                        ((MietDaten) getApplication()).setEinbauküche(false);
                        break;
                    case 10:
                        ((MietDaten) getApplication()).setSanitaereinrichtung(false);
                        break;
                    case 11:
                        ((MietDaten) getApplication()).setBodenbelag(false);
                        break;
                    case 12:
                        ((MietDaten) getApplication()).setEnergiekennwert(false);
                        break;
                }
                if(stage == 12 || gleichweiter){
                    Intent theIntent = new Intent(decide.this, finish.class);
                    startActivity(theIntent);
                } else {
                    stage++;
                    setUp();
                }
            }
        });

        wohnlage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (stage){
                    case 0:
                        ((MietDaten) getApplication()).setWohnlage(2);
                        break;
                }
                if(stage == 12 || gleichweiter){
                    Intent theIntent = new Intent(decide.this, finish.class);
                    startActivity(theIntent);
                } else {
                    stage++;
                    setUp();
                }
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_event();
            }
        });
    }

    private void finish_event() {
        if(antwort.getText().toString().isEmpty()){
            CharSequence text = "Die Zahl kann nicht leer sein!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        } else {
            switch (stage){
                case 1:
                    ((MietDaten) getApplication()).setMietkosten(Double.parseDouble(antwort.getText().toString()));
                    stage++;
                    setUp();
                    break;
                case 2:
                    ((MietDaten) getApplication()).setQm(Double.parseDouble(antwort.getText().toString()));
                    stage++;
                    setUp();
                    break;
                case 3:
                    int jahr = Integer.parseInt(antwort.getText().toString());
                    ((MietDaten) getApplication()).setJahr(3);
                    stage++;
                    setUp();
                    break;
            }
        }
    }

    private void help_setUp(String frage_string, int ja_int, String ja_string, int nein_int, String nein_string, int wohnlage_int, String wohnlage_string, int antwort_int, String antwort_string, int finish_int, int help_int, String help_string){
        step.setText("Schritt " + (stage+1) + " von 13");
        frage.setText(frage_string);
        ja.setVisibility(ja_int);
        ja.setText(ja_string);
        nein.setVisibility(nein_int);
        nein.setText(nein_string);
        wohnlage.setVisibility(wohnlage_int);
        wohnlage.setText(wohnlage_string);
        antwort.setVisibility(antwort_int);
        antwort.setHint(antwort_string);
        antwort.setText("");
        finish.setVisibility(finish_int);
        help.setVisibility(help_int);
        help.setText(help_string);
    }

    private void setUp() {
        switch (stage){
            case 0:
                help_setUp("Welche Wohnlage haben sie?",0,"einfach",0,"gut",0,"mittel",4,"", 4, 0, "Welche Wohnlage habe ich?");
                break;
            case 1:
                help_setUp("Wie hoch ist deine Nettokaltmiete?",4,"1",4,"3",4,"2",0,"600", 0, 4, "");
                break;
            case 2:
                help_setUp("Wie gross ist dein Wohnraum (in qm)?",4,"1",4,"3",4,"2",0,"70", 0, 4, "");
                break;
            case 3:
                help_setUp("Wann wurde dein Haus gebaut? Wenn du das Baujahr nicht genau kennst, schätze es.",4,"1",4,"3",4,"2",0,"1995", 0, 4, "");
                break;
            case 4:
                help_setUp("Ist deine Wohnung eine Sozialwohnung?",0,"Ja",0,"Nein",4,"2",4,"", 4, 4, "");
                break;
            case 5:
                help_setUp("Hat meine Wohnung eine Sammelheizung?",0,"Ja",0,"Nein",4,"2",4,"", 4, 0, "Was ist eine Sammelheizung?");
                break;
            case 6:
                help_setUp("Hat meine Wohnung ein Bad?",0,"Ja",0,"Nein",4,"2",4,"", 4, 4, "");
                break;
            case 7:
                help_setUp("Gibt es in meinem Haus mehr als 2 Wohnungen?",0,"Ja",0,"Nein",4,"2",4,"", 4, 4, "");
                break;
            case 8:
                help_setUp("Gibt es in meinem Haus einen Personenaufzug?",0,"Ja",0,"Nein",4,"2",4,"", 4, 4, "");
                break;
            case 9:
                help_setUp("Hat meine Wohnung eine Einbauküche?",0,"Ja",0,"Nein",4,"2",4,"", 4, 4, "");
                break;
            case 10:
                help_setUp("Gibt es in meiner Wohnung eine hochwertige Sanitaereinrichtung?",0,"Ja",0,"Nein",4,"2",4,"", 4, 0, "Was ist eine Sanitäteinrichtung?");
                break;
            case 11:
                help_setUp("Gibt es in meiner Wohnung einen hochwertige Bodenbelag?",0,"Ja",0,"Nein",4,"2",4,"", 4, 4, "");
                break;
            case 12:
                help_setUp("Hat mein Haus einen Energiekennwert von mehr als 120kWh/m2a?",0,"Ja",0,"Nein",4,"2",4,"", 4, 0, "Wo finde ich meinen Energiekennwert?");
                break;
        }
    }
}
