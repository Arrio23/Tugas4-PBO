package connectdb;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RIO
 */
import java.sql.*;
import java.util.*;

public class CRUD {
    
    static final String url = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "11111";
    static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean selesai = true;
        do{
            Scanner scan = new Scanner(System.in);
            System.out.println("PILIHAN");
            System.out.println("1. Insert");
            System.out.println("2. Update");
            System.out.println("3. Delete");
            System.out.println("4. Select");
            System.out.println("5. Selesai");
            System.out.println("Pilih : ");
            int pilih = scan.nextInt();
            System.out.println();
            
            if(pilih == 1){
                insert();
            }
            else if(pilih == 2){
                update();
            }
            else if(pilih == 3){
                delete();
            }
            else if(pilih == 4){
                select();
            }
            else if(pilih == 5){
                selesai(); 
            }
            else {
                System.out.println("salah!!");
            }
        } while(selesai = true);
    }
    
    public static void insert(){
        System.out.println("Nama : ");
        String nama = sc.next();
        System.out.println("NIM : ");
        String nim = sc.next();
        System.out.println("Alamat : ");
        String alamat = sc.next();
        
        String query = "INSERT INTO mahasiswa(nama, nim, alamat) values ('" + nama + "' , '" + nim + "' , '" + alamat + "')";
        
        try {
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement stat = con.createStatement();
            stat.execute(query);
            System.out.println("Sukses...");
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal...");
        }
    }
    
    public static void update(){
        System.out.println("Nama : ");
        String nama = sc.next();
        System.out.println("Alamat : ");
        String alamat = sc.next();
        System.out.println("NIM : ");
        String nim = sc.next();
        
        String query = "UPDATE mahasiswa SET nama = '" + nama + "' , alamat = '" + alamat + "' WHERE nim = '" + nim + "'";
        
        try {
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement stat = con.createStatement();
            stat.execute(query);
            System.out.println("Sukses...");
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal...");
        }
    }
    
    public static void delete(){
        System.out.println("NIM : ");
        String nim = sc.next();
        
        String query = "DELETE FROM mahasiswa where nim = '" + nim + "'";
        
        try {
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement stat = con.createStatement();
            stat.execute(query);
            System.out.println("Sukses...");
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal...");
        }
    }
    
    public static void select(){
        String query = "SELECT * FROM mahasiswa";
        
        try {
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement stat = con.createStatement();
            stat.execute(query);
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                System.out.println("nama\t:" + " " + String.valueOf(rs.getObject(1)));
                System.out.println("nim\t:" + " " + String.valueOf(rs.getObject(2)));
                System.out.println("alamat\t:" + " " + String.valueOf(rs.getObject(3)));
                
                System.out.println();
            }
            System.out.println("Sukses...");
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal...");
        }
    }
    
    public static void selesai(){
        System.exit(0);
    }
    
}
