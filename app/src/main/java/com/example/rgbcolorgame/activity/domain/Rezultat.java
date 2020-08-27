package com.example.rgbcolorgame.activity.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "rezultat_table")
public class Rezultat implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int rezultatID;
    int rezultat;
    String datum;
    @ColumnInfo(name = "vreme_sekundi")
    int vremeSekundi;
    String igrac;

    public Rezultat() {
    }

    public Rezultat(int rezultat, String datum, int vremeSekundi, String igrac) {
        this.rezultat = rezultat;
        this.datum = datum;
        this.vremeSekundi = vremeSekundi;
        this.igrac = igrac;
    }

    public int getVremeSekundi() {
        return vremeSekundi;
    }

    public void setVremeSekundi(int vremeSekundi) {
        this.vremeSekundi = vremeSekundi;
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
