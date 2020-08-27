package com.example.rgbcolorgame.activity.domain;

import java.io.Serializable;

public class Rezultat implements Serializable {

    int rezultatID;
    int rezultat;
    String datum;
    String igrac;

    public Rezultat() {
    }

    public Rezultat(int rezultatID, int rezultat, String datum, String igrac) {
        this.rezultatID = rezultatID;
        this.rezultat = rezultat;
        this.datum = datum;
        this.igrac = igrac;
    }

    public int getRezultatID() {
        return rezultatID;
    }

    public void setRezultatID(int rezultatID) {
        this.rezultatID = rezultatID;
    }

    public int getRezultat() {
        return rezultat;
    }

    public void setRezultat(int rezultat) {
        this.rezultat = rezultat;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getIgrac() {
        return igrac;
    }

    public void setIgrac(String igrac) {
        this.igrac = igrac;
    }
}
