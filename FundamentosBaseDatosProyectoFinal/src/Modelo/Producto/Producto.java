/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Producto;

/**
 *
 * @author venot
 */
public class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    private int stock;
    private double iva;
    private char visualizacion;
    private int categoria;

    public Producto(int codigo, String nombre, double precio, int stock, double iva, char visualizacion, int categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.iva = iva;
        this.visualizacion = visualizacion;
        this.categoria = categoria;
    }
    
    
    
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public char getVisualizacion() {
        return visualizacion;
    }

    public void setVisualizacion(char visualizacion) {
        this.visualizacion = visualizacion;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

 
}
