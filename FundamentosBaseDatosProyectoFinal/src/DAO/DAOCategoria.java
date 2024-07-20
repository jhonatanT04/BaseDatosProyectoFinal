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
import javax.swing.JComboBox;
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

    public Categoria buscarCategoriaPorNombre(String nombre) {
        Categoria categoria = null;

        if (nombre != null) {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conectar();

            String sql = "SELECT * FROM super_categorias WHERE cat_nombre = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, nombre);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    categoria = new Categoria(rs.getInt("cat_codigo"), rs.getString("cat_nombre"));
                    System.out.println("Categoría encontrada:");
                    System.out.println("Código: " + rs.getInt("cat_codigo"));
                    System.out.println("Nombre: " + rs.getString("cat_nombre"));
                } else {
                    System.out.println("No se encontró ninguna categoría con el nombre " + nombre);
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            } finally {
                conexion.desconectar();
            }
        } else {
            System.out.println("El nombre de la categoría no puede ser nulo.");
        }

        return categoria;
    }

    public Categoria buscarCategoriaPorCodigo(int codigo) {
        Categoria categoria = null;

        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "SELECT * FROM super_categorias WHERE cat_codigo = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                categoria = new Categoria(rs.getInt("cat_codigo"), rs.getString("cat_nombre"));
                System.out.println("Categoría encontrada:");
                System.out.println("Código: " + rs.getInt("cat_codigo"));
                System.out.println("Nombre: " + rs.getString("cat_nombre"));
            } else {
                System.out.println("No se encontró ninguna categoría con el código " + codigo);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }

        return categoria;
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

    public void cargarCategorias(JComboBox<String> comboBox) {
        String sql = "SELECT cat_nombre FROM super_categorias";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            comboBox.removeAllItems(); // Limpiar el JComboBox antes de cargar nuevas categorías
            while (rs.next()) {
                comboBox.addItem(rs.getString("cat_nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar categorías: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

    public int obtenerCodigoCategoria(String nombreCategoria) {
        String sql = "SELECT cat_codigo FROM super_categorias WHERE cat_nombre = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        int codigoCategoria = -1;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreCategoria);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                codigoCategoria = rs.getInt("cat_codigo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener código de categoría: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }

        return codigoCategoria;
    }
}
