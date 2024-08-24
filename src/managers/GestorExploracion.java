package managers;

import models.Enemigo;
import models.Jugador;
import models.Ubicacion;
import models.Mapa;

public class GestorExploracion {
  private Jugador jugador;
  private GestorMisiones gestorMisiones;

  public GestorExploracion(Jugador jugador, GestorMisiones gestorMisiones) {
    this.jugador = jugador;
    this.gestorMisiones = gestorMisiones;
  }

  public String viajar(Ubicacion nuevaUbicacion) {
    jugador.setUbicacionActual(nuevaUbicacion);
    String resultado = "Has viajado a " + jugador.getUbicacionActual().getNombre();
    return resultado;
  }

  public String verMapa(Mapa mapa) {
    return mapa.mostrarMapa();
  }

  public Ubicacion explorarUbicacion() {
    Ubicacion ubicacionActual = jugador.getUbicacionActual();
    StringBuilder resultado = new StringBuilder();
    resultado.append("Estás en: ").append(ubicacionActual.getNombre()).append("\n");
    resultado.append(ubicacionActual.getDescripcion()).append("\n");

    if (ubicacionActual.esPuntoViajeRapido() && !ubicacionActual.estaPuntoViajeRapidoDesbloqueado()) {
      desbloquearPuntoViajeRapido(ubicacionActual);
      interfaz.mostrarMensaje("Punto de viaje rápido desbloqueado: " + ubicacionActual.getNombre());
  }

    Enemigo enemigo = ubicacionActual.getEnemigoActual();
    if (enemigo == null) {
      resultado.append("- No hay enemigos en esta ubicación.\n");
    } else {
      resultado.append("- Enemigo: ").append(enemigo.getNombre()).append(" | Vida: ").append(enemigo.getVida()).append("\n");
    }

    String itemsEnUbicacion = ubicacionActual.getInventario().listarObjetos();
    if (!itemsEnUbicacion.isEmpty()) {
      resultado.append("- Items en esta ubicación:\n");
      resultado.append(itemsEnUbicacion);
    } else {
      resultado.append("- No hay items para recoger aquí.\n");
    }

    // Actualización de misiones
    gestorMisiones.actualizarMisiones(ubicacionActual.getNombre(), "");

    return ubicacionActual;

    private void desbloquearPuntoViajeRapido(Ubicacion ubicacion) {
      ubicacion.desbloquearPuntoViajeRapido();
  }
  }

  
}