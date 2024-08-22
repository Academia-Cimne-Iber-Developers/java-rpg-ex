
public class ControladorAcciones {
    private Juego juego;

    public ControladorAcciones(Juego juego) {
        this.juego = juego;
    }
 
    public void procesarAccion(String opcion) {
        switch (opcion) {
            case "e":
                juego.explorarUbicacion();
                break;
            case "v":
                juego.verMapa();
                break;
            case "m":
                juego.moverJugador();
                break;
            case "i":
                juego.mostrarInventario();
                break;
            case "r":
                juego.recogerItem();
                break;
            case "d":
                juego.dejarItem();
                break;
            case "u":
                juego.usarItem();
                break;
            case "l":
                juego.luchar();
                break;
            case "s":
                juego.salir();
                break;
            default:
                juego.opcionInvalida();
        }
    }}
