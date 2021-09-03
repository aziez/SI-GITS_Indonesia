import java.util.ArrayList;
import java.util.Scanner;

public class Todo {
//Deklarasi Variable todoList Sebagai ArrayList
    static ArrayList<String> todolist = new ArrayList<String>();
    static boolean edited = false;
    public static Scanner input;

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
    static void Menu(){
        int pilihan;
        input = new Scanner(System.in);
        System.out.println("\t TODO LIST APP \t");
        System.out.println("1. Lihat Todo List");
        System.out.println("2. Tambah Todo List");
        System.out.println("3. Edit Todo List");
        System.out.println("4. Hapus Todo List");
        System.out.println("0. Keluar kau");

        System.out.print("Pilih = ");
        pilihan = input.nextInt();

//IF ELSE Statment
        if (pilihan == 1){
            listTodo.list();
        }else if(pilihan == 2){
            listTodo.add();
        }else if (pilihan == 3){
            listTodo.edit();
        }else if (pilihan == 4){
            listTodo.hapus();
        }else if (pilihan == 0){
            System.exit(0);
        }else{
            kembali("Anda Salah Memilih Menu");
        }

    }
//Main Function, dimana Semua code didalam fungsi ini akan di eksekusi
    public static void main(String[] args) {
        Menu();
    }

}

//Class ListTodo Sebagai turunan dari class Todo
class listTodo extends Todo{
   public static void list(){

       try {
           if (todolist.size() > 0) {
               System.out.println("DAFTAR TUGAS : ");
               int index = 0;
//PERULANGAN FOREACH dimana variable data akan menampung banyaknya looping yang diambil dari variable todoList.
               for (String data : todolist) {
                   System.out.println(String.format("[%d] %s", index, data));
                   index++;
               }
           }else if (!edited){
               kembali("DATA MASIH KOSONG !!!");
           };
       }catch (Exception e){
            System.err.print(e);
       }
       kembali("");


   }

//FUNGSI Tambah Array
    public static void add() {
       String dumps;
       System.out.print("Nama Tugas : ");
       input.nextLine();
       dumps = input.nextLine();
       todolist.add(dumps);
       kembali("Tugas "+dumps+" Berhasil Di Tambah");

    }

//Fungsi untuk edit ArrayList
    public static void edit() {
       int dump;
       String dumps;
       System.out.println("Silahkan Pilih Index data ke-");
       System.out.print("Index Ke = ");
       dump = input.nextInt();
       try {
               System.out.print("");
               dumps = input.nextLine();
               if (dumps.isBlank() && dumps.isEmpty()){
                   System.out.print("Input Tugas : ");
               }
               dumps = input.nextLine();
               todolist.set(dump, dumps);
               kembali("Data Berhasil Dirubah");

       }catch (IndexOutOfBoundsException e){
           kembali("Index Yang Anda Input Salah");
       }
       }

//Fungsi untuk Hapus Array List
    public static void hapus() {
            int dump;
            System.out.print("Index Ke = ");
            dump = input.nextInt();
            try {
                todolist.remove(dump);
                kembali("Data Berhasil Hapus");

            }catch (IndexOutOfBoundsException e){
                kembali("Index Yang Anda Input Salah");
            }
    }
}

