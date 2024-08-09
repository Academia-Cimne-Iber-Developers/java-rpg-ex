package managers;

import models.Item;

public interface Inventario {
  boolean agregarItem(Item item);
  Item removerItem(String nombre, int cantidad);
  boolean tieneItem(String nombre, int cantidad);
  String listaItems();
  int espacioDisponible();
}
