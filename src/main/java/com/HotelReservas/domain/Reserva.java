package com.HotelReservas.domain;

import java.time.LocalDate;

public class Reserva {
    private int id;
    private Huesped huesped;
    private Habitacion habitacion;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;
    private String estado;    // PENDIENTE, CONFIRMADA, CANCELADA, etc.

    // Constructor, getters y setters
}