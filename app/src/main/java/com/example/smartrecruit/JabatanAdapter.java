package com.example.smartrecruit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class JabatanAdapter extends RecyclerView.Adapter<JabatanAdapter.JabatanViewHolder> {

    private List<Jabatan> listJabatan;
    private OnItemClickListener itemClickListener;
    private OnDeleteClickListener deleteClickListener;

    // ===== INTERFACE KLIK ITEM (UNTUK EDIT) =====
    public interface OnItemClickListener {
        void onItemClick(Jabatan jabatan, int position);
    }

    // ===== INTERFACE KLIK DELETE =====
    public interface OnDeleteClickListener {
        void onDeleteClick(Jabatan jabatan, int position);
    }

    // ===== SETTER LISTENER =====
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    // ===== CONSTRUCTOR =====
    public JabatanAdapter() {
        this.listJabatan = new ArrayList<>();
    }

    // ===== METHOD UNTUK MEMPERBARUI DATA =====
    public void setListJabatan(List<Jabatan> listJabatan) {
        this.listJabatan = listJabatan;
        notifyDataSetChanged(); // Memberitahu RecyclerView bahwa data berubah
    }

    @NonNull
    @Override
    public JabatanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Membuat View baru dari layout item_jabatan.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jabatan, parent, false);
        return new JabatanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JabatanViewHolder holder, int position) {
        // Mengambil data Jabatan berdasarkan posisi
        Jabatan jabatan = listJabatan.get(position);

        // --- TAMBAHKAN LOGIKA DI SINI ---

        // 1. Set data ke View (nama jabatan)
        holder.tvNamaJabatan.setText(jabatan.getNama());

        // 2. Atur aksi untuk klik pada item (untuk edit)
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(jabatan, position);
            }
        });

        // 3. Atur aksi untuk klik pada tombol hapus
        holder.btnDelete.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDeleteClick(jabatan, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Mengembalikan jumlah item, atau 0 jika list null
        return listJabatan == null ? 0 : listJabatan.size();
    }

    // ===== VIEW HOLDER: Menyimpan referensi View =====
    // Class ini menjadi lebih bersih karena hanya bertugas menyimpan View
    public static class JabatanViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaJabatan;
        Button btnDelete;

        public JabatanViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inisialisasi View dari layout
            tvNamaJabatan = itemView.findViewById(R.id.tvNamaJabatan);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}