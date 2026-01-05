package com.example.smartrecruit;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static DataRepository instance;

    private List<Jabatan> listJabatan;
    private List<Kandidat> listKandidat;

    private DataRepository() {

        // ===== DATA JABATAN =====
        listJabatan = new ArrayList<>();
        listJabatan.add(new Jabatan(1, "Staff"));
        listJabatan.add(new Jabatan(2, "Manager"));
        listJabatan.add(new Jabatan(3, "Supervisor"));

        // ===== DATA KANDIDAT (7 PARAMETER) =====
        listKandidat = new ArrayList<>();
        listKandidat.add(new Kandidat(1, "Sovi", 1, 80, 85, 90, 88));
        listKandidat.add(new Kandidat(2, "Afri", 1, 75, 88, 92, 90));
        listKandidat.add(new Kandidat(3, "Budi", 2, 70, 80, 85, 78));
        listKandidat.add(new Kandidat(4, "Siti", 2, 85, 90, 95, 92));
    }

    public static synchronized DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    // ===== JABATAN =====
    public List<Jabatan> getJabatanList() {
        return listJabatan;
    }

    public void addJabatan(Jabatan jabatan) {
        listJabatan.add(jabatan);
    }

    public void updateJabatan(int position, Jabatan jabatan) {
        listJabatan.set(position, jabatan);
    }

    public void deleteJabatan(int position) {
        listJabatan.remove(position);
    }

    // ===== KANDIDAT =====
    public List<Kandidat> getKandidatList() {
        return listKandidat;
    }

    public List<Kandidat> getKandidatByJabatanId(int jabatanId) {
        List<Kandidat> result = new ArrayList<>();
        for (Kandidat k : listKandidat) {
            if (k.getJabatanId() == jabatanId) {
                result.add(k);
            }
        }
        return result;
    }

    public void addKandidat(Kandidat kandidat) {
        listKandidat.add(kandidat);
    }
}
