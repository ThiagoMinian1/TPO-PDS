package com.HotelReservas.observer;

public interface Observable {
    void agregarObservador(Observador o);
    void quitarObservador(Observador o);
    void notificarObservadores(String evento, Object datos);
}