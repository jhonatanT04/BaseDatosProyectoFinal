/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Personas.Persona.Empleado;
import Modelo.Producto.Producto;
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
public class DAOProducto {

    public boolean insertarProducto(Producto producto) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "INSERT INTO super_productos (pro_nombre, pro_precio, pro_stock, pro_IVA, pro_visualizar) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, String.valueOf(producto.getPrecio()));
            pstmt.setString(3, String.valueOf(producto.getStock()));
            pstmt.setString(4, String.valueOf(producto.getIva()));
            pstmt.setString(5, String.valueOf(producto.getVisualizacion()));

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

    public void buscarProducto(String nombre) {
        List<Producto> productos = new ArrayList<>();

        if (nombre != null) {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conectar();

            String sql = "SELECT * FROM super_productos WHERE pro_nombre = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, nombre);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Producto encontrado:");
                    System.out.println("Código: " + rs.getInt("pro_codigo"));
                    System.out.println("Nombre: " + rs.getString("pro_nombre"));
                    System.out.println("Precio: " + rs.getString("pro_precio"));
                    System.out.println("Stock: " + rs.getString("pro_stock"));
                    System.out.println("IVA: " + rs.getString("pro_IVA"));
                    System.out.println("Visualizar: " + rs.getString("pro_visualizar"));
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

    public boolean actualizarProducto(Producto producto) {
        boolean llave = false;
        try {
            System.out.println("Actualizando producto: " + producto);

            String SQLr = "UPDATE super_productos SET pro_nombre=?, super_categorias_cat_codigo=?, pro_precio=?, pro_stock=?, pro_iva=?, pro_visualizar=? WHERE pro_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setString(1, producto.getNombre());
            sentencia.setInt(2, producto.getCategoria());
            sentencia.setDouble(3, producto.getPrecio());
            sentencia.setInt(4, producto.getStock());
            sentencia.setDouble(5, producto.getIva());
            sentencia.setString(6, String.valueOf(producto.getVisualizacion()));
            sentencia.setInt(7, producto.getCodigo()); 

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

    public boolean eliminarProducto(String nombre) {
        boolean llave = false;
        try {
            String SQLr = "DELETE FROM super_productos WHERE pro_nombre=?";
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
