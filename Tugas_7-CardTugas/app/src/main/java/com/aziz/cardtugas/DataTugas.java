package com.aziz.cardtugas;

import java.util.ArrayList;

public class DataTugas {
    private static String[] namaTugas = {
            "Tugas 1 Membuat Screenshoor",
            "Tugas 2 Membuat Array",
            "Tugas 1 Membuat Attribute",
            "Yang penting Tampil dulu",
            "Bagus Belakangan nanti",
            "Di Imporfisasi lagi",
            "jadi Lebih Baik",
            "Selalu Belajar",
            "Jangan Menyerah",
            "Semua bisaaaa",
            "Semua Mudahhh",
            "Demi Masa Depan",
            "Semangattttt",
            "Ahmad Dahlan",
            "Ahmad Yani",
            "Sutomo",
            "Gatot Soebroto",
            "Ki Hadjar Dewantarai",
            "Mohammad Hatta",
            "Soedirman",
            "Soekarno",
            "Soepomo",
            "Tan Malaka"
    };


    static ArrayList<Tugas> getListTugas(){
        ArrayList<Tugas> list = new ArrayList<>();

        for (int pos = 0; pos < namaTugas.length; pos++){
            Tugas tugas = new Tugas();

            tugas.setNamaTugas(namaTugas[pos]);
            list.add(tugas);
        }
        return list;
    }
}
