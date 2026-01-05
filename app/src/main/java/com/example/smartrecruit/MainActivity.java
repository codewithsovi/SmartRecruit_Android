package com.example.smartrecruit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) { return true; }
            else if (itemId == R.id.nav_jabatan) { startActivity(new Intent(this, DataJabatanActivity.class)); return true; }
            else if (itemId == R.id.nav_kandidat) { startActivity(new Intent(this, DataKandidatActivity.class)); return true; }
            else if (itemId == R.id.nav_hasil) { return true; } // TODO: Implement
            return false;
        });
    }
}