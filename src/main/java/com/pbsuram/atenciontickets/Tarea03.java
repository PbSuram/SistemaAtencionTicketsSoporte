package com.pbsuram.atenciontickets;

import java.util.Scanner;

/**
SISTEMA DE ATENCION DE TICKETS DE SOPORTE

Desarrollar una aplicación de consola en Java que permita administrar tickets de soporte técnico utilizando 
diferentes estructuras de datos estudiadas en clase.

El objetivo principal es aplicar correctamente Colecciones de Java, Pilas, Colas y Listas Enlazadas,
seleccionando cada estructura de acuerdo con la función que debe realizar dentro del sistema.

*/

/*
Punto de entrada del programa. Se encarga unicamente de mostrar el menu y leer datos del usuario;
toda la logica vive en SistemaTickets
*/
public class Tarea03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaTickets sistema = new SistemaTickets();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    registrarTicket(scanner, sistema);
                    break;
                case 2:
                    sistema.mostrarPendientes();
                    break;
                case 3:
                    atenderSiguiente(sistema);
                    break;
                case 4:
                    buscarTicket(scanner, sistema);
                    break;
                case 5:
                    resolverTicket(scanner, sistema);
                    break;
                case 6:
                    mostrarHistorial(scanner, sistema);
                    break;
                case 7:
                    sistema.mostrarUltimosAtendidos();
                    break;
                case 8:
                    sistema.mostrarTodos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
            System.out.println();

        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== SISTEMA DE TICKETS =====\n");
        System.out.println("1. Registrar nuevo ticket");
        System.out.println("2. Mostrar tickets pendientes");
        System.out.println("3. Atender siguiente ticket");
        System.out.println("4. Buscar ticket por código");
        System.out.println("5. Resolver ticket");
        System.out.println("6. Mostrar historial de estados de un ticket");
        System.out.println("7. Mostrar últimos tickets atendidos");
        System.out.println("8. Mostrar todos los tickets");
        System.out.println("0. Salir\n");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Lee un entero de forma segura. Si el usuario ingresa
     * texto que no es un número, se devuelve -1 para que el
     * menú lo trate como una opción inválida.
     */
    private static int leerEntero(Scanner scanner) {
        String linea = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void registrarTicket(Scanner scanner, SistemaTickets sistema) {
        System.out.print("Código: ");
        int codigo = leerEntero(scanner);
        if (codigo < 0) {
            System.out.println("Código inválido. Debe ser un número.");
            return;
        }

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine().trim();

        System.out.print("Problema: ");
        String problema = scanner.nextLine().trim();

        boolean registrado = sistema.registrarTicket(codigo, usuario, problema);
        if (registrado) {
            System.out.println("\nTicket registrado correctamente con estado PENDIENTE.");
        } else {
            System.out.println("\nYa existe un ticket con el código " + codigo + ".");
        }
    }

    private static void atenderSiguiente(SistemaTickets sistema) {
        Ticket ticket = sistema.atenderSiguiente();
        if (ticket == null) {
            System.out.println("No hay tickets pendientes por atender.");
            return;
        }

        System.out.println("Atendiendo ticket:\n");
        mostrarDatosTicket(ticket);
    }

    private static void buscarTicket(Scanner scanner, SistemaTickets sistema) {
        System.out.print("Ingrese código: ");
        int codigo = leerEntero(scanner);

        Ticket ticket = sistema.buscarPorCodigo(codigo);
        if (ticket == null) {
            System.out.println("Ticket no encontrado.");
            return;
        }
        mostrarDatosTicket(ticket);
    }

    private static void mostrarDatosTicket(Ticket ticket) {
        System.out.println("Código: " + ticket.getCodigo());
        System.out.println("Usuario: " + ticket.getUsuario());
        System.out.println("Problema: " + ticket.getProblema());
        System.out.println("Estado: " + ticket.getEstado());
    }

    private static void resolverTicket(Scanner scanner, SistemaTickets sistema) {
        System.out.print("Ingrese código del ticket a resolver: ");
        int codigo = leerEntero(scanner);

        SistemaTickets.ResultadoResolucion resultado = sistema.resolverTicket(codigo);

        switch (resultado) {
            case NO_ENCONTRADO:
                System.out.println("Ticket no encontrado.");
                break;
            case AUN_PENDIENTE:
                System.out.println("No se puede resolver un ticket que aún está PENDIENTE.");
                System.out.println("Primero debe atenderse (opción 3).");
                break;
            case YA_RESUELTO:
                System.out.println("El ticket ya se encuentra RESUELTO.");
                break;
            case RESUELTO_CORRECTAMENTE:
                System.out.println("Ticket marcado como RESUELTO correctamente.");
                break;
        }
    }

    private static void mostrarHistorial(Scanner scanner, SistemaTickets sistema) {
        System.out.print("Ingrese código del ticket: ");
        int codigo = leerEntero(scanner);
        sistema.mostrarHistorial(codigo);
    }
}
