package com.aziz.gits_indonesia;

import java.util.ArrayList;
import java.util.Scanner;

//Class Main yang mewarisi class Mahasiswa (Inheritance)
public class Main extends Mahasiswa {
//Deklarasi ArrayList untuk menyimpan data sementara
    public static ArrayList<String> namaDump = new ArrayList<>();
    public static ArrayList<String> nimDump = new ArrayList<>();
    public static ArrayList<String> jurusanDump = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);

    public static Mahasiswa mhs = new Mahasiswa();

//Fungsi untuk kembali ke menu
    static String kembali(String pesan){
        input = new Scanner(System.in);
        System.out.print(pesan);
        System.out.println("\n[ENTER]");
        input.nextLine();
        Menu();

        return pesan;
    }

//Fungsi Menu Utama untuk memilih
    static void Menu() {
        int pilihan;
        input = new Scanner(System.in);
        System.out.println("\t DATA MAHASISWA \t");
        System.out.println("1. Lihat Mahasiswa");
        System.out.println("2. Tambah Mahasiswa");
        System.out.println("3. Edit Mahasiswa");
        System.out.println("4. Hapus Mahasiswa");
        System.out.println("0. Keluar");

        System.out.print("Pilih = ");
        pilihan = input.nextInt();

        switch (pilihan) {
            case 1 :
                tampilMhs();
                break;
            case 2:
                addMhs();
                break;
            case 3:
                editMhs();
                break;
            case 4:
                hpsMhs();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                kembali("");
                break;

        }


    }

//FUNGSI Menampilkan Data Mahasiswa
    private static void tampilMhs() {
        int index = 0;
        System.out.println(String.format("%s %s %s %s", "No | ", "Nama | ", "Nim | ", "Jurusan" ));
           try {
               if (mhs.getNama().size() > 0){
                   for (index = 0; index < mhs.getNama().size(); index++) {
                       System.out.println(String.format("[%d] [%s] [%s] [%s]", index, mhs.getNama().get(index), mhs.getNim().get(index), mhs.getJurusan().get(index)));
                   }
               }else {
                   kembali("Mahasiswa masih Kosong !!");
               }

           }catch (NullPointerException e){
               kembali("mahasiswa Masih kosong");

        }
           kembali("");
    }

//FUNGSI UNTUK MENAMBAHKAN DATA MAHASISWA
    private static void addMhs() {
        input.nextLine();
        int index = namaDump.size();

        System.out.print("Nama : ");
        namaDump.add(input.nextLine());
        //Penggunaan Setter untuk Variable Nama
        mhs.setNama(namaDump);

        System.out.print("NIM : ");
        nimDump.add(input.nextLine());
        //Penggunaan Setter untuk Variable NIM
        mhs.setNim(nimDump);

        System.out.print("Jurusan : ");
        jurusanDump.add(input.nextLine());
        //Penggunaan Setter untuk Variable Jurusan
        mhs.setJurusan(jurusanDump);

        //Penggunaan Getter Untuk Variable Nama dan mengambil index data ke -
        kembali("Data Mahasiswa " + mhs.getNama().get(index
        ) + " Berhasil di Input");

    }

//FUNGSI EDIT DATA MAHASISWA
    private static void editMhs() {
        System.out.print("Inpit Index : ");
        int index =  input.nextInt();

        System.out.println("");
        input.nextLine();

        System.out.println("Nama "+ mhs.getNama().get(index) + " edit");
        System.out.print("nama : ");
        String nama = input.nextLine();
        if (nama.isBlank() && nama.isEmpty()){
            nama = mhs.getNama().get(index);
            namaDump.set(index, nama);
        }else{
            namaDump.set(index, nama);
            mhs.setNama(namaDump);

        }

        System.out.print("NIM : ");
        String nim = input.nextLine();
        if (nim.isEmpty() && nim.isBlank()){
            nim = mhs.getNim().get(index);
            nimDump.set(index, nim);
        }else{
            nimDump.set(index, nim);
            mhs.setNim(nimDump);
        }


        System.out.print("Jurusan : ");
        String jrs = input.nextLine();

        if (jrs.isBlank() && jrs.isEmpty()){
            jrs = mhs.getJurusan().get(index);
            jurusanDump.set(index, jrs);
        }else{
            jurusanDump.set(index, jrs);
            mhs.setJurusan(jurusanDump);
        }

        tampilMhs();


    }

//FUNGSI MENGHAPUS DATA MAHASISWA
    private static void hpsMhs() {
        System.out.print("Inpit Index : ");
        int index =  input.nextInt();

        System.out.println("");
        input.nextLine();

        System.out.print("YAKIN Data "+ mhs.getNama().get(index) + " HAPUS ! [Y/T]");
        String konfirmasi = input.nextLine();

        if (konfirmasi.isEmpty() && konfirmasi.isBlank()){
            tampilMhs();
        }else if (konfirmasi.equalsIgnoreCase("Y")){
            namaDump.remove(index);
            kembali("BERHASIL MENGHAPUS");
            tampilMhs();

        }else{
            kembali("Batal Menghapus !");
        }

    }

//FUNGSI MAIN ATAU FUNGSI UTAMA UNTUK MENJALANKAN PROGRAM
    public static void main(String[] args) {

        Menu();

        }
    }
