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
        String sql = "INSERT INTO super_productos (pro_codigo, pro_nombre, pro_precio, pro_stock, pro_IVA, pro_visualizar, super_categorias_cat_codigo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, producto.getCodigo());
            pstmt.setString(2, producto.getNombre());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setInt(4, producto.getStock());
            pstmt.setDouble(5, producto.getIva());
            pstmt.setString(6, String.valueOf(producto.getVisualizacion()));
            pstmt.setString(7, String.valueOf(producto.getCategoria()));

            int filasInsertadas = pstmt.executeUpdate();
            pstmt.close();

            if (filasInsertadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    public Producto buscarProducto(String nombre) throws SQLException {
        String productoSQL = "SELECT pro_codigo, pro_precio, pro_stock, pro_IVA, pro_visualizar FROM super_productos WHERE pro_nombre = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        try (PreparedStatement psProducto = conn.prepareStatement(productoSQL)) {
            psProducto.setString(1, nombre);
            ResultSet rsProducto = psProducto.executeQuery();

            if (rsProducto.next()) {
                int codigo = rsProducto.getInt("pro_codigo");
                double precio = rsProducto.getDouble("pro_precio");
                int stock = rsProducto.getInt("pro_stock");
                double IVA = rsProducto.getDouble("pro_IVA");
                char visualizar = rsProducto.getString("pro_visualizar").charAt(0);

                System.out.println("Producto encontrado:");
                System.out.println("Código: " + codigo);
                System.out.println("Nombre: " + nombre);
                System.out.println("Precio: " + precio);
                System.out.println("Stock: " + stock);
                System.out.println("IVA: " + IVA);
                System.out.println("Visualizar: " + visualizar);

                return new Producto(codigo, nombre, precio, stock, IVA, visualizar, codigo);
            } else {
                System.out.println("Producto no encontrado con el nombre: " + nombre);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            throw e;
        } finally {
            conexion.desconectar();
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

    public List<Producto> listarProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        Conexion conexion = new Conexion();
        String sql = "SELECT pro_codigo, pro_nombre, pro_precio, pro_stock, pro_IVA, pro_visualizar, super_categorias_cat_codigo FROM super_productos";

        try (Connection conn = conexion.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int codigo = rs.getInt("pro_codigo");
                String nombre = rs.getString("pro_nombre");
                double precio = rs.getDouble("pro_precio");
                int stock = rs.getInt("pro_stock");
                double iva = rs.getDouble("pro_IVA");
                char visualizar = rs.getString("pro_visualizar").charAt(0);
                int categoria = rs.getInt("super_categorias_cat_codigo");

                Producto producto = new Producto(codigo, nombre, precio, stock, iva, visualizar, categoria);
                listaProductos.add(producto);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }

        return listaProductos;
    }

}
