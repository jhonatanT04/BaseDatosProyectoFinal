/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.beans.Statement;
import java.sql.Connection;

/**
 *
 * @author venot
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author RICHARD
 */
public class Conexion {
    
    private static String user = "Supermercado"; 
    private static String password = "super_123"; 
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String driver = "oracle.jdbc.driver.OracleDriver";
    
    private Connection conn;
    
    public Conexion (){
        this.conn = null;
    }
    
    public Connection conectar() {
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
           //System.out.println("No Conectado");
            System.exit(0);
        }
        return this.conn;

    }

    public void desconectar() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
