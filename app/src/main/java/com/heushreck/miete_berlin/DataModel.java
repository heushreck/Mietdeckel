package com.heushreck.miete_berlin;

public class DataModel {

    private String question;
    private String antwort;
    private int stage;

    public DataModel(String question, String antwort, int stage) {
        this.question=question;
        this.antwort=antwort;
        this.stage = stage;
    }

    public String getAntwort() {
        return antwort;
    }

    public String getQuestion() {
        return question;
    }

    public int getStage() {
        return stage;
    }
}