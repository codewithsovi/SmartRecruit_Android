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

    // ===== INTERFACE KLIK ITEM =====
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

    // ===== CONSTRUCTOR TANPA PARAMETER (WAJIB ADA) =====
    public JabatanAdapter() {
        this.listJabatan = new ArrayList<>();
    }

    // ===== SET DATA DARI LUAR =====
    public void setListJabatan(List<Jabatan> listJabatan) {
        this.listJabatan = listJabatan;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JabatanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jabatan, parent, false);
        return new JabatanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JabatanViewHolder holder, int position) {
        Jabatan jabatan = listJabatan.get(position);
        holder.tvNamaJabatan.setText(jabatan.getNama());
    }

    @Override
    public int getItemCount() {
        return listJabatan == null ? 0 : listJabatan.size();
    }

    class JabatanViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaJabatan;
        Button btnDelete;

        public JabatanViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaJabatan = itemView.findViewById(R.id.tvNamaJabatan);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            // Klik item (edit / buka detail)
            itemView.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClick(
                                listJabatan.get(position),
                                position
                        );
                    }
                }
            });

            // Klik hapus
            btnDelete.setOnClickListener(v -> {
                if (deleteClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        deleteClickListener.onDeleteClick(
                                listJabatan.get(position),
                                position
                        );
                    }
                }
            });
        }
    }
}
