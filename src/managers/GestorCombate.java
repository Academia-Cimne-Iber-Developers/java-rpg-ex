package managers;

import models.Enemigo;
import models.Jugador;
import models.Ubicacion;
import ui.Interfaz;
import configurations.ConfiguracionJuego;
import models.Dificultad;

public class GestorCombate {
  private Jugador jugador;
  private Interfaz interfaz;

  public GestorCombate(Jugador jugador,Interfaz interfaz){
    this.jugador = jugador;
    this.interfaz = interfaz;
  }

  public void pelear(Enemigo enemigo){
    // Obtiene la dificultad actual del juego
    Dificultad dificultad = ConfiguracionJuego.getInstancia().getDificultad();

    while (jugador.estaVivo() && enemigo.estaVivo()){
        // Calcula el daño infligido al enemigo por el jugador
        int danioAjustadoAlEnemigo = calcularDanioAjustado(jugador.getDanioBase(), dificultad);
        enemigo.recibirDanio(danioAjustadoAlEnemigo);
        interfaz.mostrarMensaje("Has infligido " + danioAjustadoAlEnemigo + " puntos de daño al enemigo " + enemigo.getNombre() + ".");
        
        if (enemigo.estaVivo()) {
          interfaz.mostrarMensaje(enemigo.atacar(jugador));
        }
        interfaz.mostrarMensaje("Vida jugador: " + jugador.getVida() + " | Vida enemigo: " + enemigo.getVida());
    }
    if (jugador.estaVivo()){
      interfaz.mostrarMensaje("Derrotaste a: " + enemigo.getNombre());
    } else {
      interfaz.mostrarMensaje("Has sido derrotado, fin del juego.");
    }
  }

  //Calcula el daño infligido al enemigo ajustado según la dificultad seleccionada.
  private int calcularDanioAjustado(int danioBase, Dificultad dificultad) {
    // Ajusta el multiplicador para que en Fácil sea > 1 y en Difícil < 1
    double multiplicador = 2 - dificultad.getMultiplicador();
    return (int) (danioBase * multiplicador);
}

  public Enemigo getEnemigoEnUbicacionActual() {
    // Implementación para buscar enemigo en la ubicación actual
    Ubicacion ubicacionActual = jugador.getUbicacionActual();
    return ubicacionActual.getEnemigoActual(); // Devuelve el enemigo encontrado
  }
}
