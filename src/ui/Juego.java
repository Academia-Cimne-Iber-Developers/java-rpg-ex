package ui;

import managers.GestorCombate;
import managers.GestorExploracion;
import managers.GestorInventario;
import managers.GestorMisiones;
import models.Mapa;
import models.Jugador;
import models.Mision;
import models.MisionExploracion;
import models.Ubicacion;
import models.Enemigo;



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
        //gestorMisiones.agregarMision());
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
                agregarNuevaMision(new MisionExploracion("Explora el fondo de la cueva", "Fondo de la Cueva", 1));
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
                    System.out.println("Entrando en la opción de mostrar misiones...");  // Depuración
                    break;
                case "s":
                    interfaz.mostrarMensaje("Gracias por jugar!");
                    return;
                default:
                    interfaz.mostrarMensaje("Opción no válida.");
            }

            gestorMisiones.actualizarMisiones(this);
        }
    }

    private void explorarUbicacion() {
        String resultado = gestorExploracion.explorarUbicacion();
        interfaz.mostrarResultadoExploracion(resultado);

        // Verificar misiones después de explorar
        for (Mision mision : gestorMisiones.getMisionesActivas()) {
            if (mision instanceof MisionExploracion) {
                ((MisionExploracion) mision).verificarYActualizar(this);  // Verificar la misión solo durante la exploración
            }
        }

        // Muestra el mensaje si alguna misión se completó
        for (Mision mision : gestorMisiones.getMisionesActivas()) {
            if (mision.estaCompleta()) {
                interfaz.mostrarMensaje("¡Has completado la misión: " + mision.getDescripcion() + "!");
            }
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
                interfaz.mostrarMensaje("Has derrotado al enemigo!");
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