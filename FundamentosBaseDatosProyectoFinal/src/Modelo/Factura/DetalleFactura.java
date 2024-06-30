/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Factura;

import Modelo.Producto.Producto;

/**
 *
 * @author venot
 */
public class DetalleFactura {
    private int codigo;
    private int cantidad;
    private double precioUnitario;
    private double subTotal;
    private double iva;
    private double total;
    private CabeceraFactura cabeceraFactura;
    private Producto producto;

    public DetalleFactura(int codigo, int cantidad, double precioUnitario, double subTotal, double iva, double total, CabeceraFactura cabeceraFactura, Producto producto) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
        this.cabeceraFactura = cabeceraFactura;
        this.producto = producto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public CabeceraFactura getCabeceraFactura() {
        return cabeceraFactura;
    }

    public void setCabeceraFactura(CabeceraFactura cabeceraFactura) {
        this.cabeceraFactura = cabeceraFactura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
