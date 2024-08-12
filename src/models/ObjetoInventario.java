package models;

public interface ObjetoInventario {
  String getNombre();
  int getCantidad();
  void setCantidad(int cantidad);
  boolean esConsumible();
  int getPeso();
  EfectoItem getEfecto();
  void aumentarCantidad(int cantidad);
  void disminuirCantidad(int cantidad);
}
