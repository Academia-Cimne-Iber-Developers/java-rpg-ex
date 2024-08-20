//Este archivo se encuentra en SRC debido a que por algun motivo no podia importar Juego.java a otras carpetas como managers por ejemplo

import models.Ubicacion;
import models.Enemigo;
import ui.Interfaz;

public class ControladorAcciones {
    private Juego juego;
    private Interfaz interfaz;

    public ControladorAcciones(Juego juego, Interfaz interfaz) {
        this.juego = juego;
        this.interfaz = interfaz;
    }

    public void procesarAccion( String opcion ) {
        //Se intento seguir la plantilla propuesta en el issue
        switch (opcion) {
            case "e":
                Ubicacion nuevUbicacion = juego.explorarUbicacion();
                break;
            case "l":
                Enemigo enemigo = juego.getGestorCombate().getEnemigoEnUbicacionActual();
                if (enemigo!= null) {
                    juego.luchar(enemigo);
                }
                break;
            case "v": {
                String resultadoMapa = juego.verMapa();
                interfaz.mostrarMensaje(resultadoMapa);
                break;
            }
            case "m":{
                String resultadoMovimiento = juego.moverJugador();
                interfaz.mostrarMensaje(resultadoMovimiento);
                break;
            }
            case "i":{
                interfaz.mostrarInventario();
                break;
            }
            case "r":{
                boolean itemRecogido = juego.recogerItem();
                break;
            }
            case "d":{
                boolean itemDejado = juego.dejarItem();
                break;
            }
            case "u":{
                String resultadoUso = juego.usarItem();
                break;
            }
            case "s":{
                String mensajeDespedida = "Gracias por jugar!";
                interfaz.mostrarMensaje(mensajeDespedida);
                System.exit(0);
                break;
            }
            default:{
                String mensajeError = "Opción no válida";
                interfaz.mostrarMensaje(mensajeError);
            }
        }
    }
}