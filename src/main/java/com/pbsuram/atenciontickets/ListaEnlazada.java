/**
Lista enlazada simple implementada manualmente (sin usar ArrayList ni LinkedList de Java) para registrar,
en orden, cada estado por el que pasa un ticket.
*/
package com.pbsuram.atenciontickets;

public class ListaEnlazada {
    private Nodo cabeza;
        
    // Agrega un nuevo estado al final de la lista.
    public void agregar(String estado) {
        Nodo nuevoNodo = new Nodo(estado);

        if (cabeza == null) {
            cabeza = nuevoNodo;
            return;
        }

        Nodo actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevoNodo);
    }

    // Imprime el historial completo en el formato: ESTADO1 -> ESTADO2
    public void mostrar() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getEstado());
            if (actual.getSiguiente() != null) {
                System.out.println("   \u2193");
            }
            actual = actual.getSiguiente();
        }
    }
}
