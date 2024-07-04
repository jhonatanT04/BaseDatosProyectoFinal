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
    private Proveedor proveedor;
    private Producto producto;
    
    private int codigo;
    private Timestamp fecha;
    private double valorTotal;
    private int cantidad;
    
    public CompraProveedor(Proveedor proveedor, Producto producto, int codigo, Timestamp fecha, double valorTotal, int cantidad) {
        this.proveedor = proveedor;
        this.producto = producto;
        this.codigo = codigo;
        this.fecha = fecha;
        this.valorTotal = valorTotal;
        this.cantidad = cantidad;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
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
