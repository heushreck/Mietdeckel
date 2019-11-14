package com.example.miete_berlin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toolbar;

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
