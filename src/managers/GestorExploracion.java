package managers;

import models.Enemigo;
import models.Jugador;
import models.Ubicacion;
import models.Mapa;

public class GestorExploracion {
  private Jugador jugador;

  public GestorExploracion(Jugador jugador) {
    this.jugador = jugador;
  }

  public String viajar(Ubicacion nuevaUbicacion) {
    jugador.setUbicacionActual(nuevaUbicacion);
    explorarUbicacion();
    String resultado = "Has viajado a " + jugador.getUbicacionActual().getNombre();
    return resultado;

  }

  public String verMapa(Mapa mapa){
    return mapa.mostrarMapa();
  }

  public String explorarUbicacion() {
    Ubicacion ubicacionActual = jugador.getUbicacionActual();
    StringBuilder resultado = new StringBuilder();
    resultado.append("Estás en: ").append(ubicacionActual.getNombre()).append("\n");
    resultado.append(ubicacionActual.getDescripcion()).append("\n");

    Enemigo enemigo = ubicacionActual.getEnemigoActual();
    if (enemigo == null) {
        resultado.append("- No hay enemigos en esta ubicación.\n");
    } else {
        resultado.append("- Enemigo: ").append(enemigo.getNombre())
                 .append(" | Vida: ").append(enemigo.getVida()).append("\n");
    }
    String itemsEnUbicacion = ubicacionActual.getInventario().listarObjetos();
    if (!itemsEnUbicacion.isEmpty()) {
        resultado.append("- Items en esta ubicación:\n");
        resultado.append(itemsEnUbicacion);
    } else {
        resultado.append("- No hay items para recoger aquí.\n");
    }
    return resultado.toString();
  }
}

