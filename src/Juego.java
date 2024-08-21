import managers.GestorCombate;
import managers.GestorExploracion;
import managers.GestorInventario;
import managers.GestorMisiones;
import models.*;
import ui.Interfaz;

public class Juego {
    private Mapa mapa;
    private Interfaz interfaz;
    private Jugador jugador;
    private GestorCombate gestorCombate;
    private GestorExploracion gestorExploracion;
    private GestorInventario gestorInventario;
    private GestorMisiones gestorMisiones;

    public Juego() {
        mapa = new Mapa();
        jugador = new Jugador("Link", mapa.getUbicacion("Pueblo Inicio"));
        interfaz = new Interfaz(mapa, jugador);
        gestorCombate = new GestorCombate(jugador, interfaz);
        gestorExploracion = new GestorExploracion(jugador);
        gestorInventario = new GestorInventario();
        gestorMisiones = new GestorMisiones(jugador);
        inicializarMisiones();
    }

    private void inicializarMisiones() {
        gestorMisiones.agregarMision(new MisionExploracion("Explora la cueva profunda", "Cueva Profunda", 1));
        gestorMisiones.agregarMision(new MisionCombate("Derrotar un Lobo en Bosque Oscuro", "lobo", 1));
    }

    public Mapa getMapa() {
        return mapa;
    }

    public Jugador getJugador() {
        return jugador;
    }

    private void moverJugador() {
        String destino = interfaz.pedirDestinoViaje();
        Ubicacion nuevaUbicacion = mapa.getUbicacion(destino);
        if (nuevaUbicacion != null) {
            String resultado = gestorExploracion.viajar(nuevaUbicacion);
            interfaz.mostrarMensaje(resultado);
            interfaz.mostrarResultadoViaje(true, destino);

            // Ejemplo de agregar una nueva misión cuando el jugador llega a la "Cueva Profunda"
            if (destino.equals("Cueva Profunda")) {
                agregarNuevaMision(new MisionExploracion("Explora la cueva profunda", "Cueva Profunda", 1));
            }
        } else {
            interfaz.mostrarResultadoViaje(false, destino);
        }
    }

    public void iniciar() {
        while (true) {
            interfaz.actualizarPantalla();
            String opcion = interfaz.obtenerEntrada();

            switch (opcion) {
                case "e":
                    explorarUbicacion();
                    break;
                case "v":
                    verMapa();
                    break;
                case "m":
                    moverJugador();
                    break;
                case "i":
                    interfaz.mostrarInventario();
                    break;
                case "r":
                    recogerItem();
                    break;
                case "d":
                    dejarItem();
                    break;
                case "u":
                    usarItem();
                    break;
                case "l":
                    luchar();
                    break;
                case "mis":
                    interfaz.mostrarMisiones(gestorMisiones);
                    break;
                case "s":
                    interfaz.mostrarMensaje("Gracias por jugar!");
                    return;
                default:
                    interfaz.mostrarMensaje("Opción no válida.");
            }

            String ubicacionActual = jugador.getUbicacionActual().getNombre();
            String enemigoDerrotado = ""; // Este valor debería venir del resultado de un combate
            gestorMisiones.actualizarMisiones(ubicacionActual, enemigoDerrotado);
        }
    }

    private void explorarUbicacion() {
        String resultado = gestorExploracion.explorarUbicacion();
        interfaz.mostrarResultadoExploracion(resultado, gestorMisiones);

        // Muestra el progreso o finalización de todas las misiones activas después de explorar
        for (Mision mision : gestorMisiones.getMisionesActivas()) {
            interfaz.mostrarInfoMision(mision);
        }
    }

    private void verMapa() {
        interfaz.mostrarMensaje(gestorExploracion.verMapa(mapa));
    }

    private void recogerItem() {
        String nombreItem = interfaz.pedirEntrada("Nombre del item a recoger: ");
        int cantidad = Integer.parseInt(interfaz.pedirEntrada("Cantidad a recoger: "));

        if (gestorInventario.moverObjeto(jugador.getUbicacionActual().getInventario(), jugador.getInventario(), nombreItem, cantidad)) {
            interfaz.mostrarMensaje("Item recogido con éxito.");
        } else {
            interfaz.mostrarMensaje("No se pudo recoger el item.");
        }
    }

    private void dejarItem() {
        String nombreItem = interfaz.pedirEntrada("Nombre del item a dejar: ");
        int cantidad = Integer.parseInt(interfaz.pedirEntrada("Cantidad a dejar: "));

        if (gestorInventario.moverObjeto(jugador.getInventario(), jugador.getUbicacionActual().getInventario(), nombreItem, cantidad)) {
            interfaz.mostrarMensaje("Item dejado con éxito.");
        } else {
            interfaz.mostrarMensaje("No se pudo dejar el item.");
        }
    }

    private void usarItem() {
        String nombreItem = interfaz.pedirEntrada("Nombre del item a usar: ");

        String resultado = gestorInventario.usarObjeto(jugador, jugador.getInventario(), nombreItem);
        interfaz.mostrarMensaje(resultado);
    }

    private void luchar() {
        Enemigo enemigo = jugador.getUbicacionActual().getEnemigoActual();
        if (enemigo != null) {
            gestorCombate.pelear(enemigo);
            if (jugador.estaVivo()) {
                jugador.getUbicacionActual().eliminarEnemigo();
                String enemigoDerrotado = enemigo.getNombre();

                // Actualizar la misión con el nombre del enemigo derrotado inmediatamente después de la pelea
                gestorMisiones.actualizarMisiones(jugador.getUbicacionActual().getNombre(), enemigoDerrotado);

                interfaz.mostrarResultadoCombate("Has derrotado a " + enemigoDerrotado, gestorMisiones);
            } else {
                interfaz.mostrarMensaje("Has sido derrotado. Fin del juego.");
                System.exit(0);
            }
        } else {
            interfaz.mostrarMensaje("- No hay enemigos en esta ubicación.");
        }
    }

    private void agregarNuevaMision(Mision nuevaMision) {
        gestorMisiones.agregarMision(nuevaMision);
        interfaz.mostrarMensaje("¡Nueva misión obtenida: " + nuevaMision.getDescripcion() + "!");
    }

    public static void main(String[] args) {
        new Juego().iniciar();
    }
}
