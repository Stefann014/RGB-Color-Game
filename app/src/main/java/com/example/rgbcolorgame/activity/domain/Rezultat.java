package com.example.rgbcolorgame.activity.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "rezultat_table")
public class Rezultat implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int rezultatID;
    int rezultat;
    String datum;
    String igrac;

    public Rezultat() {
    }

    public Rezultat(int rezultat, String datum, String igrac) {
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

    @Override
    public String toString() {
        return "Rezultat{" +
                "rezultatID=" + rezultatID +
                ", rezultat=" + rezultat +
                ", datum='" + datum + '\'' +
                ", igrac='" + igrac + '\'' +
                '}';
    }
}
