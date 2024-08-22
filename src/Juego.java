import managers.GestorCombate;
import managers.GestorExploracion;
import managers.GestorInventario;
import models.Enemigo;
import models.Jugador;
import models.Mapa;
import models.Ubicacion;
import ui.Interfaz;

public class Juego {
    private Mapa mapa;
    private Interfaz interfaz;
    private Jugador jugador;
    private GestorCombate gestorCombate;
    private GestorExploracion gestorExploracion;
    private GestorInventario gestorInventario;
    private ControladorAcciones controladorAcciones;

    public Juego() {
        mapa = new Mapa();
        jugador = new Jugador("God", mapa.getUbicacion("Pueblo Inicio"));
        interfaz = new Interfaz(mapa, jugador);
        gestorCombate = new GestorCombate(jugador, interfaz);
        gestorExploracion = new GestorExploracion(jugador);
        gestorInventario = new GestorInventario();
        controladorAcciones = new ControladorAcciones(this); 
    }

    public void iniciar() {
        while (true) {
            interfaz.actualizarPantalla();
            String opcion = interfaz.obtenerEntrada();
            controladorAcciones.procesarAccion(opcion);
        }
    }

    public void explorarUbicacion() {
        String resultado = gestorExploracion.explorarUbicacion();
        interfaz.mostrarResultadoExploracion(resultado);
    }

    public void verMapa() {
        interfaz.mostrarMensaje(gestorExploracion.verMapa(mapa));
    }

    public void recogerItem() {
        String nombreItem = interfaz.pedirEntrada("Nombre del item a recoger: ");
        int cantidad = Integer.parseInt(interfaz.pedirEntrada("Cantidad a recoger: "));

        if (gestorInventario.moverObjeto(jugador.getUbicacionActual().getInventario(), jugador.getInventario(),
                nombreItem, cantidad)) {
            interfaz.mostrarMensaje("Item recogido con éxito.");
        } else {
            interfaz.mostrarMensaje("No se pudo recoger el item.");
        }
    }

    public void dejarItem() {
        String nombreItem = interfaz.pedirEntrada("Nombre del item a dejar: ");
        int cantidad = Integer.parseInt(interfaz.pedirEntrada("Cantidad a dejar: "));

        if (gestorInventario.moverObjeto(jugador.getInventario(), jugador.getUbicacionActual().getInventario(),
                nombreItem, cantidad)) {
            interfaz.mostrarMensaje("Item dejado con éxito.");
        } else {
            interfaz.mostrarMensaje("No se pudo dejar el item.");
        }
    }

    public void usarItem() {
        String nombreItem = interfaz.pedirEntrada("Nombre del item a usar: ");

        String resultado = gestorInventario.usarObjeto(jugador, jugador.getInventario(), nombreItem);
        interfaz.mostrarMensaje(resultado);
    }

    public void luchar() {
        Enemigo enemigo = jugador.getUbicacionActual().getEnemigoActual();
        if (enemigo != null) {
            gestorCombate.pelear(enemigo);
            if (jugador.estaVivo()) {
                jugador.getUbicacionActual().eliminarEnemigo();
                interfaz.mostrarMensaje("Has derrotado al enemigo!");
            } else {
                interfaz.mostrarMensaje("Has sido derrotado. Fin del juego.");
                System.exit(0);
            }
        } else {
            interfaz.mostrarMensaje("- No hay enemigos en esta ubicación.");
        }
    }

    public void moverJugador() {
        String destino = interfaz.pedirDestinoViaje();
        Ubicacion nuevaUbicacion = mapa.getUbicacion(destino);
        if (nuevaUbicacion != null) {
            String resultado = gestorExploracion.viajar(nuevaUbicacion);
            interfaz.mostrarMensaje(resultado);
            interfaz.mostrarResultadoViaje(true, destino);
        } else {
            interfaz.mostrarResultadoViaje(false, destino);
        }
    }

    public void mostrarInventario() {
        interfaz.mostrarInventario();
    }

    public void salir() {
        interfaz.mostrarMensaje("Gracias por jugar!");
        System.exit(0);
    }

    public void opcionInvalida() {
        interfaz.mostrarMensaje("Opción no válida.");
    }
    public static void main(String[] args) {
        new Juego().iniciar();
    }
}