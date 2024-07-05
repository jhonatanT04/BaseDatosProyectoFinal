/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Personas.Persona.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author venot
 */
public class DAOCliente {
    
    public boolean insertarCliente(Cliente cliente) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        
        String sql = "INSERT INTO super_clientes (cli_nombre, cli_apellido, cli_cedula, cli_direccion, cli_telefono, cli_correo_electronico, cli_visualizar) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getCedula());
            pstmt.setString(4, cliente.getDireccion());
            pstmt.setString(5, cliente.getTelefono());
            pstmt.setString(6,cliente.getCorreo());
            pstmt.setString(7, String.valueOf(cliente.getVisualizacion()));
            
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
            return true;
        }
    }
    public void buscaCliente(String cedula){
        List<Cliente> clientes = new ArrayList<>();
        
        if (cedula!=null) {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conectar();
        
            String sql = "SELECT * FROM super_clientes WHERE cli_cedula = ?";
        
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, cedula);
                ResultSet rs = pstmt.executeQuery();
            
                    if (rs.next()) {
                        System.out.println("Cliente encontrado:");
                        System.out.println("Código: " + rs.getInt("cli_codigo"));
                        System.out.println("Nombre: " + rs.getString("cli_nombre"));
                        System.out.println("Apellido: " + rs.getString("cli_apellido"));
                        System.out.println("Cédula: " + rs.getString("cli_cedula"));
                        System.out.println("Dirección: " + rs.getString("cli_direccion"));
                        System.out.println("Teléfono: " + rs.getString("cli_telefono"));
                        System.out.println("Correo electrónico: " + rs.getString("cli_correo_electronico"));
                        System.out.println("Visualizar: " + rs.getString("cli_visualizar"));
                        System.out.println("Persona Codigo : " + rs.getString("per_codigo"));
                        //Cliente cli = new Cliente(12, 's', 23, "0102", "Ania", "Perez", "La casa", "0999999999", "venotacu@gmail.com");
                
                        //Cliente cli = new Cliente(rs.getInt("cli_codigo"),rs.getString("cli_visualizar"));
                    } else {
                        System.out.println("No se encontró ningún cliente con la cédula " + cedula);
                    }
            
                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            } finally {
            conexion.desconectar();
            }
        }else{
            
        }
        
    }
    /*
    public void buscarClientePorCedula(String cli_cedula) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        
        String sql = "SELECT * FROM super_clientes WHERE cli_cedula = ?";
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cli_cedula);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("Cliente encontrado:");
                System.out.println("Código: " + rs.getInt("cli_codigo"));
                System.out.println("Nombre: " + rs.getString("cli_nombre"));
                System.out.println("Apellido: " + rs.getString("cli_apellido"));
                System.out.println("Cédula: " + rs.getString("cli_cedula"));
                System.out.println("Dirección: " + rs.getString("cli_direccion"));
                System.out.println("Teléfono: " + rs.getString("cli_telefono"));
                System.out.println("Correo electrónico: " + rs.getString("cli_correo_electronico"));
                System.out.println("Visualizar: " + rs.getString("cli_visualizar"));
            } else {
                System.out.println("No se encontró ningún cliente con la cédula " + cli_cedula);
            }
            
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }*/
}
