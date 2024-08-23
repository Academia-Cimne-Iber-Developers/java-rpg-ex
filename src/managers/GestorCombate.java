package managers;

import models.Enemigo;
import models.Jugador;
import models.Ubicacion;
import ui.Interfaz;

public class GestorCombate {
  private Jugador jugador;
  private Interfaz interfaz;

  public GestorCombate(Jugador jugador,Interfaz interfaz){
    this.jugador = jugador;
    this.interfaz = interfaz;
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
      interfaz.mostrarMensaje("Moriste, fin del .");
    }
  }

  public Enemigo getEnemigoEnUbicacionActual() {
    // Implementación para buscar enemigo en la ubicación actual
    Ubicacion ubicacionActual = jugador.getUbicacionActual();
    return ubicacionActual.getEnemigoActual(); // Devuelve el enemigo encontrado
  }
}
