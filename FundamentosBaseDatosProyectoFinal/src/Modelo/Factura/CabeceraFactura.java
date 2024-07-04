/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Factura;

import Modelo.Personas.Persona.Cliente;
import Modelo.Personas.Persona.Empleado;
import java.sql.Timestamp;

/**
 *
 * @author venot
 */
public class CabeceraFactura {
    private int codigo;
    private Timestamp fecha;
    private double subTotal;
    private double totalIVA;
    private double valorTotal;
    private char estado;
    private Cliente cliente;
    private Empleado empleado;

    public CabeceraFactura(int codigo, Timestamp fecha, double subTotal, double totalIVA, double valorTotal, char estado, Cliente cliente, Empleado empleado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.totalIVA = totalIVA;
        this.valorTotal = valorTotal;
        this.estado = estado;
        this.cliente = cliente;
        this.empleado = empleado;
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

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotalIVA() {
        return totalIVA;
    }

    public void setTotalIVA(double totalIVA) {
        this.totalIVA = totalIVA;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    
    
    
    
}
