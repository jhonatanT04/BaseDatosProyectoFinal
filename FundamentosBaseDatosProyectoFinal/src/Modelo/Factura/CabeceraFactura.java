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
    private int codigoCliente;
    private int codigoEmpleado;

    public CabeceraFactura(int codigo, Timestamp fecha, double subTotal, double totalIVA, double valorTotal, char estado, int codigoCliente, int codigoEmpleado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.totalIVA = totalIVA;
        this.valorTotal = valorTotal;
        this.estado = estado;
        this.codigoCliente = codigoCliente;
        this.codigoEmpleado = codigoEmpleado;
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

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    
    
}
