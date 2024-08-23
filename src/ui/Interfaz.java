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
        System.out.println("=== ESTADO DEL JUGADOR ===");
        System.out.printf("Nombre: %s | Vida: %d | Ataque: %d\n", jugador.getNombre(), jugador.getVida(), jugador.getAtaque());
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
        pausarPantalla();
    }

    public void mostrarInventario() {
        System.out.println("\n=== INVENTARIO ===");
        String resultado = jugador.mostrarInventario();
        System.out.println(resultado);
        System.out.println("==================");
        pausarPantalla();
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
                    System.out.println("Progreso en misión: " + misionExploracion.getDescripcion() + " (" + misionExploracion.getVecesVisitada() + "/" + misionExploracion.getVecesRequeridas() + ")");
                }
            }
        }
        pausarPantalla();
    }

    public void mostrarInfoMision(Mision mision) {
        if (mision instanceof MisionExploracion) {
            MisionExploracion misionExploracion = (MisionExploracion) mision;
            if (misionExploracion.esCompletada()) {
                System.out.println("¡Has completado la misión: " + misionExploracion.getDescripcion() + "!");
            } else {
                System.out.println("Progreso en misión: " + misionExploracion.getDescripcion() + " (" + misionExploracion.getVecesVisitada() + "/" + misionExploracion.getVecesRequeridas() + ")");
            }
        } else if (mision instanceof MisionCombate) {
            MisionCombate misionCombate = (MisionCombate) mision;
            if (misionCombate.esCompletada()) {
                System.out.println("¡Has completado la misión: " + misionCombate.getDescripcion() + "!");
            } else {
                System.out.println("Progreso en misión: " + misionCombate.getDescripcion() + " (" + misionCombate.getCantidadDerrotada() + "/" + misionCombate.getCantidadRequerida() + ")");
            }
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
        pausarPantalla();
    }

    public void mostrarResultadoCombate(String resultado, GestorMisiones gestorMisiones) {
        System.out.println("\n=== RESULTADO DEL COMBATE ===");
        System.out.println(resultado);
        System.out.println("====================================");

        for (Mision mision : gestorMisiones.getMisionesActivas()) {
            if (mision instanceof MisionCombate) {
                MisionCombate misionCombate = (MisionCombate) mision;
                mostrarInfoMision(misionCombate);
            }
        }
        pausarPantalla();
    }

    private void pausarPantalla() {
        System.out.print("Presioná ENTER para continuar...");
        scanner.nextLine();
    }
    public void mostrarOpcionesCombate(double porcentajeCritico) {
        System.out.println("\n=== OPCIONES DE COMBATE ===");
        System.out.println("1. Atacar");
        System.out.println("2. Bloquear");
        System.out.println("3. Cargar");
        System.out.println("4. Curarse");
        System.out.println("===========================");
        System.out.println("Probabilidad de crítico: " + porcentajeCritico + "%");
        System.out.print("Elegí una opción: ");
    }
}