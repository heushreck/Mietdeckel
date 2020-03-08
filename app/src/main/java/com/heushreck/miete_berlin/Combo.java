package com.heushreck.miete_berlin;

public class Combo {
    public double max_miete;
    public String why;
    public boolean zu_hoch;
    public String rechnung;

    public Combo(double max_miete, String why, boolean zu_hoch, String rechnung){
        this.max_miete = max_miete;
        this.why = why;
        this.zu_hoch = zu_hoch;
        this.rechnung = rechnung;
    }
}
