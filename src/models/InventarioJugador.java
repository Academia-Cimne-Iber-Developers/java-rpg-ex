package models;

import java.util.Map;
import java.util.HashMap;

import managers.Inventario;

public class InventarioJugador implements Inventario {

  private Map<String, Item> items;
  private int capacidadMax;
  private int cargaActual;

  public InventarioJugador(int capacidadMax){
    this.items = new HashMap<>();
    this.capacidadMax = capacidadMax;
    this.cargaActual = 0;
  }

  @Override
  public boolean agregarItem(Item item) {
    if (espacioDisponible() >= item.getPeso() * item.getCantidad()) {
      if (items.containsKey(item.getNombre())) {
          items.get(item.getNombre()).aumentarCantidad(item.getCantidad());
      } else {
          items.put(item.getNombre(), item);
      }
      cargaActual += item.getPeso() * item.getCantidad();
      return true;
  }
  return false;
  }

  @Override
  public Item removerItem(String nombre, int cantidad) {
    if (items.containsKey(nombre)) {
      Item item = items.get(nombre);
      if (item.getCantidad() >= cantidad) {
          item.disminuirCantidad(cantidad);
          cargaActual -= item.getPeso() * cantidad;
          if (item.getCantidad() == 0) {
              items.remove(nombre);
          }
          return new Item(item.getNombre(), cantidad, item.getPeso());
      }
    }
    return null;
  }

  @Override
  public boolean tieneItem(String nombre, int cantidad) {
    return items.containsKey(nombre) && items.get(nombre).getCantidad() >= cantidad;
  }

  @Override
  public String listaItems() {
    StringBuilder sb = new StringBuilder("Inventario del jugador:\n");
    for (Item item : items.values()) {
        sb.append(item.getNombre()).append(": ").append(item.getCantidad()).append("\n");
    }
    sb.append("Espacio disponible: ").append(espacioDisponible()).append("/").append(capacidadMax);
    return sb.toString();
  }

  @Override
  public int espacioDisponible() {
    return capacidadMax - cargaActual;
  }
  
}
