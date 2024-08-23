import java.util.ArrayList;
import java.util.List;
import managers.GestorCombate;
import managers.GestorExploracion;
import managers.GestorInventario;
import managers.GestorMisiones;
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
    private List<IMision> misionesDisponibles = new ArrayList<>();
    private GestorMisiones gestorMisiones;

    public Juego() {
        mapa = new Mapa();
        jugador = new Jugador("Link", mapa.getUbicacion("Pueblo Inicio"));
        interfaz = new Interfaz(mapa, jugador);
        gestorCombate = new GestorCombate(jugador, interfaz);
        gestorExploracion = new GestorExploracion(jugador);
        gestorInventario = new GestorInventario();
        CrearMisiones();
        gestorMisiones = new GestorMisiones(jugador);
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
                    String data = this.gestorMisiones.mostrarMisiones();
                    interfaz.mostrarMisiones(data);
                    break;
                case "1":
                    asignarMision(misionesDisponibles.get(0));   
                    break;      
                case "2":
                    asignarMision(misionesDisponibles.get(1));

                    break;    
                case "3":
                    asignarMision(misionesDisponibles.get(2));                    
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
            gestorMisiones.actualizarMisiones("m_atesorar", nombreItem);            
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
            this.gestorMisiones.actualizarMisiones("m_exploracion", destino);            
            String resultado = gestorExploracion.viajar(nuevaUbicacion);
            interfaz.mostrarMensaje(resultado);
            interfaz.mostrarResultadoViaje(true, destino);
            
        } else {
            interfaz.mostrarResultadoViaje(false, destino);
        }
    }

    private void asignarMision(IMision mision){
        this.gestorMisiones.agregarNuevaMision(mision);
        interfaz.mostrarMisiones(this.gestorMisiones.mostrarMisiones());
    }


    private void CrearMisiones(){

        this.misionesDisponibles.add(new MisionExploracion(
            "Explorar Pueblo Inicio por 3 veces.",
            "Pueblo Inicio", 
            3));

        this.misionesDisponibles.add(new MisionExploracion(
            "Explorar Cueva Profunda por 2 veces.",
            "Cueva Profunda", 
            2
        ));

        this.misionesDisponibles.add(new MisionAtesorar(
            "Obtener Pocion atesorando 2 cantidad de veces.",
            "Pocion", 
            2
        ));
    }



    public static void main(String[] args) {
        new Juego().iniciar();
    }
}