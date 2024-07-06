/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Proveedor.CompraProveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void buscarCompraProveedor(int codigo) {
        if (codigo > 0) {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conectar();

            String sql = "SELECT * FROM super_compra_proveedores WHERE com_codigo = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, codigo);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Compra encontrada:");
                    System.out.println("Código: " + rs.getInt("com_codigo"));
                    System.out.println("Fecha: " + rs.getTimestamp("com_fecha"));
                    System.out.println("Valor Total: " + rs.getDouble("com_valor_total"));
                    System.out.println("Cantidad: " + rs.getInt("com_cantidad"));
                    System.out.println("Código Producto: " + rs.getInt("super_productos_pro_codigo"));
                    System.out.println("Código Proveedor: " + rs.getDouble("super_proveedores_prov_codigo"));
                } else {
                    System.out.println("No se encontró ninguna compra con el código " + codigo);
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            } finally {
                conexion.desconectar();
            }
        } else {
            System.out.println("El código de la compra no puede ser menor o igual a 0.");
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

}
