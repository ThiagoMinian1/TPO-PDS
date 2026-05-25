package com.HotelReservas.strategy;

import com.HotelReservas.Modelos.Reserva;

public interface EstrategiaPrecio {
    double calcularPrecio(Reserva reserva);
}