package com.HotelReservas.Modelos;

public class Habitacion {
    private int numero;
    private String tipo;
    private int capacidad;
    private double precioPorNoche;
    private boolean disponible;
    private String fechaDesde;
    private String fechaHasta;

    public Habitacion(int numero, String tipo, int capacidad, double precioPorNoche) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precioPorNoche = precioPorNoche;
        this.disponible = true;
        this.fechaDesde = "";
        this.fechaHasta = "";
    }

    public int getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public int getCapacidad() { return capacidad; }
    public double getPrecioPorNoche() { return precioPorNoche; }
    public boolean isDisponible() { return disponible; }
    public String getFechaDesde() { return fechaDesde; }
    public String getFechaHasta() { return fechaHasta; }

    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public void setPrecioPorNoche(double precio) { this.precioPorNoche = precio; }
    public void setFechaDesde(String fechaDesde) { this.fechaDesde = fechaDesde; }
    public void setFechaHasta(String fechaHasta) { this.fechaHasta = fechaHasta; }

    public boolean estaDisponibleEnRango(String desde, String hasta) {
        if (!disponible) return false;
        if (fechaDesde == "" || fechaHasta == "") return true;
        return hasta != fechaDesde || desde != fechaHasta;
    }

    public void ocupar(String desde, String hasta) {
        this.disponible = false;
        this.fechaDesde = desde;
        this.fechaHasta = hasta;
        System.out.println("Habitacion #" + numero + " ocupada del " + desde + " al " + hasta);
    }

    public void liberar() {
        this.disponible = true;
        this.fechaDesde = "";
        this.fechaHasta = "";
        System.out.println("Habitacion #" + numero + " liberada.");
    }
}