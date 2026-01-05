package com.example.smartrecruit;

import android.os.Parcel;
import android.os.Parcelable;

public class Jabatan implements Parcelable {

    private int id;
    private String nama;

    public Jabatan(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    protected Jabatan(Parcel in) {
        id = in.readInt();
        nama = in.readString();
    }

    public static final Creator<Jabatan> CREATOR = new Creator<Jabatan>() {
        @Override
        public Jabatan createFromParcel(Parcel in) {
            return new Jabatan(in);
        }

        @Override
        public Jabatan[] newArray(int size) {
            return new Jabatan[size];
        }
    };

    // ===== GETTER =====
    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    // ===== SETTER (INI YANG MEMPERBAIKI ERROR) =====
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setId(int id) {
        this.id = id;
    }

    // ===== PARCELABLE =====
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
    }
}
