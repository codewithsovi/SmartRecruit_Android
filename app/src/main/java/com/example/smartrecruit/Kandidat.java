package com.example.smartrecruit;

import java.io.Serializable;

public class Kandidat implements Serializable {

    private int id;
    private String nama;
    private int jabatanId;
    private int tesTulis;
    private int tesWawancara;
    private int tesKesehatan;
    private int tesKeterampilan;

    // ===== CONSTRUCTOR 7 PARAMETER =====
    public Kandidat(int id,
                    String nama,
                    int jabatanId,
                    int tesTulis,
                    int tesWawancara,
                    int tesKesehatan,
                    int tesKeterampilan) {

        this.id = id;
        this.nama = nama;
        this.jabatanId = jabatanId;
        this.tesTulis = tesTulis;
        this.tesWawancara = tesWawancara;
        this.tesKesehatan = tesKesehatan;
        this.tesKeterampilan = tesKeterampilan;
    }

    // ===== GETTER (KODE ANDA SUDAH BENAR) =====
    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getJabatanId() {
        return jabatanId;
    }

    public int getTesTulis() {
        return tesTulis;
    }

    public int getTesWawancara() {
        return tesWawancara;
    }

    public int getTesKesehatan() {
        return tesKesehatan;
    }

    public int getTesKeterampilan() {
        return tesKeterampilan;
    }

    // ===== SETTER (TAMBAHKAN METHOD-METHOD INI) =====
    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJabatanId(int jabatanId) {
        this.jabatanId = jabatanId;
    }

    public void setTesTulis(int tesTulis) {
        this.tesTulis = tesTulis;
    }

    public void setTesWawancara(int tesWawancara) {
        this.tesWawancara = tesWawancara;
    }

    public void setTesKesehatan(int tesKesehatan) {
        this.tesKesehatan = tesKesehatan;
    }

    public void setTesKeterampilan(int tesKeterampilan) {
        this.tesKeterampilan = tesKeterampilan;
    }

    // ===== TOTAL SCORE =====
    public int getTotalScore() {
        return tesTulis + tesWawancara + tesKesehatan + tesKeterampilan;
    }
}