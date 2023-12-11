public class Main {
  public static void main(String[] args) {
    Matematika matematika1 = new Segitiga(5, 10);
    Matematika matematika2 = new PersegiPanjang(6, 6);

    //Menampilkan Hasil
    System.out.println("Luas segitiga: " + matematika1.luas());
    System.out.println("Luas persegi panjang: " + matematika2.luas());
  }
}