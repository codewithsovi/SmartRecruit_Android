package com.example.smartrecruit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView tvJumlahKriteria;
    private TextView tvJumlahJabatan;
    private TextView tvJumlahKandidat;
    private TextView tvProsesSeleksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi TextView
        tvJumlahKriteria = findViewById(R.id.tvJumlahKriteria);
        tvJumlahJabatan = findViewById(R.id.tvJumlahJabatan);
        tvJumlahKandidat = findViewById(R.id.tvJumlahKandidat);
        tvProsesSeleksi = findViewById(R.id.tvProsesSeleksi);

        // Ambil data dari repository
        DataRepository repo = DataRepository.getInstance();

        // Jumlah Kriteria (parameter penilaian)
        int jumlahKriteria = 4;
        tvJumlahKriteria.setText(String.valueOf(jumlahKriteria));

        // Jumlah Jabatan
        tvJumlahJabatan.setText(
                String.valueOf(repo.getJabatanList().size())
        );

        // Jumlah Kandidat
        tvJumlahKandidat.setText(
                String.valueOf(repo.getKandidatList().size())
        );

        // Proses Seleksi (jumlah hasil perangkingan)
        tvProsesSeleksi.setText(
                String.valueOf(repo.getRankedAllCandidates().size())
        );

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                return true;
            } else if (itemId == R.id.nav_jabatan) {
                startActivity(new Intent(this, DataJabatanActivity.class));
                return true;
            } else if (itemId == R.id.nav_kandidat) {
                startActivity(new Intent(this, DataKandidatActivity.class));
                return true;
            } else if (itemId == R.id.nav_hasil) {
                startActivity(new Intent(this, HasilAkhirActivity.class));
                return true;
            }

            return false;
        });
    }
}
