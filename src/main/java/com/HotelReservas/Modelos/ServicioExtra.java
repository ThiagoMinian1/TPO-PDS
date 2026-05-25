package com.HotelReservas.Modelos;

public class ServicioExtra {
    private String nombre;
    private double costoAdicional;

    public ServicioExtra(String nombre, double costoAdicional) {
        this.nombre = nombre;
        this.costoAdicional = costoAdicional;
    }

    public String getNombre() { return nombre; }
    public double getCostoAdicional() { return costoAdicional; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCostoAdicional(double costo) { this.costoAdicional = costo; }

}