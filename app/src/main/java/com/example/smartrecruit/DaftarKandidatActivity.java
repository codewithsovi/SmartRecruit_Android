package com.example.smartrecruit;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class DaftarKandidatActivity extends AppCompatActivity {

    private KandidatAdapter adapter;
    private Jabatan selectedJabatan;
    private DataRepository repository = DataRepository.getInstance();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kandidat);

        selectedJabatan = getIntent().getParcelableExtra("EXTRA_JABATAN");

        RecyclerView rv = findViewById(R.id.rvKandidat);
        FloatingActionButton fab = findViewById(R.id.fabAdd);
        bottomNavigationView = findViewById(R.id.bottomNav);

        adapter = new KandidatAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        // --- Aksi untuk Floating Action Button (TAMBAH Kandidat) ---
        fab.setOnClickListener(v -> {
            // ... (kode untuk dialog tambah tidak berubah) ...
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_form_kandidat, null);
            EditText etNama = dialogView.findViewById(R.id.etNamaKandidat);
            EditText etTulis = dialogView.findViewById(R.id.etTesTulis);
            EditText etWawancara = dialogView.findViewById(R.id.etTesWawancara);
            EditText etKesehatan = dialogView.findViewById(R.id.etTesKesehatan);
            EditText etKeterampilan = dialogView.findViewById(R.id.etTesKeterampilan);

            new AlertDialog.Builder(DaftarKandidatActivity.this)
                    .setTitle("Tambah Kandidat")
                    .setView(dialogView)
                    .setPositiveButton("Simpan", (dialog, which) -> {
                        String nama = etNama.getText().toString().trim();
                        if (nama.isEmpty()) {
                            Toast.makeText(DaftarKandidatActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int id = repository.getKandidatList().size() + 1;
                        Kandidat newKandidat = new Kandidat(
                                id, nama, selectedJabatan.getId(),
                                getIntValue(etTulis), getIntValue(etWawancara),
                                getIntValue(etKesehatan), getIntValue(etKeterampilan)
                        );
                        repository.addKandidat(newKandidat);

                        loadData();
                        Toast.makeText(DaftarKandidatActivity.this, "Kandidat berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Batal", null)
                    .show();
        });

        // --- Aksi untuk Adapter (EDIT dan HAPUS Kandidat) ---
        adapter.setOnActionListener(new KandidatAdapter.OnActionListener() {
            @Override
            public void onEdit(Kandidat k) {
                // ... (kode untuk dialog edit tidak berubah) ...
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_form_kandidat, null);
                EditText etNama = dialogView.findViewById(R.id.etNamaKandidat);
                EditText etTulis = dialogView.findViewById(R.id.etTesTulis);
                EditText etWawancara = dialogView.findViewById(R.id.etTesWawancara);
                EditText etKesehatan = dialogView.findViewById(R.id.etTesKesehatan);
                EditText etKeterampilan = dialogView.findViewById(R.id.etTesKeterampilan);

                etNama.setText(k.getNama());
                etTulis.setText(String.valueOf(k.getTesTulis()));
                etWawancara.setText(String.valueOf(k.getTesWawancara()));
                etKesehatan.setText(String.valueOf(k.getTesKesehatan()));
                etKeterampilan.setText(String.valueOf(k.getTesKeterampilan()));

                new AlertDialog.Builder(DaftarKandidatActivity.this)
                        .setTitle("Edit Kandidat")
                        .setView(dialogView)
                        .setPositiveButton("Simpan Perubahan", (dialog, which) -> {
                            String nama = etNama.getText().toString().trim();
                            if (nama.isEmpty()) {
                                Toast.makeText(DaftarKandidatActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            k.setNama(nama);
                            k.setTesTulis(getIntValue(etTulis));
                            k.setTesWawancara(getIntValue(etWawancara));
                            k.setTesKesehatan(getIntValue(etKesehatan));
                            k.setTesKeterampilan(getIntValue(etKeterampilan));

                            loadData();
                            Toast.makeText(DaftarKandidatActivity.this, "Kandidat berhasil diperbarui", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Batal", null)
                        .show();
            }

            @Override
            public void onDelete(Kandidat k) {
                repository.getKandidatList().remove(k);
                loadData();
                Toast.makeText(DaftarKandidatActivity.this, "Kandidat berhasil dihapus", Toast.LENGTH_SHORT).show();
            }
        });

        setupBottomNavigation(); // <<<<< PERBAIKAN: TAMBAHKAN BARIS INI
        loadData();
    }

    private void loadData() {
        List<Kandidat> data = repository.getKandidatByJabatanId(selectedJabatan.getId());
        adapter.setListKandidat(data);
    }

    private int getIntValue(EditText editText) {
        String text = editText.getText().toString();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
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
        finish();
        return true;
    }
}