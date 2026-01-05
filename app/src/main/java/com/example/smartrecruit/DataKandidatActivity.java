package com.example.smartrecruit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class DataKandidatActivity extends AppCompatActivity {

    private RecyclerView rvJabatan;
    private JabatanAdapter jabatanAdapter;
    private List<Jabatan> listJabatan;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kandidat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        rvJabatan = findViewById(R.id.rvJabatan);
        bottomNavigationView = findViewById(R.id.bottomNav);

        // ✅ Ambil data
        listJabatan = DataRepository.getInstance().getJabatanList();

        // ✅ FIX: gunakan constructor kosong
        jabatanAdapter = new JabatanAdapter();
        jabatanAdapter.setListJabatan(listJabatan);

        rvJabatan.setLayoutManager(new LinearLayoutManager(this));
        rvJabatan.setAdapter(jabatanAdapter);

        jabatanAdapter.setOnItemClickListener((jabatan, position) -> {
            Intent intent = new Intent(
                    DataKandidatActivity.this,
                    DaftarKandidatActivity.class
            );
            intent.putExtra("EXTRA_JABATAN", jabatan);
            startActivity(intent);
        });

        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.nav_kandidat);
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
                return true;
            } else if (id == R.id.nav_hasil) {
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
