import java.sql.*;
import java.util.Scanner;

public class Main {
    // Informasi koneksi database
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://vultr-prod-d4ab89e8-a895-462c-ba44-3f247d71dd4d-vultr-prod-f0bb.vultrdb.com:16751/kampus";
    static final String USER = "vultradmin";
    static final String PASS = "AVNS__kHdMYl9eQu-19FTCv_";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Mendaftarkan driver JDBC
            Class.forName(JDBC_DRIVER);
            // Membuat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // Membuat objek Scanner untuk input pengguna
            Scanner scanner = new Scanner(System.in);
            int choice;

            // Menampilkan Menu Utama
            do {
                System.out.println("======= Menu Utama =======");
                System.out.println("1. Input Data");
                System.out.println("2. Tampil Data");
                System.out.println("3. Update Data");
                System.out.println("0. Keluar");
                System.out.print("\nPilihan> ");

                // Membaca pilihan pengguna
                choice = scanner.nextInt();

                // Memproses pilihan pengguna
                switch (choice) {
                    case 1:
                        inputData(conn, stmt);
                        break;
                    case 2:
                        tampilData(stmt);
                        break;
                    case 3:
                        updateData(conn, stmt);
                        break;
                    case 0:
                        System.out.println("Keluar dari menu. Program berakhir.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }

            } while (choice != 0);

            // Menutup Scanner setelah selesai digunakan
            scanner.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Menutup statement
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {}
            try {
                // Menutup koneksi
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    // Memasukkan Data ke Tabel
    private static void inputData(Connection conn, Statement stmt) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("======= Input Data =======");
        // Meminta pengguna memasukkan Nama
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        // Meminta pengguna memasukkan Alamat
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();

        try {
            // Membuat SQL untuk memasukkan data ke tabel mahasiswa
            String sql = "INSERT INTO mahasiswa (nama, alamat) VALUES ('" + nama + "', '" + alamat + "')";
            // Menjalankan pernyataan SQL
            stmt.executeUpdate(sql);
            System.out.println("Data berhasil dimasukkan ke dalam tabel.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal memasukkan data ke dalam tabel.");
        }
    }

    // Menampilkan Data dari Tabel
    private static void tampilData(Statement stmt) {
        try {
            // Membuat SQL untuk mendapatkan semua data dari tabel mahasiswa
            String sql = "SELECT * FROM mahasiswa";
            // Menjalankan pernyataan SQL dan mendapatkan hasilnya
            ResultSet rs = stmt.executeQuery(sql);

            // Menampilkan hasil ke layar
            System.out.println("======= Data Mahasiswa =======");
            System.out.printf("%-5s %-20s %-20s\n", "ID", "Nama", "Alamat");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String alamat = rs.getString("alamat");
                System.out.printf("%-5d %-20s %-20s\n", id, nama, alamat);
            }
            // Menutup ResultSet setelah selesai digunakan
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menampilkan data dari tabel.");
        }
    }

    // Mengupdate Data dalam Tabel
    private static void updateData(Connection conn, Statement stmt) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("======= Update Data =======");
        // Meminta pengguna memasukkan ID data yang akan di-update
        System.out.print("Masukkan ID data yang akan di-update: ");
        int idToUpdate = scanner.nextInt();
        // Membuang newline setelah nextInt()
        scanner.nextLine();

        try {
            // Membuat SQL untuk mendapatkan data dengan ID yang diberikan
            String sql = "SELECT * FROM mahasiswa WHERE id = " + idToUpdate;
            // Menjalankan pernyataan SQL dan mendapatkan hasilnya
            ResultSet rs = stmt.executeQuery(sql);

            // Memeriksa apakah data ditemukan
            if (rs.next()) {
                System.out.println("Data yang akan di-update:");
                System.out.printf("%-5s %-20s %-20s\n", "ID", "Nama", "Alamat");
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String alamat = rs.getString("alamat");
                System.out.printf("%-5d %-20s %-20s\n", id, nama, alamat);

                // Meminta pengguna memasukkan data baru
                System.out.print("Masukkan nama baru: ");
                String newNama = scanner.nextLine();
                System.out.print("Masukkan alamat baru: ");
                String newAlamat = scanner.nextLine();

                // Membuat SQL untuk mengupdate data dalam tabel
                sql = "UPDATE mahasiswa SET nama = '" + newNama + "', alamat = '" + newAlamat + "' WHERE id = " + idToUpdate;
                // Menjalankan pernyataan SQL untuk update
                stmt.executeUpdate(sql);
                System.out.println("Data berhasil di-update.");
            } else {
                System.out.println("Data dengan ID " + idToUpdate + " tidak ditemukan.");
            }

            // Menutup ResultSet setelah selesai digunakan
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal melakukan update data.");
        }
    }
}
