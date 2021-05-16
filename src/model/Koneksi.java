/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Widia Novita
 */
public class Koneksi {
    static String driver = "com.mysql.jdbc.Driver"; 
    static String database = "jdbc:mysql://localhost:3306/penjualan_brg"; 
    static String user = "root"; 
    static String password = "";
}