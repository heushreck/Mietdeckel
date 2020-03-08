package com.heushreck.miete_berlin;

import android.app.Application;

public class MietDaten extends Application {

    private double qm = 0.0;
    private double mietkosten = 0.0;
    private int jahr = -1;
    private int sozialwohnung = -1;
    private int sammelheizung = -1;
    private int bad = -1;
    private int mehr_als_zwei_wohnungen = -1;
    private int aufzug = -1;
    private int einbauküche = -1;
    private int sanitaereinrichtung = -1;
    private int bodenbelag = -1;
    private int energiekennwert = -1;
    private int wohnlage = -1;

    private boolean ist_modern(){
        int counter = 0;
        if(aufzug == 1) counter++;
        if(einbauküche == 1) counter++;
        if(sanitaereinrichtung == 1) counter++;
        if(bodenbelag == 1) counter++;
        if(energiekennwert == 1) counter++;
        if(counter >= 3) return true;
        return false;
    }

    public double make_nice(double d){
        double ganzes = d;
        ganzes -= ganzes % 0.01;
        ganzes *= 100d;
        ganzes = ((int)ganzes) / 100d;
        return ganzes;
    }

    public Combo getMaxMietkosten() {
        String rechnung = "";
        if(!isComplete()){
            return new Combo(-1, "Wir konnten noch nicht bestimmen, ob ihre Miete zu hoch ist, da uns noch Angaben fehlen.", false, "");
        }
        double qm_preis = 0;
        if(jahr == 1 && sammelheizung==1 && bad==1){
            rechnung += "Ermittelte Tabellenmiete ist:\n6,45 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 6.45;
        } else if(jahr == 1 && (sammelheizung==1 || bad==1)){
            rechnung += "Ermittelte Tabellenmiete ist:\n5 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 5;
        } else if(jahr == 1 && sammelheizung==2 && bad==2){
            rechnung += "Ermittelte Tabellenmiete ist:\n3,92 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 3.92;
        } else if(jahr == 2 && sammelheizung==1 && bad==1){
            rechnung += "Ermittelte Tabellenmiete ist:\n6,27 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 6.27;
        } else if(jahr == 2 && (sammelheizung==1 || bad==1)){
            rechnung += "Ermittelte Tabellenmiete ist:\n5,22 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 5.22;
        } else if(jahr == 2 && sammelheizung==2 && bad==2){
            rechnung += "Ermittelte Tabellenmiete ist:\n4,59 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 4.59;
        } else if(jahr == 3 && sammelheizung==1 && bad==1){
            rechnung += "Ermittelte Tabellenmiete ist:\n6,08 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 6.08;
        } else if(jahr == 3 && (sammelheizung==1 || bad==1)){
            rechnung += "Ermittelte Tabellenmiete ist:\n5,62 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 5.62;
        } else if(jahr == 4 && sammelheizung==1 && bad==1){
            rechnung += "Ermittelte Tabellenmiete ist:\n5,95 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 5.95;
        } else if(jahr == 5 && sammelheizung==1 && bad==1){
            rechnung += "Ermittelte Tabellenmiete ist:\n6,04 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 6.04;
        } else if(jahr == 6 && sammelheizung==1 && bad==1){
            rechnung += "Ermittelte Tabellenmiete ist:\n8,13 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 8.13;
        } else if(jahr == 7 && sammelheizung==1 && bad==1){
            rechnung += "Ermittelte Tabellenmiete ist:\n9,8 Euro/m² (laut Gesetzesentwurf)\n\n";
            qm_preis = 9.8;
        } else if(jahr >= 3){
            return new Combo(-1.0, "Wir konnten deine Miete nicht ermitteln, da alle Wohnungen die nach 1950 gebaut wurden eine Sammelheizung und ein Bad enthalten.", false, "");
        } else {
            return new Combo(-1.0, "Wir konnten aus uns unbekannten Gründen deine Miete nicht ermitteln", false, "");
        }
        switch (wohnlage){
            case 1:
                qm_preis = qm_preis - 0.28;
                rechnung += "Durch die Wohnlage einfach werden:\n0,28 Euro/m² abgezogen.\n\n";
                break;
            case 2:
                qm_preis = qm_preis - 0.09;
                rechnung += "Durch die Wohnlage mittel werden:\n0,09 Euro/m² abgezogen.\n\n";
                break;
            case 3:
                qm_preis = qm_preis + 0.74;
                rechnung += "Durch die Wohnlage gut werden:\n0,74 Euro/m² aufgeschlagen.\n\n";
                break;
        }
        if(ist_modern()){
            rechnung += "Modernisierungszuschlag: 1 Euro/m².\n\n";
            qm_preis = qm_preis + 1;
        }else {
            rechnung += "Kein Modernisierungszuschlag auf den Quadratmeter Preis.\n\n";
        }
        if(mehr_als_zwei_wohnungen==0){
            rechnung += "Zuschlag für Ein- oder\nZweifamilienhaus (10 %): ja.\n\n";
            qm_preis = qm_preis * 1.1;
        } else {
            rechnung += "Zuschlag für Ein- oder\nZweifamilienhaus (10 %): nein.\n\n";
        }
        rechnung += "Tabellenmiete mit Ab- oder Zuschlägen:\n " + make_nice(qm_preis) + " Euro/m²\n\n";
        qm_preis = qm_preis * 1.2;
        rechnung += "Wuchergrenze: " + make_nice(qm_preis) + " Euro/m².\n\n";
        double ganzes = qm_preis * qm;
        ganzes = make_nice(ganzes);
        rechnung += "Neue Mietobergrenze:\n" + ganzes + " Euro.";
        String text = "";
        if(ganzes < mietkosten){
            double sparen = (mietkosten - ganzes);
            sparen = make_nice(sparen);
            text = "Ihr Miete ist zu hoch!\nDie Maximale obermiete für ihre Wohnung beträgt " +
                    ganzes + "€ und ihr Miete von " + mietkosten + "€ überschreitet diese.\n" +
                    "Das heißt sie können klagen und sich monatlich bis zu " + sparen + "€ sparen.\n(Für Rechnung antippen)";
            return new Combo(ganzes, text, true, rechnung);
        }
        text = "Ihr Miete ist nicht zu hoch!\nDie Maximale obermiete für ihre Wohnung beträgt " +
                ganzes + "€ und ihr Miete von " + mietkosten + "€ überschreitet diese nicht.\n(Für Rechnung antippen)";
        return new Combo(ganzes,text, false, rechnung);
    }


