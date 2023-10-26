/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connectdb;

import java.util.*;
import java.sql.*;

/**
 *
 * @author RIO
 */




public class ConnectDB {
    
  
    
    
    Scanner sc = new Scanner(System.in);
    
    private String nama;
    private String nim;
    private String alamat;
    
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "123";
    public static String select = "SELECT * FROM mahasiswa";
    public static String insert = "INSERT INTO mahasiswa values(?, ?, ?)";
    public static String delete = "DELETE FROM mahasiswa where = '?'";

    public static void main(String[] args) {
        // Open a connection
        Scanner sc = new Scanner(System.in);
        
        int pilih = sc.nextInt();
        
        if(pilih == 1){
            try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(select);) {
                while (rs.next()) {
                    String qr = "SELECT * FROM mahasiswa";
                    PreparedStatement ps = conn.prepareCall(qr);
                    ps.setString(1, qr);
                    //Display values
                    System.out.print("nim: " + String.valueOf(rs.getObject(1)));
                    System.out.print(", nama: " + String.valueOf(rs.getObject(2)));
                    System.out.print(", alamat: " + String.valueOf(rs.getObject(3)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the nim
     */
    public String getNim() {
        return nim;
    }

    /**
     * @param nim the nim to set
     */
    public void setNim(String nim) {
        this.nim = nim;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}