package managers;

import models.Enemigo;
import models.Jugador;
import ui.Interfaz;

public class GestorCombate {
  private Jugador jugador;
  private Interfaz interfaz;
  private double porcentajeCritico;

  public GestorCombate(Jugador jugador,Interfaz interfaz){
    this.jugador = jugador;
    this.interfaz = interfaz;
    this.porcentajeCritico = 0;
  }
  public Interfaz getInterfaz() {
    return interfaz;
  }

  public void elegirAccionJugador(Jugador jugador, Enemigo enemigo) {
    interfaz.mostrarOpcionesCombate(porcentajeCritico);
    int eleccion = Integer.parseInt(interfaz.obtenerEntrada());
    AccionCombate accion = null;
    switch (eleccion) {
        case 1:
            accion = new AccionAtaque();
            break;
        case 2:
            accion = new AccionBloqueo();
            break;
        case 3:
            accion = new AccionCarga();
            break;
        case 4:
            accion = new AccionCuracion();
            break;
        default:
            interfaz.mostrarMensaje("Opción no válida.");
            return;
    }
    if (accion != null) {
        accion.ejecutar(jugador, enemigo, this);
    }
}

public double getPorcentajeCritico() {
  return porcentajeCritico;
}

public void aumentarPorcentajeCritico(double incremento) {
  porcentajeCritico = Math.min(porcentajeCritico + incremento, 80);
}

public void resetearPorcentajeCritico() {
  porcentajeCritico = 0;
}

  public void pelear(Enemigo enemigo){

    while (jugador.estaVivo() && enemigo.estaVivo()){
        interfaz.mostrarMensaje(jugador.atacar(enemigo));
        if (enemigo.estaVivo()) {
          interfaz.mostrarMensaje(enemigo.atacar(jugador));
        }
        interfaz.mostrarMensaje("Vida jugador: " + jugador.getVida() + " | Vida enemigo: " + enemigo.getVida());
    }
    if (jugador.estaVivo()){
      interfaz.mostrarMensaje("Derrotaste a: " + enemigo.getNombre());
    } else {
      interfaz.mostrarMensaje("Moriste, fin del juego.");
    }
  }

}


