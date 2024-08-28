package managers;

import java.util.List;
import models.Enemigo;
import models.Jugador;
import models.Mapa;
import models.Ubicacion;

public class GestorExploracion {
  private Jugador jugador;
  private GestorMisiones gestorMisiones;

  public GestorExploracion(Jugador jugador, GestorMisiones gestorMisiones) {
    this.jugador = jugador;
    this.gestorMisiones = gestorMisiones;
  }

  public String viajar(Ubicacion nuevaUbicacion) {
    if (puedeViajarA(nuevaUbicacion)) {
      jugador.setUbicacionActual(nuevaUbicacion);
      if (nuevaUbicacion.esPuntoViajeRapido()) {
        nuevaUbicacion.desbloquearPuntoViajeRapido();
      }
      return "Has viajado a " + jugador.getUbicacionActual().getNombre();
    } else {
      return "No puedes viajar a esa ubicación.";
    }
  }

  public boolean puedeViajarA(Ubicacion destino) {
    Ubicacion ubicacionActual = jugador.getUbicacionActual();
    return ubicacionActual.getUbicacionesAdyacentes().contains(destino) ||
            (ubicacionActual.estaPuntoViajeRapidoDesbloqueado() && destino.estaPuntoViajeRapidoDesbloqueado());
  }

  public List<Ubicacion> getOpcionesViajeDisponibles(Mapa mapa) {
    Ubicacion ubicacionActual = jugador.getUbicacionActual();
    List<Ubicacion> opciones = ubicacionActual.getUbicacionesAdyacentes();
    if (ubicacionActual.estaPuntoViajeRapidoDesbloqueado()) {
      opciones.addAll(mapa.getPuntosViajeRapidoDesbloqueados());
    }
    return opciones;
  }

  public String verMapa(Mapa mapa) {
    return mapa.mostrarMapa();
  }

  public Ubicacion explorarUbicacion() {
    Ubicacion ubicacionActual = jugador.getUbicacionActual();
    StringBuilder resultado = new StringBuilder();
    resultado.append("Estás en: ").append(ubicacionActual.getNombre()).append("\n");
    resultado.append(ubicacionActual.getDescripcion()).append("\n");

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

    // Lógica de desbloqueo de puntos de viaje rápido
    if (ubicacionActual.esPuntoViajeRapido() && !ubicacionActual.estaPuntoViajeRapidoDesbloqueado()) {
      ubicacionActual.desbloquearPuntoViajeRapido();
      resultado.append("¡Has desbloqueado un punto de viaje rápido en ").append(ubicacionActual.getNombre()).append("!\n");
    }

    // Actualización de misiones
    gestorMisiones.actualizarMisiones(ubicacionActual.getNombre(), "");

    return ubicacionActual;
  }
}
