/**
 Estados posibles de un ticket dentro del sistema. Se define como enum para evitar el uso de cadenas "mágicas"
 y controlar las transiciones válidas entre estados.
 */

package com.pbsuram.atenciontickets;

public enum EstadoTicket {
    PENDIENTE("PENDIENTE"),
    EN_PROCESO("EN PROCESO"),
    RESUELTO("RESUELTO");

    private final String descripcion;

    EstadoTicket(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
