package com.example.a10.palembangtour.Models;

import android.graphics.Bitmap;

/**
 * Created by 10 on 11/06/2017.
 */

public class Result{

    
    String id;
    String nama;
    String dayaTarik;
    String lokasi;
    String fasilitas;
    String pengelola;
    String jarakTempuh;
    String image;
    private Bitmap picture;
    private boolean database;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDayaTarik() {
        return dayaTarik;
    }

    public void setDayaTarik(String dayaTarik) {
        this.dayaTarik = dayaTarik;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getPengelola() {
        return pengelola;
    }

    public void setPengelola(String pengelola) {
        this.pengelola = pengelola;
    }

    public String getJarakTempuh() {
        return jarakTempuh;
    }

    public void setJarakTempuh(String jarakTempuh) {
        this.jarakTempuh = jarakTempuh;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public boolean isDatabase() {
        return database;
    }

    public void setDatabase(boolean database) {
        this.database = database;
    }
}
