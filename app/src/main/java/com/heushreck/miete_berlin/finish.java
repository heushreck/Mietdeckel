package com.heushreck.miete_berlin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class finish extends AppCompatActivity {

    private TextView hoch;
    private TextView titel;
    private TextView angaben;

    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    private ConstraintLayout mConstraintLayout;
    private ConstraintSet mConstraintSet = new ConstraintSet();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        if(height < 1300){
            setContentView(R.layout.activity_finish_small);
        } else {
            setContentView(R.layout.activity_finish);
        }
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");

        titel = findViewById(R.id.titel);
        hoch = findViewById(R.id.hoch);
        angaben = findViewById(R.id.deine_angaben);
        mConstraintLayout = findViewById(R.id.my_constraint_layout);
        listView = findViewById(R.id.liste);
        titel.setTypeface(custom_font);
        hoch.setTypeface(custom_font);
        angaben.setTypeface(custom_font);

        hoch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Combo combo = ((MietDaten) getApplication()).getMaxMietkosten();
                if(combo.max_miete != -1){
                    CustomDialogClass cdd=new CustomDialogClass(finish.this, combo.rechnung, "Rechnung");
                    cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    cdd.show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        Combo combo = ((MietDaten) getApplication()).getMaxMietkosten();
        hoch.setText(combo.why);

        dataModels= new ArrayList<>();

        String wohnlage = ((MietDaten) getApplication()).getWohnlage();
        String mietkosten = String.format("%1$,.2f", ((MietDaten) getApplication()).getMietkosten()) + "€";
        String qm = String.format("%1$,.2f", ((MietDaten) getApplication()).getQm()) + " m²";
        String jahr = ((MietDaten) getApplication()).getJahr();
        String sozialwohnung = ((MietDaten) getApplication()).isSozialwohnung();
        String sammelheizung = ((MietDaten) getApplication()).isSammelheizung();
        String bad = ((MietDaten) getApplication()).isBad();
        String mehr_als_zwei = ((MietDaten) getApplication()).isMehr_als_zwei_wohnungen();
        String aufzug = ((MietDaten) getApplication()).isAufzug();
        String einbauküche = ((MietDaten) getApplication()).isEinbauküche();
        String sanitaereinrichtung = ((MietDaten) getApplication()).isSanitaereinrichtung();
        String bodenbelag = ((MietDaten) getApplication()).isBodenbelag();
        String energiekennwert = ((MietDaten) getApplication()).isEnergiekennwert();
        dataModels.add(new DataModel("Wohnlage:", wohnlage , 0));
        dataModels.add(new DataModel("Miete jetzt:", mietkosten, 1));
        dataModels.add(new DataModel("Wohnfläche:", qm, 2));
        dataModels.add(new DataModel("Das Haus wurde gebaut:", jahr, 3));
        dataModels.add(new DataModel("Wohnung ist Sozialwohnung:",sozialwohnung, 4));
        dataModels.add(new DataModel("Sammelheizung vorhanden:",sammelheizung , 5));
        dataModels.add(new DataModel("Bad vorhanden:",bad, 6));
        dataModels.add(new DataModel("Mehr als 2 Wohnungen im Haus:",mehr_als_zwei , 7));
        dataModels.add(new DataModel("Personenaufzug:",aufzug , 8));
        dataModels.add(new DataModel("Einbauküche vorhanden:",einbauküche , 9));
        dataModels.add(new DataModel("Hochwertige Sanitäreinrichtung:",sanitaereinrichtung , 10));
        dataModels.add(new DataModel("Hochwertiger Bodenbelag:",bodenbelag , 11));
        dataModels.add(new DataModel("Energiekennwert > 120kwh/m²a:",energiekennwert, 12));
        adapter= new CustomAdapter(dataModels,getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel= dataModels.get(position);
                Intent theIntent = new Intent(finish.this, decide.class);
                theIntent.putExtra("stage", dataModel.getStage());
                theIntent.putExtra("gleichweiter", true);
                startActivity(theIntent);
            }
        });
        super.onResume();
    }
}
