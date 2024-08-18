import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import managers.GestorCombate;
import managers.GestorExploracion;
import managers.GestorInventario;
import models.Enemigo;
import models.IMision;
import models.Jugador;
import models.Mapa;
import models.MisionAtesorar;
import models.MisionExploracion;
import models.Ubicacion;
import ui.Interfaz;

public class Juego {
    private Mapa mapa;
    private Interfaz interfaz;
    private Jugador jugador;
    private GestorCombate gestorCombate;
    private GestorExploracion gestorExploracion;
    private GestorInventario gestorInventario;

    public Juego() {
        mapa = new Mapa();
        jugador = new Jugador("Link", mapa.getUbicacion("Pueblo Inicio"));
        interfaz = new Interfaz(mapa, jugador);
        gestorCombate = new GestorCombate(jugador, interfaz);
        gestorExploracion = new GestorExploracion(jugador);
        gestorInventario = new GestorInventario();
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
                case "l":
                    luchar();
                    break;                
                case "mi":
                    interfaz.mostrarMisiones();
                    break;
                case "ex":
                    crearNuevaMisionExploracion();
                    break;      
                case "at":
                    crearNuevaMisionAtesorar();
                    break;              
                case "s":
                    interfaz.mostrarMensaje("Gracias por jugar!");
                    return;
                default:
                    interfaz.mostrarMensaje("Opción no válida.");
            }
        }
    }

    private void explorarUbicacion() {
        String resultado = gestorExploracion.explorarUbicacion();
        interfaz.mostrarResultadoExploracion(resultado);
    }

    private void verMapa(){
        interfaz.mostrarMensaje(gestorExploracion.verMapa(mapa));
    }

    private void recogerItem() {
        String nombreItem = interfaz.pedirEntrada("Nombre del item a recoger: ");
        int cantidad = Integer.parseInt(interfaz.pedirEntrada("Cantidad a recoger: "));

        if (gestorInventario.moverObjeto(jugador.getUbicacionActual().getInventario(), jugador.getInventario(), nombreItem, cantidad)) {
            interfaz.mostrarMensaje("Item recogido con éxito.");
            //actualicemos las misiones
            jugador.actualizarMisiones("m_atesorar", nombreItem);
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

    private void usarItem(){
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

    private void moverJugador() {
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

    private void crearNuevaMisionExploracion() {
        //vamos a obterner los destinos posibles
        Map<String, Ubicacion> mapa = new Mapa().getUbicaciones();
        //voy a crear una lista de las keys de las ubicaciones
        List<String> destinos = new ArrayList<>(mapa.keySet());
        //vamos a elegir un destino al azar para crear la nueva mision
        Random random = new Random();
        String destino = destinos.get(random.nextInt(destinos.size()));

        int visitasRequeridas = random.nextInt(3) + 1;
        String descripcion = "Explorar " + destino + " por " + visitasRequeridas + " vez(es).";
        //vamos a crear una nueva mision        
        IMision nuevaMision = new MisionExploracion(descripcion, destino, visitasRequeridas); 
        //agregamos la nueva mision
        this.jugador.agregarNuevaMision(nuevaMision);
        //mostramos el resultado
        interfaz.mostrarMisiones();
        
    }

    private void crearNuevaMisionAtesorar() {
        //vamos a obtener los posibles items desde el mapa creado
        Set<String> conjuntoItems = this.mapa.getConjuntoItems();
        
        //vamos a elegir un item al azar para crear la nueva mision
        List<String> listaItems = new ArrayList<>(conjuntoItems);
        Random random = new Random();
        String tesoro = listaItems.get(random.nextInt(listaItems.size()));

        int numeroRequerido = random.nextInt(3) + 1;
        String descripcion = "Obtener " + tesoro + " atesorando " + numeroRequerido + " cantidad de veces.";
        //vamos a crear una nueva mision        
        IMision nuevaMisionAtesorar = new MisionAtesorar(descripcion, tesoro, numeroRequerido); 
        //agregamos la nueva mision
        this.jugador.agregarNuevaMision(nuevaMisionAtesorar);
        //mostramos el resultado
        interfaz.mostrarMisiones();
        
    }

    public static void main(String[] args) {
        new Juego().iniciar();
    }
}