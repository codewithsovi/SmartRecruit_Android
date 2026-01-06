package com.example.smartrecruit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HasilAkhirAdapter extends RecyclerView.Adapter<HasilAkhirAdapter.ViewHolder> {

    private List<HasilAkhirItem> listResults;

    public void setListResults(List<HasilAkhirItem> listResults) {
        this.listResults = listResults;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hasil_akhir, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HasilAkhirItem result = listResults.get(position);
        holder.tvRank.setText("#" + result.getRank());
        holder.tvCandidateName.setText(result.getCandidateName());
        holder.tvJobTitle.setText("Jabatan: " + result.getJobTitle());
        holder.tvScore.setText(String.valueOf(result.getScore()));
    }

    @Override
    public int getItemCount() {
        return listResults != null ? listResults.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi semua TextView yang ada di item_hasil_akhir.xml
        TextView tvRank, tvCandidateName, tvJobTitle, tvScore;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // --- HUBUNGKAN VARIABEL DENGAN ID DI XML ---
            // Pastikan ID ini SAMA PERSIS dengan yang ada di item_hasil_akhir.xml
            tvRank = itemView.findViewById(R.id.tvRank);
            tvCandidateName = itemView.findViewById(R.id.tvCandidateName);
            tvJobTitle = itemView.findViewById(R.id.tvJobTitle);
            tvScore = itemView.findViewById(R.id.tvScore);
        }
    }
}