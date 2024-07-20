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

    public boolean insertarProducto(Producto producto) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String insertProductoSQL = "INSERT INTO super_productos (pro_codigo, pro_nombre, pro_precio, pro_stock, pro_iva, pro_visualizar, super_categorias_cat_codigo) VALUES (SEQ_PRO_CODIGO.nextval, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement psProducto = conn.prepareStatement(insertProductoSQL)) {
            psProducto.setString(1, producto.getNombre());
            psProducto.setDouble(2, producto.getPrecio());
            psProducto.setInt(3, producto.getStock());
            psProducto.setDouble(4, producto.getIva());
            psProducto.setString(5, String.valueOf(producto.getVisualizacion()));
            psProducto.setInt(6, producto.getCategoria());
            psProducto.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                conn.close();
            }
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

                return new Producto(codigo, nombre, precio, stock, IVA, visualizar, codigo);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Producto buscarProductoPorCodigo(int codigo) throws SQLException {
        String productoSQL = "SELECT pro_codigo, pro_nombre, pro_precio, pro_stock, pro_IVA, pro_visualizar FROM super_productos WHERE pro_codigo = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        try (PreparedStatement psProducto = conn.prepareStatement(productoSQL)) {
            psProducto.setInt(1, codigo);
            ResultSet rsProducto = psProducto.executeQuery();

            if (rsProducto.next()) {
                String nombre = rsProducto.getString("pro_nombre");
                double precio = rsProducto.getDouble("pro_precio");
                int stock = rsProducto.getInt("pro_stock");
                double IVA = rsProducto.getDouble("pro_IVA");
                char visualizar = rsProducto.getString("pro_visualizar").charAt(0);

                return new Producto(codigo, nombre, precio, stock, IVA, visualizar, codigo);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<Producto> buscarProductosPorCategoria(String categoria) throws SQLException {
        String productoSQL = "SELECT p.pro_codigo, p.pro_nombre, p.pro_precio, p.pro_stock, p.pro_IVA, p.pro_visualizar "
                + "FROM super_productos p "
                + "JOIN super_categorias c ON p.pro_categoria_id = c.cat_id "
                + "WHERE c.cat_nombre = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement psProducto = conn.prepareStatement(productoSQL)) {
            psProducto.setString(1, categoria);
            ResultSet rsProducto = psProducto.executeQuery();

            while (rsProducto.next()) {
                int codigo = rsProducto.getInt("pro_codigo");
                String nombreProducto = rsProducto.getString("pro_nombre");
                double precio = rsProducto.getDouble("pro_precio");
                int stock = rsProducto.getInt("pro_stock");
                double IVA = rsProducto.getDouble("pro_IVA");
                char visualizar = rsProducto.getString("pro_visualizar").charAt(0);

                Producto producto = new Producto(codigo, nombreProducto, precio, stock, IVA, visualizar, codigo);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return productos;
    }

    public boolean actualizarProducto(Producto producto) {
        boolean actualizado = false;
        String SQLr = "UPDATE super_productos SET pro_nombre=?, super_categorias_cat_codigo=?, pro_precio=?, pro_stock=?, pro_iva=?, pro_visualizar=? WHERE pro_codigo=?";
        Conexion conexion = new Conexion();
        Connection con = null;

        try {
            con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);
            sentencia.setString(1, producto.getNombre());
            sentencia.setInt(2, producto.getCategoria());
            sentencia.setDouble(3, producto.getPrecio());
            sentencia.setInt(4, producto.getStock());
            sentencia.setDouble(5, producto.getIva());
            sentencia.setString(6, String.valueOf(producto.getVisualizacion()));
            sentencia.setInt(7, producto.getCodigo());

            int filasAfectadas = sentencia.executeUpdate();
            actualizado = filasAfectadas > 0;

            sentencia.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return actualizado;
    }

    public boolean eliminarProducto(String nombre) {
        boolean eliminado = false;
        String SQLr = "DELETE FROM super_productos WHERE pro_nombre=?";
        Conexion conexion = new Conexion();
        Connection con = null;

        try {
            con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);
            sentencia.setString(1, nombre);

            int filasAfectadas = sentencia.executeUpdate();
            eliminado = filasAfectadas > 0;

            sentencia.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return eliminado;
    }

    public boolean eliminarProducto2(String nombre) {
        boolean eliminado = false;
        String SQLr = "DELETE FROM super_productos WHERE pro_nombre=?";
        String SQLu = "UPDATE super_productos SET pro_visualizar='I' WHERE pro_nombre=?";
        String SQLCheckRelacion = "SELECT COUNT(*) FROM super_detalle_facturas WHERE super_productos_pro_codigo = (SELECT pro_codigo FROM super_productos WHERE pro_nombre=?) "
                + "UNION ALL "
                + "SELECT COUNT(*) FROM super_compra_proveedores WHERE super_productos_pro_codigo = (SELECT pro_codigo FROM super_productos WHERE pro_nombre=?)";
        Conexion conexion = new Conexion();
        Connection con = null;

        try {
            con = conexion.conectar();

            // Verificar si el producto está relacionado con alguna factura o compra
            PreparedStatement checkStmt = con.prepareStatement(SQLCheckRelacion);
            checkStmt.setString(1, nombre);
            checkStmt.setString(2, nombre);
            ResultSet rs = checkStmt.executeQuery();

            int relacionCount = 0;
            while (rs.next()) {
                relacionCount += rs.getInt(1);
            }

            rs.close();
            checkStmt.close();

            if (relacionCount == 0) {
                // No está relacionado, eliminar completamente
                PreparedStatement deleteStmt = con.prepareStatement(SQLr);
                deleteStmt.setString(1, nombre);
                int filasAfectadas = deleteStmt.executeUpdate();
                eliminado = filasAfectadas > 0;
                deleteStmt.close();
            } else {
                // Está relacionado, actualizar a inactivo
                PreparedStatement updateStmt = con.prepareStatement(SQLu);
                updateStmt.setString(1, nombre);
                int filasAfectadas = updateStmt.executeUpdate();
                eliminado = filasAfectadas > 0;
                updateStmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return eliminado;
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
            e.printStackTrace();
        }

        return listaProductos;
    }
}
