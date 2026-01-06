package com.example.smartrecruit;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.MenuItem;
import java.util.List;

public class DataJabatanActivity extends AppCompatActivity implements JabatanAdapter.OnDeleteClickListener {

    private RecyclerView rvJabatan;
    private JabatanAdapter jabatanAdapter;
    private FloatingActionButton fabAdd;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_jabatan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvJabatan = findViewById(R.id.rvJabatan);
        fabAdd = findViewById(R.id.fabAdd);
        bottomNavigationView = findViewById(R.id.bottomNav);

        setupRecyclerView();
        loadDataFromRepository();

        fabAdd.setOnClickListener(v -> showAddDialog());
        setupBottomNavigation();
    }

    private void setupRecyclerView() {
        jabatanAdapter = new JabatanAdapter();
        rvJabatan.setLayoutManager(new LinearLayoutManager(this));
        rvJabatan.setAdapter(jabatanAdapter);

        // Set listener untuk klik item (edit)
        jabatanAdapter.setOnItemClickListener((jabatan, position) -> showEditDialog(jabatan, position));

        // Set listener untuk klik tombol hapus
        jabatanAdapter.setOnDeleteClickListener(this); // 'this' karena Activity mengimplement interface
    }

    private void loadDataFromRepository() {
        List<Jabatan> jabatanList = DataRepository.getInstance().getJabatanList();
        jabatanAdapter.setListJabatan(jabatanList);
    }

    // --- Method dari interface OnDeleteClickListener ---
    @Override
    public void onDeleteClick(Jabatan jabatan, int position) {
        // Tampilkan dialog konfirmasi sebelum menghapus
        new AlertDialog.Builder(this)
                .setTitle("Hapus Jabatan")
                .setMessage("Apakah Anda yakin ingin menghapus jabatan '" + jabatan.getNama() + "'?")
                .setPositiveButton("Hapus", (dialog, which) -> {
                    // Hapus dari Repository
                    DataRepository.getInstance().deleteJabatan(position);
                    // Muat ulang data
                    loadDataFromRepository();
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    private void showAddDialog() {
        // ... (kode showAddDialog tetap sama) ...
        View view = getLayoutInflater().inflate(R.layout.dialog_form_jabatan, null);
        EditText etNama = view.findViewById(R.id.etNamaJabatan);
        new AlertDialog.Builder(this).setTitle("Tambah Jabatan").setView(view)
                .setPositiveButton("Simpan", (dialog, which) -> {
                    String nama = etNama.getText().toString().trim();
                    if (!nama.isEmpty()) {
                        List<Jabatan> currentList = DataRepository.getInstance().getJabatanList();
                        int newId = currentList.size() + 1;
                        Jabatan newJabatan = new Jabatan(newId, nama);
                        DataRepository.getInstance().addJabatan(newJabatan);
                        loadDataFromRepository();
                    }
                }).setNegativeButton("Batal", null).show();
    }

    private void showEditDialog(Jabatan jabatan, int position) {
        // ... (kode showEditDialog tetap sama) ...
        View view = getLayoutInflater().inflate(R.layout.dialog_form_jabatan, null);
        EditText etNama = view.findViewById(R.id.etNamaJabatan);
        etNama.setText(jabatan.getNama());
        new AlertDialog.Builder(this).setTitle("Edit Jabatan").setView(view)
                .setPositiveButton("Simpan", (dialog, which) -> {
                    jabatan.setNama(etNama.getText().toString().trim());
                    DataRepository.getInstance().updateJabatan(position, jabatan);
                    loadDataFromRepository();
                })
                .setNeutralButton("Batal", null).show();
    }

    private void setupBottomNavigation() {
        // ... (kode setupBottomNavigation tetap sama) ...
        bottomNavigationView.setSelectedItemId(R.id.nav_jabatan);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) { startActivity(new Intent(this, MainActivity.class)); finish(); return true; }
            else if (id == R.id.nav_jabatan) { return true; }
            else if (id == R.id.nav_kandidat) { startActivity(new Intent(this, DataKandidatActivity.class)); finish(); return true; }
            else if (id == R.id.nav_hasil) {  startActivity(new Intent(this, HasilAkhirActivity.class)); finish(); return true; }
            return false;
        });
    }

    @Override
    public boolean onSupportNavigateUp() { finish(); return true; }
}