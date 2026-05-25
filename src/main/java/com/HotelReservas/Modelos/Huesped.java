package com.HotelReservas.Modelos;

public class Huesped {
    private String nombre;
    private String dni;
    private String email;

    public Huesped(String nombre, String dni, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getEmail() { return email; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
}