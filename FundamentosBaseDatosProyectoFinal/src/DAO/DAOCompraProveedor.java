/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Proveedor.CompraProveedor;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class DAOCompraProveedor {

    public boolean insertarCompraProveedor(CompraProveedor compraProveedor) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "INSERT INTO super_compra_proveedores (com_codigo, com_fecha, com_valor_total, com_cantidad, super_productos_pro_codigo, super_proveedores_prov_codigo) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, compraProveedor.getCodigo());
            pstmt.setTimestamp(2, compraProveedor.getFecha());
            pstmt.setDouble(3, compraProveedor.getValorTotal());
            pstmt.setInt(4, compraProveedor.getCantidad());
            pstmt.setInt(5, compraProveedor.getCodigoProducto());
            pstmt.setDouble(6, compraProveedor.getCodigoProveedor());

            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    public CompraProveedor buscarCompraProveedor(int codigo) {
        String sql = "SELECT com_codigo, com_fecha, com_valor_total, com_cantidad, super_productos_pro_codigo, super_proveedores_prov_codigo FROM super_compra_proveedores WHERE com_codigo = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int comCodigo = rs.getInt("com_codigo");
                Timestamp comFecha = rs.getTimestamp("com_fecha");
                double comValorTotal = rs.getDouble("com_valor_total");
                int comCantidad = rs.getInt("com_cantidad");
                int superProductosProCodigo = rs.getInt("super_productos_pro_codigo");
                int superProveedoresProvCodigo = rs.getInt("super_proveedores_prov_codigo");

                System.out.println("Compra encontrada:");
                System.out.println("Código: " + comCodigo);
                System.out.println("Fecha: " + comFecha);
                System.out.println("Valor Total: " + comValorTotal);
                System.out.println("Cantidad: " + comCantidad);
                System.out.println("Código Producto: " + superProductosProCodigo);
                System.out.println("Código Proveedor: " + superProveedoresProvCodigo);

                return new CompraProveedor(
                        superProveedoresProvCodigo,
                        superProductosProCodigo,
                        comCodigo,
                        comFecha,
                        comValorTotal,
                        comCantidad
                );
            } else {
                System.out.println("No se encontró ninguna compra con el código " + codigo);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    public boolean actualizarCompraProveedor(CompraProveedor compraProveedor) {
        boolean llave = false;
        try {
            System.out.println("Actualizando compra: " + compraProveedor);

            String SQLr = "UPDATE super_compra_proveedores SET com_fecha=?, com_valor_total=?, com_cantidad=?, super_productos_pro_codigo=?, super_proveedores_prov_codigo=? WHERE com_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setTimestamp(1, compraProveedor.getFecha());
            sentencia.setDouble(2, compraProveedor.getValorTotal());
            sentencia.setInt(3, compraProveedor.getCantidad());
            sentencia.setInt(4, compraProveedor.getCodigoProducto());
            sentencia.setDouble(5, compraProveedor.getCodigoProveedor());
            sentencia.setInt(6, compraProveedor.getCodigo());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se actualizó correctamente la compra");
                llave = true;
            } else {
                System.out.println("No se pudo actualizar la compra");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

    public boolean eliminarCompraProveedor(int codigo) {
        boolean llave = false;
        try {
            String SQLr = "DELETE FROM super_compra_proveedores WHERE com_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setInt(1, codigo);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se eliminó correctamente la compra con código " + codigo);
                llave = true;
            } else {
                System.out.println("No se pudo eliminar la compra con el código " + codigo + " (No existe en la base de datos)");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

    public List<CompraProveedor> listarCompras() {
        List<CompraProveedor> listaCompras = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "SELECT com_codigo, com_fecha, com_valor_total, com_cantidad, super_productos_pro_codigo, super_proveedores_prov_codigo FROM super_compra_proveedores";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int comCodigo = rs.getInt("com_codigo");
                Timestamp comFecha = rs.getTimestamp("com_fecha");
                double comValorTotal = rs.getDouble("com_valor_total");
                int comCantidad = rs.getInt("com_cantidad");
                int codigoProducto = rs.getInt("super_productos_pro_codigo");
                int codigoProveedor = rs.getInt("super_proveedores_prov_codigo");

                CompraProveedor compraProveedor = new CompraProveedor(
                        codigoProveedor,
                        codigoProducto,
                        comCodigo,
                        comFecha,
                        comValorTotal,
                        comCantidad
                );
                listaCompras.add(compraProveedor);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }

        return listaCompras;
    }

}
