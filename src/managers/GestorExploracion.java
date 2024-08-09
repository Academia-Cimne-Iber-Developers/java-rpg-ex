package managers;

import java.util.Map;

import models.Enemigo;
import models.Item;
import models.Jugador;
import models.Ubicacion;
import ui.Interfaz;

public class GestorExploracion {
  private Jugador jugador;
  private Interfaz interfaz;

  public GestorExploracion(Jugador jugador, Interfaz interfaz) {
    this.jugador = jugador;
    this.interfaz = interfaz;
  }

  public void viajar(Ubicacion nuevaUbicacion) {
    jugador.setUbicacionActual(nuevaUbicacion);
    interfaz.mostrarMensaje("Has viajado a " + jugador.getUbicacionActual().getNombre());
    explorarUbicacion();
  }

  public String explorarUbicacion() {
      String resultado = "Estás en: " + jugador.getUbicacionActual().getNombre() + "\n" + jugador.getUbicacionActual().getDescripcion();


      Enemigo enemigo = jugador.getUbicacionActual().getEnemigoActual();

      if (enemigo == null){
        resultado += "\n- No hay enemigos en esta ubicación.";
      } else {
        resultado += "\n- Enemigo: " + enemigo.getNombre() + " | Vida: " + enemigo.getVida();
      }
        
      Map<String, Item> itemsEnUbicacion = jugador.getUbicacionActual().getGestorItems().getItems();
      if (!itemsEnUbicacion.isEmpty()) {
          resultado += "\n- Items en esta ubicación:";
          for (Item item : itemsEnUbicacion.values()) {
              resultado += "\n" + item.getNombre() + ": " + item.getCantidad();
          }
      } else {
          resultado += "\n- No hay items para recoger aquí.";
      }
      return resultado;
  }
}
