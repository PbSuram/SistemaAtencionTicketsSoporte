package com.pbsuram.atenciontickets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Contiene toda la lógica del sistema de tickets y coordina
 * el uso de las distintas estructuras de datos:
 *
 *  - ArrayList : historial completo de todos los tickets registrados.
 *  - HashMap   : búsqueda rápida de un ticket a partir de su código.
 *  - Queue     : tickets pendientes, respetando el orden de llegada (FIFO).
 *  - Stack     : últimos tickets atendidos (LIFO).
 *  - ListaEnlazada (dentro de cada Ticket): historial de estados.
 */


public class SistemaTickets {
    private ArrayList<Ticket> todosLosTickets;
    private HashMap<Integer, Ticket> ticketsPorCodigo;
    private Queue<Ticket> ticketsPendientes;
    private Stack<Ticket> ticketsAtendidos;

    public SistemaTickets() {
        todosLosTickets = new ArrayList<>();
        ticketsPorCodigo = new HashMap<>();
        ticketsPendientes = new LinkedList<>();
        ticketsAtendidos = new Stack<>();
    }

    /**
     * Registra un ticket nuevo. Devuelve false si el código ya existe.
     */
    public boolean registrarTicket(int codigo, String usuario, String problema) {
        if (ticketsPorCodigo.containsKey(codigo)) {
            return false;
        }

        Ticket nuevoTicket = new Ticket(codigo, usuario, problema);
        todosLosTickets.add(nuevoTicket);
        ticketsPorCodigo.put(codigo, nuevoTicket);
        ticketsPendientes.add(nuevoTicket);
        return true;
    }

    public void mostrarPendientes() {
        if (ticketsPendientes.isEmpty()) {
            System.out.println("No hay tickets pendientes.");
            return;
        }

        System.out.println("Tickets pendientes:\n");
        for (Ticket ticket : ticketsPendientes) {
            System.out.println(ticket);
        }
    }

    /**
     * Toma el primer ticket de la cola, lo pasa a EN PROCESO
     * y lo apila como el más reciente atendido.
     * Devuelve null si no hay tickets pendientes.
     */
    public Ticket atenderSiguiente() {
        if (ticketsPendientes.isEmpty()) {
            return null;
        }

        Ticket ticket = ticketsPendientes.poll();
        ticket.cambiarEstado(EstadoTicket.EN_PROCESO);
        ticketsAtendidos.push(ticket);
        return ticket;
    }

    public Ticket buscarPorCodigo(int codigo) {
        return ticketsPorCodigo.get(codigo);
    }

    /**
     * Intenta resolver un ticket. Devuelve un código de resultado
     * para que la capa de menú (Main) decida qué mensaje mostrar.
     */
    public ResultadoResolucion resolverTicket(int codigo) {
        Ticket ticket = ticketsPorCodigo.get(codigo);

        if (ticket == null) {
            return ResultadoResolucion.NO_ENCONTRADO;
        }
        if (ticket.getEstado() == EstadoTicket.PENDIENTE) {
            return ResultadoResolucion.AUN_PENDIENTE;
        }
        if (ticket.getEstado() == EstadoTicket.RESUELTO) {
            return ResultadoResolucion.YA_RESUELTO;
        }

        ticket.cambiarEstado(EstadoTicket.RESUELTO);
        return ResultadoResolucion.RESUELTO_CORRECTAMENTE;
    }

    public void mostrarHistorial(int codigo) {
        Ticket ticket = ticketsPorCodigo.get(codigo);

        if (ticket == null) {
            System.out.println("No existe un ticket con ese código.");
            return;
        }

        System.out.println("Historial del Ticket " + codigo + "\n");
        ticket.getHistorialEstados().mostrar();
    }

    /**
     * Muestra los tickets atendidos comenzando por el más reciente,
     * usando la pila sin vaciarla (solo la recorre).
     */
    public void mostrarUltimosAtendidos() {
        if (ticketsAtendidos.isEmpty()) {
            System.out.println("Aún no se ha atendido ningún ticket.");
            return;
        }

        System.out.println("Últimos tickets atendidos:\n");
        for (int i = ticketsAtendidos.size() - 1; i >= 0; i--) {
            Ticket ticket = ticketsAtendidos.get(i);
            System.out.println(ticket.getCodigo() + " - " + ticket.getUsuario());
        }
    }

    public void mostrarTodos() {
        if (todosLosTickets.isEmpty()) {
            System.out.println("No hay tickets registrados.");
            return;
        }

        for (Ticket ticket : todosLosTickets) {
            System.out.println(ticket);
        }
    }

    /**
     * Posibles resultados al intentar resolver un ticket.
     */
    public enum ResultadoResolucion {
        NO_ENCONTRADO,
        AUN_PENDIENTE,
        YA_RESUELTO,
        RESUELTO_CORRECTAMENTE
    }
}
