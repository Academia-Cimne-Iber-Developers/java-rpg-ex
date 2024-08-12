package managers;

import models.ObjetoInventario;

public interface Inventario {
  boolean agregarObjeto(ObjetoInventario objeto);
  ObjetoInventario removerObjeto(String nombre, int cantidad);
  boolean tieneObjeto(String nombre, int cantidad);
  String listarObjetos();
  int espacioDisponible();
}
