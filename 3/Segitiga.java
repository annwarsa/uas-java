public class Segitiga extends Matematika {
  private double alas;
  private double tinggi;

  public Segitiga(double alas, double tinggi) {
    this.alas = alas;
    this.tinggi = tinggi;
  }

  @Override
  public double luas() {
    return 0.5 * alas * tinggi;
  }
}