package managers;

import models.Enemigo;
import models.Jugador;
import models.Ubicacion;
import models.Mapa;

import java.util.List;

public class GestorExploracion {
  private Jugador jugador;
  private GestorMisiones gestorMisiones;
  private Mapa mapa;

  public GestorExploracion(Jugador jugador, GestorMisiones gestorMisiones, Mapa mapa) {
    this.jugador = jugador;
    this.gestorMisiones = gestorMisiones;
    this.mapa = mapa;
  }

  public String viajar(Ubicacion nuevaUbicacion) {
    if (!puedeViajarA(nuevaUbicacion)) {
      return "No puedes viajar a esta ubicación.";
    }
    jugador.setUbicacionActual(nuevaUbicacion);
    return "Has viajado a " + jugador.getUbicacionActual().getNombre();
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
  }

  public boolean puedeViajarA(Ubicacion destino) {
    Ubicacion ubicacionActual = jugador.getUbicacionActual();

    if (ubicacionActual.getUbicacionesAdyacentes().contains(destino)) {
      return true;
    }
    if (destino.estaPuntoViajeRapidoDesbloqueado()) {
      return true;
    }

    return false;
  }

  public List<Ubicacion> getOpcionesViajeDisponibles() {
    List<Ubicacion> opcionesDisponibles = ubicacionActual.getUbicacionesAdyacentes();

    for (Ubicacion punto : mapa.getPuntosViajeRapidoDesbloqueados()) {
      opcionesDisponibles.add(punto);
    }

    return opcionesDisponibles;
  }

  private void desbloquearPuntoViajeRapido(Ubicacion ubicacion) {
    ubicacion.desbloquearPuntoViajeRapido();
  }
}
