package com.HotelReservas.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private int id;
    private Huesped huesped;
    private Habitacion habitacion;
    private String fechaIngreso;
    private String fechaEgreso;
    private String estado;
    private List<ServicioExtra> serviciosExtra;

    public Reserva(int id, Huesped huesped, Habitacion habitacion, String fechaIngreso, String fechaEgreso) {
        this.id = id;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.estado = "PENDIENTE";
        this.serviciosExtra = new ArrayList<>();
    }

    public int getId() { return id; }
    public Huesped getHuesped() { return huesped; }
    public Habitacion getHabitacion() { return habitacion; }
    public String getFechaIngreso() { return fechaIngreso; }
    public String getFechaEgreso() { return fechaEgreso; }
    public String getEstado() { return estado; }
    public List<ServicioExtra> getServiciosExtra() { return serviciosExtra; }

    public void setEstado(String estado) { this.estado = estado; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public void setFechaEgreso(String fechaEgreso) { this.fechaEgreso = fechaEgreso; }

    public void agregarServicioExtra(ServicioExtra servicio) {
        serviciosExtra.add(servicio);
    }

    public double calcularCostoTotal() {
        double total = habitacion.getPrecioPorNoche();
        for (ServicioExtra s : serviciosExtra) {
            total += s.getCostoAdicional();
        }
        return total;
    }
}