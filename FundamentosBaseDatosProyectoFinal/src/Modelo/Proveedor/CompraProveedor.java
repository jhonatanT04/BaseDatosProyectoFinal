/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Proveedor;

import Modelo.Producto.Producto;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author venot
 */
public class CompraProveedor {
    private int codigoProveedor;
    private int codigoProducto;
    
    private int codigo;
    private Timestamp fecha;
    private double valorTotal;
    private int cantidad;

    public CompraProveedor(int codigoProveedor, int codigoProducto, int codigo, Timestamp fecha, double valorTotal, int cantidad) {
        this.codigoProveedor = codigoProveedor;
        this.codigoProducto = codigoProducto;
        this.codigo = codigo;
        this.fecha = fecha;
        this.valorTotal = valorTotal;
        this.cantidad = cantidad;
    }

    public int getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(int codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
