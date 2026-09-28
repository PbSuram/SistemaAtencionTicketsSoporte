// Nodo de la lista enlazada simple utilizada para almacenar el historial de estados de un ticket.
package com.pbsuram.atenciontickets;

public class Nodo {
    private String estado;
    private Nodo siguiente;

    public Nodo(String estado) {
        this.estado = estado;
        this.siguiente = null;
    }

    public String getEstado() {
        return estado;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

}
