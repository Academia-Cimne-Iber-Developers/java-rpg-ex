package models;

import java.util.Map;
import java.util.HashMap;

import managers.Inventario;

public class InventarioUbicacion implements Inventario {

  private Map<String, Item> items;

  public InventarioUbicacion() {
    this.items = new HashMap<>();
  }

  @Override
  public boolean agregarItem(Item item) {
    if (items.containsKey(item.getNombre())) {
      items.get(item.getNombre()).aumentarCantidad(item.getCantidad());
    } else {
      items.put(item.getNombre(), item);
    }
    return true;
  }

  @Override
  public Item removerItem(String nombre, int cantidad) {
    if (items.containsKey(nombre)) {
        Item item = items.get(nombre);
        if (item.getCantidad() >= cantidad) {
            item.disminuirCantidad(cantidad);
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
    StringBuilder sb = new StringBuilder("\n");
      for (Item item : items.values()) {
          sb.append(item.getNombre()).append(": ").append(item.getCantidad()).append("\n");
      }
      return sb.toString();
  }

  @Override
  public int espacioDisponible() {
    return Integer.MAX_VALUE;
  }
  
}
