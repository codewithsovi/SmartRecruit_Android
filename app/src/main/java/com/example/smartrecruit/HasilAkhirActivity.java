package com.example.smartrecruit;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class HasilAkhirActivity extends AppCompatActivity {

    private RecyclerView rvHasilAkhir;
    private HasilAkhirAdapter hasilAkhirAdapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_akhir);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        rvHasilAkhir = findViewById(R.id.rvHasilAkhir);
        bottomNavigationView = findViewById(R.id.bottomNav);

        setupRecyclerView();
        setupBottomNavigation();
        loadResultData();
    }

    private void setupRecyclerView() {
        hasilAkhirAdapter = new HasilAkhirAdapter();
        rvHasilAkhir.setLayoutManager(new LinearLayoutManager(this));
        rvHasilAkhir.setAdapter(hasilAkhirAdapter);
    }

    private void loadResultData() {
        List<HasilAkhirItem> results = DataRepository.getInstance().getRankedAllCandidates();
        hasilAkhirAdapter.setListResults(results);
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.nav_hasil);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_jabatan) {
                startActivity(new Intent(this, DataJabatanActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_kandidat) {
                startActivity(new Intent(this, DataKandidatActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_hasil) {
                // PERBAIKAN: Hanya return true, karena sudah di halaman ini
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}