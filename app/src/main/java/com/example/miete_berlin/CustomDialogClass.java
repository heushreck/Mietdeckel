package com.example.miete_berlin;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialogClass extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button ok;
    public TextView txt_dia;
    public TextView txt_title;
    String message;
    String titel;

    public CustomDialogClass(Activity a, String message, String titel) {
        super(a);
        this.c = a;
        this.message = message;
        this.titel = titel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        Typeface custom_font = Typeface.createFromAsset(c.getAssets(),  "fonts/Montserrat-Regular.ttf");
        ok = (Button) findViewById(R.id.btn_ok);
        txt_dia = findViewById(R.id.txt_dia);
        txt_title = findViewById(R.id.txt_title);
        ok.setTypeface(custom_font);
        txt_dia.setTypeface(custom_font);
        txt_title.setTypeface(custom_font);
        txt_dia.setText(message);
        txt_title.setText(titel);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
