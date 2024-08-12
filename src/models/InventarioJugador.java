package models;

import java.util.Map;
import java.util.HashMap;

import managers.Inventario;

public class InventarioJugador implements Inventario {

  private Map<String, ObjetoInventario> objetos;
  private int capacidadMax;
  private int cargaActual;

  public InventarioJugador(int capacidadMax){
    this.objetos = new HashMap<>();
    this.capacidadMax = capacidadMax;
    this.cargaActual = 0;
  }

  @Override
  public boolean agregarObjeto(ObjetoInventario objeto) {
      if (espacioDisponible() >= objeto.getPeso() * objeto.getCantidad()) {
          if (objetos.containsKey(objeto.getNombre())) {
              ObjetoInventario objetoExistente = objetos.get(objeto.getNombre());
              objetoExistente.setCantidad(objetoExistente.getCantidad() + objeto.getCantidad());
          } else {
              objetos.put(objeto.getNombre(), objeto);
          }
          cargaActual += objeto.getPeso() * objeto.getCantidad();
          return true;
      }
      return false;
  }

  @Override
  public ObjetoInventario removerObjeto(String nombre, int cantidad) {
      if (objetos.containsKey(nombre)) {
          ObjetoInventario objetoExistente = objetos.get(nombre);
          if (objetoExistente.getCantidad() > cantidad) {
              ObjetoInventario objetoRemovido = new Item(
                  objetoExistente.getNombre(),
                  cantidad,
                  objetoExistente.getPeso(),
                  objetoExistente.getEfecto(),
                  objetoExistente.esConsumible()
              );
              objetoExistente.setCantidad(objetoExistente.getCantidad() - cantidad);
              cargaActual -= objetoRemovido.getPeso() * cantidad;
              return objetoRemovido;
          } else if (objetoExistente.getCantidad() == cantidad) {
              cargaActual -= objetoExistente.getPeso() * objetoExistente.getCantidad();
              return objetos.remove(nombre);
          }
      }
      return null;
  }

  @Override
  public boolean tieneObjeto(String nombre, int cantidad) {
      return objetos.containsKey(nombre) && objetos.get(nombre).getCantidad() >= cantidad;
  }

  @Override
  public String listarObjetos() {
      StringBuilder sb = new StringBuilder("Inventario del jugador:\n");
      if (objetos.isEmpty()) {
          sb.append("---El inventario está vacío.---\n");
          return sb.toString();
      }
      for (ObjetoInventario objeto : objetos.values()) {
          sb.append(objeto.getNombre()).append(": ").append(objeto.getCantidad())
            .append(objeto.esConsumible() ? " (Consumible)" : "").append("\n");
      }
      sb.append("Espacio disponible: ").append(espacioDisponible()).append("/").append(capacidadMax);
      return sb.toString();
  }

  @Override
  public int espacioDisponible() {
    return capacidadMax - cargaActual;
  }
  
}
