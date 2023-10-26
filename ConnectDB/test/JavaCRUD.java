import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class JavaCRUD {

    
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "11111";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
           

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Insert Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertBuku();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updateBuku();
                    break;
                case 4:
                    deleteBuku();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showData() {
        String sql = "SELECT * FROM buku";

        try {
            rs = stmt.executeQuery(sql);
            
            System.out.println("+--------------------------------+");
            System.out.println("|    DATA BUKU DI PERPUSTAKAAN   |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                int nama = rs.getInt("nama");
                String nim = rs.getString("nim");
                String alamat = rs.getString("alamat");

                
                System.out.println(String.format("%d. %s -- (%s)", nama, nim, alamat));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insertBuku() {
        try {
            // ambil input dari user
            System.out.print("nama: ");
            String nama = input.readLine().trim();
            System.out.print("alamat: ");
            String alamat = input.readLine().trim();
            System.out.print("nim: ");
            String nim = input.readLine().trim();
 
            // query simpan
            String sql = "INSERT INTO buku (nama, alamat, nim) VALUE('%s', '%s', %s)";
            sql = String.format(sql, nama, alamat, nim);

            // simpan buku
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateBuku() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang mau diedit: ");
            int alamat = Integer.parseInt(input.readLine());
            System.out.print("Judul: ");
            String nama = input.readLine().trim();
            System.out.print("nim: ");
            String nim = input.readLine().trim();

            // query update
            String sql = "UPDATE buku SET alamat='%s', nama='%s' WHERE nim=%d";
            sql = String.format(sql, alamat, nama, nim);

            // update data buku
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteBuku() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang mau dihapus: ");
            int nim = Integer.parseInt(input.readLine());
            
            // buat query hapus
            String sql = String.format("DELETE FROM buku WHERE nim=%d", nim);

            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