    public double getMietkosten() {
        return mietkosten;
    }

    public void setMietkosten(double mietkosten) {
        this.mietkosten = mietkosten;
    }

    public String getJahr() {
        String value = "";
        switch (jahr){
            case -1:
                value = "";
                break;
            case 1:
                value = "vor 1918";
                break;
            case 2:
                value = "zwischen 1918\nund 1949";
                break;
            case 3:
                value = "zwischen 1950\nund 1964";
                break;
            case 4:
                value = "zwischen 1965\nund 1972";
                break;
            case 5:
                value = "zwischen 1973\nund 1990";
                break;
            case 6:
                value = "zwischen 1991\nund 2002";
                break;
            case 7:
                value = "zwischen 2003\nund 2014";
                break;
            case 8:
                value = "nach 2014";
                break;
        }
        return value;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public String getWohnlage() {
        String value = "";
        switch (wohnlage){
            case -1:
                value = "";
                break;
            case 1:
                value = "einfach";
                break;
            case 2:
                value = "mittel";
                break;
            case 3:
                value = "gut";
                break;

        }
        return value;
    }

    public void setWohnlage(int wohnlage) {
        this.wohnlage = wohnlage;
    }

    public String isSammelheizung() {
        String value = "";
        switch (sammelheizung){
            case -1:
                value = "";
                break;
            case 0:
                value = "Nein";
                break;
            case 1:
                value = "Ja";
                break;

        }
        return value;
    }

    public void setSammelheizung(int sammelheizung) {
        this.sammelheizung = sammelheizung;
    }

    public String isSozialwohnung() {
        String value = "";
        switch (sozialwohnung){
            case -1:
                value = "";
                break;
            case 0:
                value = "Nein";
                break;
            case 1:
                value = "Ja";
                break;

        }
        return value;
    }

    public void setSozialwohnung(int sozialwohnung) {
        this.sozialwohnung = sozialwohnung;
    }

    public String isMehr_als_zwei_wohnungen() {
        String value = "";
        switch (mehr_als_zwei_wohnungen){
            case -1:
                value = "";
                break;
            case 0:
                value = "Nein";
                break;
            case 1:
                value = "Ja";
                break;

        }
        return value;
    }

    public void setMehr_als_zwei_wohnungen(int mehr_als_zwei_wohnungen) {
        this.mehr_als_zwei_wohnungen = mehr_als_zwei_wohnungen;
    }

    public String isAufzug() {
        String value = "";
        switch (aufzug){
            case -1:
                value = "";
                break;
            case 0:
                value = "Nein";
                break;
            case 1:
                value = "Ja";
                break;

        }
        return value;
    }

    public void setAufzug(int aufzug) {
        this.aufzug = aufzug;
    }

    public String isEinbauküche() {
        String value = "";
        switch (einbauküche){
            case -1:
                value = "";
                break;
            case 0:
                value = "Nein";
                break;
            case 1:
                value = "Ja";
                break;

        }
        return value;
    }

    public void setEinbauküche(int einbauküche) {
        this.einbauküche = einbauküche;
    }

    public String isBodenbelag() {
        String value = "";
        switch (bodenbelag){
            case -1:
                value = "";
                break;
            case 0:
                value = "Nein";
                break;
            case 1:
                value = "Ja";
                break;

        }
        return value;
    }

    public void setBodenbelag(int bodenbelag) {
        this.bodenbelag = bodenbelag;
    }

    public String isBad() {
        String value = "";
        switch (bad){
            case -1:
                value = "";
                break;
            case 0:
                value = "Nein";
                break;
            case 1:
                value = "Ja";
                break;

        }
        return value;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public String isSanitaereinrichtung() {
        String value = "";
        switch (sanitaereinrichtung){
            case -1:
                value = "";
                break;
            case 0:
                value = "Nein";
                break;
            case 1:
                value = "Ja";
                break;

        }
        return value;
    }

    public void setSanitaereinrichtung(int sanitaereinrichtung) {
        this.sanitaereinrichtung = sanitaereinrichtung;
    }

    public String isEnergiekennwert() {
        String value = "";
        switch (energiekennwert){
            case -1:
                value = "";
                break;
            case 0:
                value = "Nein";
                break;
            case 1:
                value = "Ja";
                break;

        }
        return value;
    }

    public void setEnergiekennwert(int energiekennwert) {
        this.energiekennwert = energiekennwert;
    }

    public double getQm() {
        return qm;
    }

    public void setQm(double qm) {
        this.qm = qm;
    }

    public boolean isComplete() {
        if(qm > 0.0 && mietkosten > 0.0 && jahr > -1 && sozialwohnung > -1 && sammelheizung > -1 && bad > -1 && mehr_als_zwei_wohnungen > -1 && aufzug > -1 && einbauküche > -1 && sanitaereinrichtung > -1 && bodenbelag > -1 && energiekennwert > -1 && wohnlage > -1){
            return true;
        }
        return false;
    }

}
