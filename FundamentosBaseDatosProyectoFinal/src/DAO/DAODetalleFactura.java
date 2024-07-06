/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Factura.DetalleFactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class DAODetalleFactura {

    public boolean insertarDetalleFactura(DetalleFactura detalleFactura) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "INSERT INTO super_detalle_facturas (det_codigo, det_cantidad, det_precio_unitario, det_subtotal, det_iva, det_total, super_cabecera_facturas_fac_codigo, super_productos_pro_codigo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, detalleFactura.getCodigo());
            pstmt.setInt(2, detalleFactura.getCantidad());
            pstmt.setDouble(3, detalleFactura.getPrecioUnitario());
            pstmt.setDouble(4, detalleFactura.getSubTotal());
            pstmt.setDouble(5, detalleFactura.getIva());
            pstmt.setDouble(6, detalleFactura.getTotal());
            pstmt.setInt(7, detalleFactura.getCodigoCabeceraFactura());
            pstmt.setInt(8, detalleFactura.getCodigoProducto());

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

    public void buscarDetalleFactura(int codigo) {
        if (codigo > 0) {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conectar();

            String sql = "SELECT * FROM super_detalle_facturas WHERE det_codigo = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, codigo);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Detalle de factura encontrado:");
                    System.out.println("Código: " + rs.getInt("det_codigo"));
                    System.out.println("Cantidad: " + rs.getInt("det_cantidad"));
                    System.out.println("Precio Unitario: " + rs.getDouble("det_precio_unitario"));
                    System.out.println("Subtotal: " + rs.getDouble("det_subtotal"));
                    System.out.println("IVA: " + rs.getDouble("det_iva"));
                    System.out.println("Total: " + rs.getDouble("det_total"));
                    System.out.println("Código Cabecera Factura: " + rs.getInt("super_cabecera_facturas_fac_codigo"));
                    System.out.println("Código Producto: " + rs.getInt("super_productos_pro_codigo"));
                } else {
                    System.out.println("No se encontró ningún detalle de factura con el código " + codigo);
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            } finally {
                conexion.desconectar();
            }
        } else {
            System.out.println("El código del detalle de factura no puede ser menor o igual a 0.");
        }
    }

    public boolean actualizarDetalleFactura(DetalleFactura detalleFactura) {
        boolean llave = false;
        try {
            System.out.println("Actualizando detalle de factura: " + detalleFactura);

            String SQLr = "UPDATE super_detalle_facturas SET det_cantidad=?, det_precio_unitario=?, det_subtotal=?, det_iva=?, det_total=?, super_cabecera_facturas_fac_codigo=?, super_productos_pro_codigo=? WHERE det_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setInt(1, detalleFactura.getCantidad());
            sentencia.setDouble(2, detalleFactura.getPrecioUnitario());
            sentencia.setDouble(3, detalleFactura.getSubTotal());
            sentencia.setDouble(4, detalleFactura.getIva());
            sentencia.setDouble(5, detalleFactura.getTotal());
            sentencia.setInt(6, detalleFactura.getCodigoCabeceraFactura());
            sentencia.setInt(7, detalleFactura.getCodigoProducto());
            sentencia.setInt(8, detalleFactura.getCodigo());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se actualizó correctamente el detalle de factura");
                llave = true;
            } else {
                System.out.println("No se pudo actualizar el detalle de factura");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

    public boolean eliminarDetalleFactura(int codigo) {
        boolean llave = false;
        try {
            String SQLr = "DELETE FROM super_detalle_facturas WHERE det_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setInt(1, codigo);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se eliminó correctamente el detalle de factura con código " + codigo);
                llave = true;
            } else {
                System.out.println("No se pudo eliminar el detalle de factura con el código " + codigo + " (No existe en la base de datos)");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

}
