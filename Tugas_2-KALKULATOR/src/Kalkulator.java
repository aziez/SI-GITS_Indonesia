import java.util.Scanner;

//Nama : Muhamad Abdul Aziz
//Asal : Universitas Pamulang
//Tugas Studi Independent PT.Gits Indonesia
//Log : Java Pemula Dengan Intelij

public class Kalkulator {
    public static Scanner input;
    public static int operator,nilai1,nilai2;
    public static boolean validasi = false;

    public static final String BIRU = "\u001b[34m";

    public static void main(String[] args) {
        input = new Scanner(System.in);

        System.out.println("APLIKASI KALKULATOR");
        System.out.println("--------------------");
        System.out.println("----Tugas 02 SI-----");

        do {
            System.out.println("\t Pilih Operator \t");
            System.out.println("1.Penambahan");
            System.out.println("2.Pengurangan");
            System.out.println("3.Perkalian");
            System.out.println("4.Pembagian");
            System.out.print("Pilihan = ");
            operator = input.nextInt();

            switch (operator) {
                default:
                    System.out.println("Anda Harus Menmasukan Angka 1-4");
                    validasi = false;
                    break;
                case 1:
                    validasi = true;
                    penambahan();
                    break;
                case 2:
                    validasi = true;
                    pengurangan();
                    break;
                case 3:
                    validasi = true;
                    perkalian();
                    break;
                case 4:
                    validasi = true;
                    penmbagian();
                    break;
            }
        }while ( validasi == false);
    }


    private static void penmbagian() {
        System.out.print("Angka Ke-1 = ");
        nilai1 = input.nextInt();

        System.out.print("Angka Ke-2 = ");
        nilai2 = input.nextInt();

        float hasil = (float) nilai1 / (float) nilai2;
        System.out.print(BIRU+"Hasil "+nilai1+"/"+nilai2+"="+hasil);
    }

    private static void perkalian() {
        System.out.print("Angka Ke-1 = ");
        nilai1 = input.nextInt();

        System.out.print("Angka Ke-2 = ");
        nilai2 = input.nextInt();

        int hasil = nilai1 * nilai2;
        System.out.print(BIRU+"Hasil "+nilai1+"*"+nilai2+"="+hasil);
    }

    private static void pengurangan() {
        System.out.print("Angka Ke-1 = ");
        nilai1 = input.nextInt();

        System.out.print("Angka Ke-2 = ");
        nilai2 = input.nextInt();

        int hasil = nilai1 - nilai2;
        System.out.print(BIRU+"Hasil "+nilai1+"/"+nilai2+"="+hasil);
    }

    private static void penambahan() {
        System.out.print("Angka Ke-1 = ");
        nilai1 = input.nextInt();

        System.out.print("Angka Ke-2 = ");
        nilai2 = input.nextInt();

        int hasil = nilai1 + nilai2;
        System.out.print(BIRU+"Hasil "+nilai1+"+"+nilai2+"="+hasil);
    }

}
