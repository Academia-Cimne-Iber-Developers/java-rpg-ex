package ui;
import java.util.Scanner;

import models.Jugador;
import models.Mapa;
import models.Ubicacion;

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
        System.out.println("[L]uchar");
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

    public void mostrarResultadoExploracion(String resultado) {
        System.out.println("\n=== RESULTADO DE LA EXPLORACIÓN ===");
        System.out.println(resultado);
        System.out.println("====================================");
        System.out.print("Presioná ENTER para continuar...");
        scanner.nextLine();
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