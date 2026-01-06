package com.example.smartrecruit;

import java.util.ArrayList;
import java.util.Iterator;
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

        // ===== DATA KANDIDAT =====
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
    public List<Jabatan> getJabatanList() { return listJabatan; }
    public void addJabatan(Jabatan jabatan) { listJabatan.add(jabatan); }
    public void updateJabatan(int position, Jabatan jabatan) { listJabatan.set(position, jabatan); }
    public void deleteJabatan(int position) { listJabatan.remove(position); }

    // ===== KANDIDAT =====
    public List<Kandidat> getKandidatList() { return listKandidat; }

    public List<Kandidat> getKandidatByJabatanId(int jabatanId) {
        List<Kandidat> result = new ArrayList<>();
        for (Kandidat k : listKandidat) {
            if (k.getJabatanId() == jabatanId) {
                result.add(k);
            }
        }
        return result;
    }

    public void addKandidat(Kandidat kandidat) { listKandidat.add(kandidat); }
    public void deleteKandidat(Kandidat kandidatToDelete) {
        Iterator<Kandidat> iterator = listKandidat.iterator();
        while (iterator.hasNext()) {
            Kandidat k = iterator.next();
            if (k.getId() == kandidatToDelete.getId()) {
                iterator.remove();
                break;
            }
        }
    }
    public void updateKandidat(Kandidat kandidatToUpdate) {
        for (int i = 0; i < listKandidat.size(); i++) {
            Kandidat k = listKandidat.get(i);
            if (k.getId() == kandidatToUpdate.getId()) {
                listKandidat.set(i, kandidatToUpdate);
                break;
            }
        }
    }

    // ===== HASIL AKHIR =====
    public List<HasilAkhirItem> getRankedAllCandidates() {
        List<HasilAkhirItem> rankedList = new ArrayList<>();

        // 1. Hitung skor untuk setiap kandidat dan buat item sementara
        for (Kandidat kandidat : listKandidat) {
            int averageScore = (kandidat.getTesTulis() + kandidat.getTesWawancara() +
                    kandidat.getTesKesehatan() + kandidat.getTesKeterampilan()) / 4;

            // 2. Cari nama jabatan berdasarkan ID
            String jobTitle = "Jabatan Tidak Diketahui";
            for (Jabatan jabatan : listJabatan) {
                if (jabatan.getId() == kandidat.getJabatanId()) {
                    jobTitle = jabatan.getNama();
                    break;
                }
            }

            // 3. Tambahkan ke daftar (peringkat awal bisa 0)
            rankedList.add(new HasilAkhirItem(0, kandidat.getNama(), jobTitle, averageScore));
        }

        // 4. Urutkan daftar dari skor tertinggi ke terendah
        rankedList.sort((item1, item2) -> Integer.compare(item2.getScore(), item1.getScore()));

        // 5. Assign peringkat setelah diurutkan menggunakan method setter
        for (int i = 0; i < rankedList.size(); i++) {
            // PERBAIKAN: Gunakan method setRank() yang sudah kita buat
            rankedList.get(i).setRank(i + 1);
        }

        return rankedList;
    }
}