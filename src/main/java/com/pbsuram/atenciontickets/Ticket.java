package com.pbsuram.atenciontickets;

/**
Representa una solicitud de soporte técnico. Cada ticket mantiene, además de sus datos básicos,
su propio historial de estados en una lista enlazada.
*/

public class Ticket {
    private int codigo;
    private String usuario;
    private String problema;
    private EstadoTicket estado;
    private ListaEnlazada historialEstados;

    public Ticket(int codigo, String usuario, String problema) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.problema = problema;
        this.estado = EstadoTicket.PENDIENTE;
        this.historialEstados = new ListaEnlazada();
        this.historialEstados.agregar(this.estado.toString());
    }

    public int getCodigo() {
        return codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getProblema() {
        return problema;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public ListaEnlazada getHistorialEstados() {
        return historialEstados;
    }

    // Cambia el estado del ticket y deja constancia del cambio en su historial.
    public void cambiarEstado(EstadoTicket nuevoEstado) {
        this.estado = nuevoEstado;
        this.historialEstados.agregar(nuevoEstado.toString());
    }

    @Override
    public String toString() {
        return codigo + " - " + usuario + " - " + estado;
    }

}
