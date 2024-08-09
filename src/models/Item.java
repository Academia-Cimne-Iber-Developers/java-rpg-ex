package models;

public class Item {
  private String nombre;
  private int cantidad;
  private int peso;

  public Item(String nombre, int cantidad, int peso) {
      this.nombre = nombre;
      this.cantidad = cantidad;
      this.peso = peso;
  }

  public String getNombre() {
      return nombre;
  }

  public int getCantidad() {
      return cantidad;
  }

  public void aumentarCantidad(int cantidad) {
      this.cantidad += cantidad;
  }

  public void disminuirCantidad(int cantidad) {
      this.cantidad -= cantidad;
  }

  public void setCantidad(int cantidad) {
      this.cantidad = cantidad;
  }

  public int getPeso() {
    return peso;
  }
}