package com.aziz.gits_indonesia;

import java.util.ArrayList;

public class Mahasiswa {
    private ArrayList<String> nama, nim, jurusan;


//Penggunaan Constructor pada Class Mahasiswa
    public Mahasiswa() {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

//Penggunaan GETTER untuk variable nama,nim dan jurusan
    public ArrayList<String> getNama() { return nama; }
    public ArrayList<String> getNim() {
        return nim;
    }
    public ArrayList<String> getJurusan() {
        return jurusan;
    }

//Penggunaan SETTER untuk variable nama,nim dan jurusan
    public void setNama(ArrayList<String> nama) {
        this.nama = nama;
    }
    public void setNim(ArrayList<String> nim) {
        this.nim = nim;
    }
    public void setJurusan(ArrayList<String> jurusan) {
        this.jurusan = jurusan;
    }
}
