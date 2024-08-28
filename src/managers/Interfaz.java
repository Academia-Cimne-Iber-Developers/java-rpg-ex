package ui;

import managers.GestorMisiones;
import models.*;

import java.util.Scanner;

public class Interfaz {
    private static final Scanner scanner = new Scanner(System.in);
    private Jugador jugador;

    public Interfaz(Mapa mapa, Jugador jugador) {
        this.jugador = jugador;
    }

    public void actualizarPantalla() {
        System.out.println("\n".repeat(50));
        mostrarInfoJugador();
        mostrarUbicacionActual();
        mostrarOpciones();
    }

    public void mostrarMisiones(GestorMisiones gestorMisiones) {
        System.out.println("=== Misiones Activas ===");
        if (gestorMisiones.getMisionesActivas().isEmpty()) {
            System.out.println("No tienes misiones activas en este momento.");
        } else {
            for (Mision mision : gestorMisiones.getMisionesActivas()) {
                String estado = mision.estaCompleta() ? "[Completada]" : "[En progreso]";
                System.out.println("- " + mision.getDescripcion() + " " + estado);
            }
        }

        System.out.println("\n=== Misiones Completadas ===");
        if (gestorMisiones.getMisionesCompletadas().isEmpty()) {
            System.out.println("No has completado ninguna misión todavía.");
        } else {
            for (Mision mision : gestorMisiones.getMisionesCompletadas()) {
                System.out.println("- " + mision.getDescripcion() + " [Completada]");
            }
        }

        pausarPantalla();
    }

    public String pedirEntrada(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private void mostrarInfoJugador() {
        System.out.println("=== Información del Jugador ===");
        System.out.println("Nombre: " + jugador.getNombre());
        System.out.println("Vida: " + jugador.getVida());
        System.out.println("Ataque: " + jugador.getAtaque());
    }

    private void mostrarUbicacionActual() {
        System.out.println("=== Ubicación Actual ===");
        System.out.println(jugador.getUbicacionActual().getDescripcion());
    }

    private void mostrarOpciones() {
        System.out.println("=== Opciones ===");
        System.out.println("1. Ver Inventario");
        System.out.println("2. Mostrar Mapa");
        System.out.println("3. Realizar Combate");
        System.out.println("4. Ver Misiones");
        System.out.println("5. Salir");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String obtenerEntrada() {
        return scanner.nextLine();
    }

    private void pausarPantalla() {
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
}