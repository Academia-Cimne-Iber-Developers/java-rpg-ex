package ui;

import java.util.Scanner;

import managers.GestorMisiones;
import models.*;

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

        System.out.print("Presioná ENTER para continuar...");
        scanner.nextLine();  // Pausa antes de volver al menú principal
    }


    public String pedirEntrada(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private void mostrarInfoJugador() {
        System.out.println("=== ESTADO DEL JUGADOR ===");
        System.out.printf("Nombre: %s | Vida: %d | Ataque: %d\n",
                jugador.getNombre(), jugador.getVida(), jugador.getAtaque());
        System.out.println("============================");
    }

    private void mostrarUbicacionActual() {
        Ubicacion ubicacionActual = jugador.getUbicacionActual();
        System.out.println("\n=== UBICACIÓN ACTUAL ===");
        System.out.println(ubicacionActual.getNombre());
        System.out.println(ubicacionActual.getDescripcion());
        System.out.println("=========================");
    }

    private void mostrarOpciones() {
        System.out.println("\n¿Qué querés hacer?");
        System.out.println("[E]xplorar   [M]over   [V]er mapa");
        System.out.println("[I]nventario [R]ecoger [U]sar [D]ejar item");
        System.out.println("[L]uchar     [Mis]iones");  // Nueva opción para ver misiones
        System.out.println("====================================");
        System.out.println("[S]alir");
        System.out.print("Elegí una opción: ");
    }

    public String obtenerEntrada() {
        return scanner.nextLine().toLowerCase();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
        System.out.print("Presioná ENTER para continuar...");
        scanner.nextLine();
    }

    public void mostrarInventario() {
        System.out.println("\n=== INVENTARIO ===");
        String resultado = jugador.mostrarInventario();
        System.out.println(resultado);
        System.out.println("==================");
        System.out.print("Presioná ENTER para continuar...");
        scanner.nextLine();
    }

    public void mostrarResultadoExploracion(String resultado, GestorMisiones gestorMisiones) {
        System.out.println("\n=== RESULTADO DE LA EXPLORACIÓN ===");
        System.out.println(resultado);
        System.out.println("====================================");

        // Verificar si alguna misión se ha completado después de la exploración
        for (Mision mision : gestorMisiones.getMisionesActivas()) {
            if (mision instanceof MisionExploracion) {
                MisionExploracion misionExploracion = (MisionExploracion) mision;
                if (misionExploracion.esCompletada()) {
                    System.out.println("¡Has completado la misión: " + misionExploracion.getDescripcion() + "!");
                } else {
                    System.out.println("Progreso en misión: " + misionExploracion.getDescripcion() +
                            " (" + misionExploracion.getVecesVisitada() + "/" + misionExploracion.getVecesRequeridas() + ")");
                }
            }
        }
        System.out.print("Presioná ENTER para continuar...");
        scanner.nextLine();
    }



    public void mostrarInfoMision(MisionExploracion mision) {
        if (mision.esCompletada()) {
            System.out.println("¡Has completado la misión: " + mision.getDescripcion() + "!");
        } else {
            System.out.println("Progreso en misión: " + mision.getDescripcion() +
                    " (" + mision.getVecesVisitada() + "/" + mision.getVecesRequeridas() + ")");
        }
    }

    public String pedirDestinoViaje() {
        System.out.print("\n¿A dónde quieres ir?: ");
        return scanner.nextLine();
    }

    public void mostrarResultadoViaje(boolean exito, String destino) {
        if (exito) {
            System.out.println("\nHas viajado con éxito a " + destino);
        } else {
            System.out.println("\nNo puedes viajar a " + destino);
        }
        System.out.print("Presioná ENTER para continuar...");
        scanner.nextLine();
    }
}