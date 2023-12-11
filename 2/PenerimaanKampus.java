public class PenerimaanKampus {
    public static void main(String[] args) {
        
        //Nilai Tono
        int nilaiTono = 80;

        //Cek Kampus yang akan menerima tono
        if (nilaiTono > 81) {
            System.out.println("Tono diterima di kampus A");
        } else if (nilaiTono >= 71 && nilaiTono <= 80) {
            System.out.println("Tono diterima di kampus B");
        } else if (nilaiTono >= 61 && nilaiTono <= 70) {
            System.out.println("Tono diterima di kampus C");
        } else {
            System.out.println("Tono diterima di kampus D");
        }
    }
}
