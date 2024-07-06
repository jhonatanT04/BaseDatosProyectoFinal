/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Factura.CabeceraFactura;
import Modelo.Producto.Producto;
import java.security.Timestamp;
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
public class DAOCabeceraFactura {

    public boolean insertarCabeceraFacturas(CabeceraFactura cabeceraFactura) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "INSERT INTO super_cabecera_facturas (fac_codigo, fac_fecha, fac_subtotal, fac_total_iva, fac_valor_total, fac_estado, super_empleados_emp_codigo, super_clientes_cli_codigo, super_clientesv1_cli_codigo, super_empleadosv1_emp_codigo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cabeceraFactura.getCodigo());
            pstmt.setTimestamp(2, cabeceraFactura.getFecha());
            pstmt.setDouble(3, cabeceraFactura.getSubTotal());
            pstmt.setDouble(4, cabeceraFactura.getTotalIVA());
            pstmt.setDouble(5, cabeceraFactura.getValorTotal());
            pstmt.setString(6, String.valueOf(cabeceraFactura.getEstado()));
            pstmt.setInt(7, cabeceraFactura.getCodigoEmpleado());
            pstmt.setInt(8, cabeceraFactura.getCodigoCliente());

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

    public void buscarCabeceraFactura(int codigo) {
        if (codigo > 0) {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conectar();

            String sql = "SELECT * FROM super_cabecera_facturas WHERE fac_codigo = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, codigo);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Factura encontrada:");
                    System.out.println("Código: " + rs.getInt("fac_codigo"));
                    System.out.println("Fecha: " + rs.getTimestamp("fac_fecha"));
                    System.out.println("Subtotal: " + rs.getDouble("fac_subtotal"));
                    System.out.println("Total IVA: " + rs.getDouble("fac_total_iva"));
                    System.out.println("Valor Total: " + rs.getDouble("fac_valor_total"));
                    System.out.println("Estado: " + rs.getString("fac_estado"));
                    System.out.println("Código Empleado: " + rs.getInt("super_empleados_emp_codigo"));
                    System.out.println("Código Cliente: " + rs.getInt("super_clientes_cli_codigo"));
                    System.out.println("Código Cliente V1: " + rs.getInt("super_clientesv1_cli_codigo"));
                    System.out.println("Código Empleado V1: " + rs.getInt("super_empleadosv1_emp_codigo"));
                } else {
                    System.out.println("No se encontró ninguna factura con el código " + codigo);
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            } finally {
                conexion.desconectar();
            }
        } else {
            System.out.println("El código de la factura no puede ser menor o igual a 0.");
        }
    }

    public boolean actualizarCabeceraFactura(CabeceraFactura cabeceraFactura) {
        boolean llave = false;
        try {
            System.out.println("Actualizando factura: " + cabeceraFactura);

            String SQLr = "UPDATE super_cabecera_facturas SET fac_fecha=?, fac_subtotal=?, fac_total_iva=?, fac_valor_total=?, fac_estado=?, super_empleados_emp_codigo=?, super_clientes_cli_codigo=?, super_clientesv1_cli_codigo=?, super_empleadosv1_emp_codigo=? WHERE fac_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setTimestamp(1, cabeceraFactura.getFecha());
            sentencia.setDouble(2, cabeceraFactura.getSubTotal());
            sentencia.setDouble(3, cabeceraFactura.getTotalIVA());
            sentencia.setDouble(4, cabeceraFactura.getValorTotal());
            sentencia.setString(5, String.valueOf(cabeceraFactura.getEstado()));
            sentencia.setInt(6, cabeceraFactura.getCodigoEmpleado());
            sentencia.setInt(7, cabeceraFactura.getCodigoCliente());
            sentencia.setInt(10, cabeceraFactura.getCodigo());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se actualizó correctamente la factura");
                llave = true;
            } else {
                System.out.println("No se pudo actualizar la factura");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

    public boolean eliminarCabeceraFactura(int codigo) {
        boolean llave = false;
        try {
            String SQLr = "DELETE FROM super_cabecera_facturas WHERE fac_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setInt(1, codigo);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se eliminó correctamente la factura con código " + codigo);
                llave = true;
            } else {
                System.out.println("No se pudo eliminar la factura con el código " + codigo + " (No existe en la base de datos)");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

}
