package models;

import java.util.Map;
import java.util.HashMap;

import managers.Inventario;

public class InventarioUbicacion implements Inventario {

  private Map<String, ObjetoInventario> objetos;

  public InventarioUbicacion() {
    this.objetos = new HashMap<>();
  }

  @Override
  public boolean agregarObjeto(ObjetoInventario objeto) {
      if (objetos.containsKey(objeto.getNombre())) {
          ObjetoInventario objetoExistente = objetos.get(objeto.getNombre());
          objetoExistente.setCantidad(objetoExistente.getCantidad() + objeto.getCantidad());
      } else {
          objetos.put(objeto.getNombre(), objeto);
      }
      return true;
  }

  @Override
  public ObjetoInventario removerObjeto(String nombre, int cantidad) {
      if (objetos.containsKey(nombre)) {
          ObjetoInventario itemExistente = objetos.get(nombre);
          if (itemExistente.getCantidad() > cantidad) {
              Item itemExtraido = new Item(
                  itemExistente.getNombre(),
                  cantidad,
                  itemExistente.getPeso(),
                  itemExistente.getEfecto(),
                  itemExistente.esConsumible()
              );
              itemExistente.disminuirCantidad(cantidad);
              return itemExtraido;
          } else if (itemExistente.getCantidad() == cantidad) {
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
      StringBuilder sb = new StringBuilder("");
      for (ObjetoInventario objeto : objetos.values()) {
          sb.append(objeto.getNombre()).append(": ").append(objeto.getCantidad())
            .append(objeto.esConsumible() ? " (Consumible)" : "").append("\n");
      }
      return sb.toString();
  }

  @Override
  public int espacioDisponible() {
    return Integer.MAX_VALUE;
  }
  
}
