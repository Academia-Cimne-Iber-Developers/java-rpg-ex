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
    private Jugador jugador;
    private Interfaz interfaz;
    private GestorCombate gestorCombate;
    private GestorExploracion gestorExploracion;
    private GestorInventario gestorInventario;
    private ControladorAcciones controladorAcciones;

    public Juego() {
        this.mapa = new Mapa();
        this.jugador = new Jugador("Link", mapa.getUbicacion("Pueblo Inicio"));
        this.interfaz = new Interfaz(mapa, jugador);
        this.gestorCombate = new GestorCombate(jugador, interfaz);
        this.gestorExploracion = new GestorExploracion(jugador);
        this.gestorInventario = new GestorInventario();
        this.controladorAcciones = new ControladorAcciones(this, interfaz);
    }

    public void iniciar() {
        while (true) {
            interfaz.actualizarPantalla();
            String opcion = interfaz.obtenerEntrada();
            controladorAcciones.procesarAccion(opcion);
        }
    }

    public Ubicacion explorarUbicacion() {
        Ubicacion nuevaUbicacion = gestorExploracion.explorarUbicacion();  
        interfaz.mostrarResultadoExploracion("Has explorado: " + nuevaUbicacion.getNombre());
        jugador.setUbicacionActual(nuevaUbicacion);
        return nuevaUbicacion;
    }

    public boolean luchar(Enemigo enemigo) {
        gestorCombate.pelear(enemigo);
        if (jugador.estaVivo()) {
            jugador.getUbicacionActual().eliminarEnemigo();
            interfaz.mostrarMensaje("¡Has derrotado al enemigo!");
            return true;
        } else {
            interfaz.mostrarMensaje("Has sido derrotado. Fin del juego.");
            System.exit(0);
            return false;
        }
    }

    public GestorCombate getGestorCombate() {
        return gestorCombate;
    }

    public String verMapa(){
        return gestorExploracion.verMapa(mapa);
    }

    public boolean recogerItem() {
        String nombreItem = interfaz.pedirEntrada("Nombre del item a recoger: ");
        int cantidad = Integer.parseInt(interfaz.pedirEntrada("Cantidad a recoger: "));

        return gestorInventario.moverObjeto(jugador.getUbicacionActual().getInventario(), jugador.getInventario(), nombreItem, cantidad);
    }

    public boolean dejarItem() {
        String nombreItem = interfaz.pedirEntrada("Nombre del item a dejar: ");
        int cantidad = Integer.parseInt(interfaz.pedirEntrada("Cantidad a dejar: "));

        return gestorInventario.moverObjeto(jugador.getInventario(), jugador.getUbicacionActual().getInventario(), nombreItem, cantidad);
    }

    public String usarItem(){
        String nombreItem = interfaz.pedirEntrada("Nombre del item a usar: ");
        return gestorInventario.usarObjeto(jugador, jugador.getInventario(), nombreItem);
    }

    public String moverJugador() {
        String destino = interfaz.pedirDestinoViaje();
        Ubicacion nuevaUbicacion = mapa.getUbicacion(destino);
        if (nuevaUbicacion != null) {
            return gestorExploracion.viajar(nuevaUbicacion);
        } else {
            return "No se encontró el destino: " + destino;
        }
    }

    public static void main(String[] args) {
        new Juego().iniciar();
    }
}