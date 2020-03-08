package com.heushreck.miete_berlin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.barteksc.pdfviewer.PDFView;

public class pdf extends AppCompatActivity {

    PDFView pdfView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        androidx.appcompat.app.ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("Gesetzentwurf-Neuregelung-Mietenbegrenzung-MietenWoGBln.pdf").load();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
