package com.example.smartrecruit;

public class HasilAkhirItem {
    // Variabel-variabel ini bersifat private
    private int rank;
    private String candidateName;
    private String jobTitle;
    private int score;

    public HasilAkhirItem(int rank, String candidateName, String jobTitle, int score) {
        this.rank = rank;
        this.candidateName = candidateName;
        this.jobTitle = jobTitle;
        this.score = score;
    }

    // --- GETTERS (untuk mengambil nilai) ---
    public int getRank() {
        return rank;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getScore() {
        return score;
    }

    // --- SETTER (untuk mengubah nilai) ---
    // TAMBAHKAN METHOD INI. Ini adalah kunci perbaikannya.
    public void setRank(int rank) {
        this.rank = rank;
    }
}