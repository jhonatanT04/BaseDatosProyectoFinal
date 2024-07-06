/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Producto.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class DAOCategoria {
    public boolean insertarCategoria(Categoria categoria) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "INSERT INTO super_categorias (cat_codigo, cat_nombre) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(categoria.getCodigo())); 
            pstmt.setString(2, categoria.getNombre());

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

    public void buscarCategoria(String nombre) {
        List<Categoria> categorias = new ArrayList<>();

        if (nombre != null) {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conectar();

            String sql = "SELECT * FROM super_categorias WHERE cat_nombre = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, nombre);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Categoria encontrado:");
                    System.out.println("Código: " + rs.getInt("cat_codigo"));
                    System.out.println("Nombre: " + rs.getString("cat_nombre"));
                    //Cliente cli = new Cliente(12, 's', 23, "0102", "Ania", "Perez", "La casa", "0999999999", "venotacu@gmail.com");

                    //Cliente cli = new Cliente(rs.getInt("cli_codigo"),rs.getString("cli_visualizar"));
                } else {
                    System.out.println("No se encontró ningún cliente con la cédula " + nombre);
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            } finally {
                conexion.desconectar();
            }
        } else {

        }

    }

    public boolean actualizarCategoria(Categoria categoria) {
        boolean llave = false;
        try {
            System.out.println("Actualizando producto: " + categoria);

            String SQLr = "UPDATE super_categorias SET cat_nombre=? WHERE cat_nombre=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setString(1, categoria.getNombre());
            sentencia.setInt(2, categoria.getCodigo()); 

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se actualizó correctamente el producto");
                llave = true;
            } else {
                System.out.println("No se pudo actualizar el producto");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

    public boolean eliminarCategoria(String nombre) {
        boolean llave = false;
        try {
            String SQLr = "DELETE FROM super_categorias WHERE cat_nombre=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setString(1, nombre);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se eliminó correctamente el producto " + nombre);
                llave = true;
            } else {
                System.out.println("No se pudo eliminar el producto con el nombre " + nombre + " (No existe en la base de datos)");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }
}
