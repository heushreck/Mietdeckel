package com.example.miete_berlin;

import android.app.Application;

public class MietDaten extends Application {

    private double qm;
    private double mietkosten;
    private int jahr;
    private boolean sozialwohnung;
    private boolean sammelheizung;
    private boolean bad;
    private boolean mehr_als_zwei_wohnungen;
    private boolean aufzug;
    private boolean einbauküche;
    private boolean sanitaereinrichtung;
    private boolean bodenbelag;
    private boolean energiekennwert;
    private int wohnlage;

    private boolean ist_modern(){
        int counter = 0;
        if(aufzug) counter++;
        if(einbauküche) counter++;
        if(sanitaereinrichtung) counter++;
        if(bodenbelag) counter++;
        if(energiekennwert) counter++;
        if(counter >= 3) return true;
        return false;
    }

    public double getMaxMietkosten() {
        try {
            double qm_preis = 0;
            if(jahr == 1 && sammelheizung && bad){
                qm_preis = 6.45;
            } else if(jahr == 1 && (sammelheizung || bad)){
                qm_preis = 5;
            } else if(jahr == 1 && !sammelheizung && !bad){
                qm_preis = 3.92;
            } else if(jahr == 2 && sammelheizung && bad){
                qm_preis = 6.27;
            } else if(jahr == 2 && (sammelheizung || bad)){
                qm_preis = 5.22;
            } else if(jahr == 2 && !sammelheizung && !bad){
                qm_preis = 4.59;
            } else if(jahr == 3 && sammelheizung && bad){
                qm_preis = 6.08;
            } else if(jahr == 3 && (sammelheizung || bad)){
                qm_preis = 5.62;
            } else if(jahr == 4 && sammelheizung && bad){
                qm_preis = 5.95;
            } else if(jahr == 5 && sammelheizung && bad){
                qm_preis = 6.04;
            } else if(jahr == 6 && sammelheizung && bad){
                qm_preis = 8.13;
            } else if(jahr == 7 && sammelheizung && bad){
                qm_preis = 9.8;
            }
            if(mehr_als_zwei_wohnungen) qm_preis = qm_preis * 1.1;
            if(ist_modern()) qm_preis = qm_preis + 1;
            switch (wohnlage){
                case 1:
                    qm_preis = qm_preis - 0.28;
                    break;
                case 2:
                    qm_preis = qm_preis - 0.09;
                    break;
                case 3:
                    qm_preis = qm_preis + 0.74;
                    break;
            }
            double ganzes = qm_preis * qm;
            ganzes -= ganzes % 0.01;
            ganzes *= 100d;
            ganzes = ((int)ganzes) / 100d;
            return ganzes;
        } catch (Exception e) {
            return -1.0;
        }
    }


    public double getMietkosten() {
        return mietkosten;
    }

    public void setMietkosten(double mietkosten) {
        this.mietkosten = mietkosten;
    }

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public int getWohnlage() {
        return wohnlage;
    }

    public void setWohnlage(int wohnlage) {
        this.wohnlage = wohnlage;
    }

    public boolean isSammelheizung() {
        return sammelheizung;
    }

    public void setSammelheizung(boolean sammelheizung) {
        this.sammelheizung = sammelheizung;
    }

    public boolean isSozialwohnung() {
        return sozialwohnung;
    }

    public void setSozialwohnung(boolean sozialwohnung) {
        this.sozialwohnung = sozialwohnung;
    }

    public boolean isMehr_als_zwei_wohnungen() {
        return mehr_als_zwei_wohnungen;
    }

    public void setMehr_als_zwei_wohnungen(boolean mehr_als_zwei_wohnungen) {
        this.mehr_als_zwei_wohnungen = mehr_als_zwei_wohnungen;
    }

    public boolean isAufzug() {
        return aufzug;
    }

    public void setAufzug(boolean aufzug) {
        this.aufzug = aufzug;
    }

    public boolean isEinbauküche() {
        return einbauküche;
    }

    public void setEinbauküche(boolean einbauküche) {
        this.einbauküche = einbauküche;
    }

    public boolean isBodenbelag() {
        return bodenbelag;
    }

    public void setBodenbelag(boolean bodenbelag) {
        this.bodenbelag = bodenbelag;
    }

    public boolean isBad() {
        return bad;
    }

    public void setBad(boolean bad) {
        this.bad = bad;
    }

    public boolean isSanitaereinrichtung() {
        return sanitaereinrichtung;
    }

    public void setSanitaereinrichtung(boolean sanitaereinrichtung) {
        this.sanitaereinrichtung = sanitaereinrichtung;
    }

    public boolean isEnergiekennwert() {
        return energiekennwert;
    }

    public void setEnergiekennwert(boolean energiekennwert) {
        this.energiekennwert = energiekennwert;
    }

    public double getQm() {
        return qm;
    }

    public void setQm(double qm) {
        this.qm = qm;
    }
}
