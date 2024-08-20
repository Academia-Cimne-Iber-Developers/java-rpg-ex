import managers.GestorCombate;
import managers.GestorExploracion;
import managers.GestorInventario;
import managers.GestorMisiones;
import models.*;
import ui.Interfaz;

public class Juego {
    private Mapa mapa;
    private Jugador jugador;
    private Interfaz interfaz;
    private GestorCombate gestorCombate;
    private GestorExploracion gestorExploracion;
    private GestorInventario gestorInventario;
    private GestorMisiones gestorMisiones;
    private ControladorAcciones controladorAcciones; // Declarar el controlador de acciones

    public Juego() {
        mapa = new Mapa();
        jugador = new Jugador("Link", mapa.getUbicacion("Pueblo Inicio"));
        gestorMisiones = new GestorMisiones(jugador);
        gestorExploracion = new GestorExploracion(jugador, gestorMisiones);
        interfaz = new Interfaz(mapa, jugador);
        gestorCombate = new GestorCombate(jugador, interfaz);
        gestorInventario = new GestorInventario();
        controladorAcciones = new ControladorAcciones(this, interfaz); // Inicializar el controlador de acciones
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

    public GestorMisiones getGestorMisiones() {
        return gestorMisiones;
    }

    public String moverJugador() {
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
        return destino;
    }

    public void iniciar() {
        while (true) {
            interfaz.actualizarPantalla();
            String opcion = interfaz.obtenerEntrada();
            controladorAcciones.procesarAccion(opcion);
        }
    }

    public Ubicacion explorarUbicacion() {
        String resultado = String.valueOf(gestorExploracion.explorarUbicacion());
        interfaz.mostrarResultadoExploracion(resultado, gestorMisiones);

        // Muestra el progreso o finalización de todas las misiones activas después de explorar
        for (Mision mision : gestorMisiones.getMisionesActivas()) {
            interfaz.mostrarInfoMision(mision);
        }
        return null;
    }

    private void luchar() {
        Enemigo enemigo = jugador.getUbicacionActual().getEnemigoActual();
        if (enemigo == null) {
            interfaz.mostrarMensaje("No hay enemigos en esta ubicación.");
        }else {
            // gestorCombate.elegirAccionJugador(jugador, enemigo);
            while (jugador.estaVivo() && enemigo.estaVivo()){
                gestorCombate.elegirAccionJugador(jugador, enemigo);
                // interfaz.mostrarMensaje(jugador.atacar(enemigo));
                if (enemigo.estaVivo()) {
                    interfaz.mostrarMensaje("El enemigo se prepara para atacar");
                    interfaz.mostrarMensaje(enemigo.atacar(jugador));
                }
                interfaz.mostrarMensaje("Vida jugador: " + jugador.getVida() + " | Vida enemigo: " + enemigo.getVida());
            }
            if (jugador.estaVivo()){
                jugador.getUbicacionActual().eliminarEnemigo();
                interfaz.mostrarMensaje("Derrotaste a: " + enemigo.getNombre());
                String enemigoDerrotado = enemigo.getNombre();

                // Actualizar la misión con el nombre del enemigo derrotado inmediatamente después de la pelea
                gestorMisiones.actualizarMisiones(jugador.getUbicacionActual().getNombre(), enemigoDerrotado);

                interfaz.mostrarResultadoCombate("Has derrotado a " + enemigoDerrotado, gestorMisiones);
            } else {

                interfaz.mostrarMensaje("Moriste, fin del juego.");
                System.exit(0);
            }
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

    public String usarItem() {
        String nombreItem = interfaz.pedirEntrada("Nombre del item a usar: ");

        // Obtenemos el resultado de usar el objeto
        ResultadoUsoItem resultado = gestorInventario.usarObjeto(jugador, jugador.getInventario(), nombreItem);

        // Manejamos el resultado y mostramos el mensaje que corresponde
        if (resultado.isExito()) {
            interfaz.mostrarMensaje("Éxito: " + resultado.getMensaje());
        } else {
            interfaz.mostrarMensaje("Error: " + resultado.getMensaje());
        }
        return resultado.getMensaje();
    }

    public void agregarNuevaMision(Mision nuevaMision) {
        gestorMisiones.agregarMision(nuevaMision);
        interfaz.mostrarMensaje("¡Nueva misión obtenida: " + nuevaMision.getDescripcion() + "!");
    }

    public static void main(String[] args) {
        new Juego().iniciar();
    }
}
