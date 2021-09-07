package com.aziz.gits_indonesia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
//Deklarasi arrayList dengan type data String pada Variable kataKunci dan jawaban
    public static ArrayList<String> kataKunci = new ArrayList<>();
    public static ArrayList<String> jawaban = new ArrayList<>();

//deklarasi ARRAY untuk memasukan String;
    public static String[] jawab = new String[]{"<!DOCTYPE>","<title>","<body>","<h1>","<p>","<br>","<b>","<a>","<nav>","<ul>","<ol>","<li>","<table>","<th>","<tr>","<td>","<header>","<footer>"};
    public static String[] kunci = new String[]{"Type dokumen","membuat Judul","Membuat body Halaman","Heading Terbesar","Paragraf","Membuat Barir Baru","Teks Tebal","Hyperlink","Membuat Navigasi","membuat daftar dengan simbol","membuat daftar dengan nomor","membuat daftar List","membuat Table","Membuat Judul Tabel","Membuat Baris Table","Membuat Cell Tabel","Membuat Header","Membuat Footer"};

    public static Scanner input = new Scanner(System.in);

//FUNGSI UTAMA
    public static void main(String[] args) {
        menu();
    }

 //FUNGSI MENU
   private static void  menu(){
//Memasuukan isi dari array ke ArrayList dengan sekaligus
        kataKunci.addAll(Arrays.asList(kunci));
        jawaban.addAll(Arrays.asList(jawab));
        int index = 0;

        System.out.println("\t TEBAK TAGS HTML");
        System.out.println("\t Kata Kunci : ");
//Perulangan Foreach Statment sesuai untuk menampilkan isi dari ArrayList katakunci
        for (String ask : kataKunci){
            System.out.printf(String.format("%s  %s%n", index, ask));
            index++;
        }
       mulaiPertanyaan();
    }

    private static void mulaiPertanyaan() {
        int mundur = 5;

        System.out.println("Mulai Permainan [Y/T]");
        System.out.print("Jawab : ");
        String mulai = input.nextLine();

        if (mulai.equalsIgnoreCase("Y")){
            try {
                System.out.println("mengacak Pertanyaan ");
                while (mundur > 0){
                    System.out.print("dimulai dalam "+mundur+"\n");
                    mundur--;
                    Thread.sleep(500);
                }
            }catch (InterruptedException e){}

            cariKata();
        }else{
            System.out.println("Keluar");
            System.exit(1);
        }
    }

    private static void cariKata() {
        Random acak = new Random();
        int indexAcak = acak.nextInt(kataKunci.size());
        String pertayaan;

        pertayaan = kataKunci.get(indexAcak);;
        System.out.println(pertayaan);

        System.out.print("Jawab : ");
        String jawab = input.nextLine();

        if(jawaban.contains(jawab)){
         int index = jawaban.indexOf(jawab);

            if (index == indexAcak){
                System.out.println("BENAR...");
                System.out.println(pertayaan + " adalah " +jawab);

                System.out.println("Mulai Lagi...");
                mulaiPertanyaan();

            }else{
                System.out.println("JAWABAN ANDA SALAH !!!");
                System.out.println("Ulangi ya...");
                mulaiPertanyaan();
            }
        }else{
            System.out.println("YAHHH...JAWABAN ANDA SALAH !!!");
            System.out.println("Ulangi ya...");
            mulaiPertanyaan();
        }


    }
}
