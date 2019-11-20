package com.example.miete_berlin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
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
    private ConstraintLayout mConstraintLayout;
    private ConstraintSet mConstraintSet = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        if(height < 1300){
            setContentView(R.layout.activity_decide_small);
        } else {
            setContentView(R.layout.activity_decide);
        }
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b!=null)
        {
            stage = (int) b.get("stage");
            gleichweiter = (boolean) b.get("gleichweiter");
        }
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");


        mConstraintLayout = findViewById(R.id.decide_constraint_layout);
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
                String text = "";
                String dialog_titel = "";
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
                        dialog_titel = "Sammelheizung";
                        break;
                    case 10:
                        text = "Als Sanitäteinrichtung bezeichnet man eine Toilette.";
                        dialog_titel = "Sanitäteinrichtung";
                        break;
                    case 12:
                        text = "Der Energieverbrauchskennwert bzw. Stromverbrauchskennwert gibt den gemessenen spezifischen Energieverbrauch in Kilowattstunden pro Jahr und m² Fläche (Abk. kWh/(a m²)) eines Gebäudes wieder und soll das Gebäude energetisch bewerten. Der Kennwert gilt immer für das Gebäude als Ganzes.";
                        dialog_titel = "Energieverbrauchskennwert";
                        break;

                }
                if(!text.isEmpty()){
                    CustomDialogClass cdd=new CustomDialogClass(decide.this, text, dialog_titel);
                    cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    cdd.show();
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
                        ((MietDaten) getApplication()).setSozialwohnung(1);
                        break;
                    case 5:
                        ((MietDaten) getApplication()).setSammelheizung(1);
                        break;
                    case 6:
                        ((MietDaten) getApplication()).setBad(1);
                        break;
                    case 7:
                        ((MietDaten) getApplication()).setMehr_als_zwei_wohnungen(1);
                        break;
                    case 8:
                        ((MietDaten) getApplication()).setAufzug(1);
                        break;
                    case 9:
                        ((MietDaten) getApplication()).setEinbauküche(1);
                        break;
                    case 10:
                        ((MietDaten) getApplication()).setSanitaereinrichtung(1);
                        break;
                    case 11:
                        ((MietDaten) getApplication()).setBodenbelag(1);
                        break;
                    case 12:
                        ((MietDaten) getApplication()).setEnergiekennwert(1);
                        break;
                }
                if (gleichweiter){
                    finish();
                } else if(stage == 12){
                    Intent theIntent = new Intent(decide.this, finish.class);
                    startActivity(theIntent);
                    stage = 0;
                    finish();
                } else  {
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
                        ((MietDaten) getApplication()).setSozialwohnung(0);
                        break;
                    case 5:
                        ((MietDaten) getApplication()).setSammelheizung(0);
                        break;
                    case 6:
                        ((MietDaten) getApplication()).setBad(0);
                        break;
                    case 7:
                        ((MietDaten) getApplication()).setMehr_als_zwei_wohnungen(0);
                        break;
                    case 8:
                        ((MietDaten) getApplication()).setAufzug(0);
                        break;
                    case 9:
                        ((MietDaten) getApplication()).setEinbauküche(0);
                        break;
                    case 10:
                        ((MietDaten) getApplication()).setSanitaereinrichtung(0);
                        break;
                    case 11:
                        ((MietDaten) getApplication()).setBodenbelag(0);
                        break;
                    case 12:
                        ((MietDaten) getApplication()).setEnergiekennwert(0);
                        break;
                }
                if (gleichweiter){
                    finish();
                } else if(stage == 12){
                    Intent theIntent = new Intent(decide.this, finish.class);
                    startActivity(theIntent);
                    stage = 0;
                    finish();
                } else  {
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
                if (gleichweiter){
                    finish();
                } else if(stage == 12){
                    Intent theIntent = new Intent(decide.this, finish.class);
                    startActivity(theIntent);
                    stage = 0;
                    finish();
                } else  {
                    stage++;
                    setUp();
                }
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stage == 3){
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                finish_event();
            }
        });
    }

    private void finish_event() {
        CharSequence text = "Die Zahl kann nicht leer sein!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        boolean normal = true;

        if(antwort.getText().toString().isEmpty()){
            toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        } else {
            double tmp;
            switch (stage){
                case 1:
                    tmp = Double.parseDouble(antwort.getText().toString());
                    if(tmp > 10 && tmp < 100000){
                        ((MietDaten) getApplication()).setMietkosten(tmp);
                    } else {
                        text = "Diese Miete können wir uns nicht vorstellen.";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        antwort.setText("");
                        normal = false;
                    }
                    break;
                case 2:
                    tmp = Double.parseDouble(antwort.getText().toString());
                    if(tmp > 2 && tmp < 5000){
                        ((MietDaten) getApplication()).setQm(tmp);
                    } else {
                        text = "So eine Wohnung gibt es in Berlin nicht...";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        antwort.setText("");
                        normal = false;
                    }

                    break;
                case 3:
                    int jahr;
                    try {
                        jahr = Integer.parseInt(antwort.getText().toString());
                    } catch (Exception e){
                        text = "Dieses Jahr kennen wir nicht";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        antwort.setText("");
                        normal = false;
                        break;
                    }
                    if(jahr < 1300) {
                        text = "Deine Wohnung ist zu alt...";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        antwort.setText("");
                        normal = false;
                    }else if (jahr > 2013){
                        text = "Das Mietdeckel Gesetzt gilt nur für Wohnungen, die vor dem 1. Januar 2014 bezugsfertig wurden.";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        antwort.setText("");
                        normal = false;
                    } else {
                        if(jahr <= 1918){
                            ((MietDaten) getApplication()).setJahr(1);
                        } else if(jahr <= 1949){
                            ((MietDaten) getApplication()).setJahr(2);
                        } else if(jahr <= 1964){
                            ((MietDaten) getApplication()).setJahr(3);
                        } else if(jahr <= 1972){
                            ((MietDaten) getApplication()).setJahr(4);
                        } else if(jahr <= 1990){
                            ((MietDaten) getApplication()).setJahr(5);
                        } else if(jahr <= 2002){
                            ((MietDaten) getApplication()).setJahr(6);
                        } else if(jahr <= 2013){
                            ((MietDaten) getApplication()).setJahr(7);
                        }
                    }
                    break;
            }
            if(gleichweiter && normal){
                //Intent theIntent = new Intent(decide.this, finish.class);
                //startActivity(theIntent);
                stage = 0;
                finish();
            } else if(normal){
                stage++;
                setUp();
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

        ConstraintLayout.LayoutParams jaLayoutParams;
        ConstraintLayout.LayoutParams neinLayoutParams;
        int px8Value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, decide.this.getResources().getDisplayMetrics());
        int px32Value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, decide.this.getResources().getDisplayMetrics());
        int px48Value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, decide.this.getResources().getDisplayMetrics());

        if(wohnlage_int == 0){
            jaLayoutParams = (ConstraintLayout.LayoutParams) ja.getLayoutParams();
            jaLayoutParams.leftMargin = px8Value;
            jaLayoutParams.topMargin = px32Value;
            ja.setLayoutParams(jaLayoutParams);
            neinLayoutParams = (ConstraintLayout.LayoutParams) nein.getLayoutParams();
            neinLayoutParams.rightMargin = px8Value;
            neinLayoutParams.topMargin = px32Value;
            nein.setLayoutParams(neinLayoutParams);
        } else {
            jaLayoutParams = (ConstraintLayout.LayoutParams) ja.getLayoutParams();
            jaLayoutParams.leftMargin = px48Value;
            jaLayoutParams.topMargin = px32Value;
            ja.setLayoutParams(jaLayoutParams);
            neinLayoutParams = (ConstraintLayout.LayoutParams) nein.getLayoutParams();
            neinLayoutParams.rightMargin = px48Value;
            neinLayoutParams.topMargin = px32Value;
            nein.setLayoutParams(neinLayoutParams);
        }

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
                help_setUp("Wie groß ist dein Wohnraum in qm(m²)?",4,"1",4,"3",4,"2",0,"70", 0, 4, "");
                break;
            case 3:
                help_setUp("Wann wurde dein Haus gebaut? Wenn du das Baujahr nicht genau kennst, schätze es.",4,"1",4,"3",4,"2",0,"1995", 0, 4, "");
                break;
            case 4:
                help_setUp("Ist deine Wohnung eine Sozialwohnung?",0,"Ja",0,"Nein",4,"2",4,"", 4, 4, "");
                break;
            case 5:
                help_setUp("Hat meine Wohnung eine Sammelheizung?",0,"Ja",0,"Nein",4,"2",4,"", 4, 0, "Sammelheizung?");
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
                help_setUp("Gibt es in meiner Wohnung eine hochwertige Sanitäreinrichtung?",0,"Ja",0,"Nein",4,"2",4,"", 4, 0, "Sanitäteinrichtung?");
                break;
            case 11:
                help_setUp("Gibt es in meiner Wohnung einen hochwertigen Bodenbelag?",0,"Ja",0,"Nein",4,"2",4,"", 4, 4, "");
                break;
            case 12:
                help_setUp("Hat mein Haus einen Energiekennwert von mehr als 120kWh/m² im Jahr?",0,"Ja",0,"Nein",4,"2",4,"", 4, 0, "Energiekennwert?");
                break;
        }
    }

    @Override
    public void finish() {
        if (stage == 0 || gleichweiter){
            super.finish();
        } else {
            stage--;
            setUp();
        }

    }
}
