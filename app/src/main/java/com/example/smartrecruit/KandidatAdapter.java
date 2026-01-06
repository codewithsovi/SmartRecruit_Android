package com.example.smartrecruit;

import android.view.*;
import android.widget.ImageButton; // Tambahkan import ini
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class KandidatAdapter extends RecyclerView.Adapter<KandidatAdapter.ViewHolder> {

    private List<Kandidat> list = new ArrayList<>();
    private OnActionListener listener;

    public interface OnActionListener {
        void onEdit(Kandidat kandidat);
        void onDelete(Kandidat kandidat);
    }

    public void setOnActionListener(OnActionListener listener) {
        this.listener = listener;
    }

    public void setListKandidat(List<Kandidat> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kandidat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int p) {
        Kandidat k = list.get(p);

        h.nama.setText(k.getNama());
        h.tulis.setText(String.valueOf(k.getTesTulis()));
        h.wawancara.setText(String.valueOf(k.getTesWawancara()));
        h.kesehatan.setText(String.valueOf(k.getTesKesehatan()));
        h.keterampilan.setText(String.valueOf(k.getTesKeterampilan()));

        int total = (k.getTesTulis() + k.getTesWawancara()
                + k.getTesKesehatan() + k.getTesKeterampilan()) / 4;
        h.total.setText(String.valueOf(total));

        // Aksi untuk edit (klik pada item)
        h.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEdit(k);
            }
        });

        // --- PERUBAHAN: Aksi untuk hapus (klik pada tombol hapus) ---
        h.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDelete(k);
            }
        });
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, tulis, wawancara, kesehatan, keterampilan, total;
        ImageButton btnDelete; // Tambahkan ini

        ViewHolder(View v) {
            super(v);
            nama = v.findViewById(R.id.tvNamaKandidat);
            tulis = v.findViewById(R.id.tvTesTulis);
            wawancara = v.findViewById(R.id.tvTesWawancara);
            kesehatan = v.findViewById(R.id.tvTesKesehatan);
            keterampilan = v.findViewById(R.id.tvTesKeterampilan);
            total = v.findViewById(R.id.tvTotalScore);
            btnDelete = v.findViewById(R.id.btnDelete); // Dan ini
        }
    }
}