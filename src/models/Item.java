package models;

public class Item implements ObjetoInventario {
  private String nombre;
  private int cantidad;
  private int peso;
  private EfectoItem efecto;
  private boolean esConsumible;

  public Item(String nombre, int cantidad, int peso, EfectoItem efecto, boolean esConsumible) {
      this.nombre = nombre;
      this.cantidad = cantidad;
      this.peso = peso;
      this.efecto = efecto;
      this.esConsumible = esConsumible;
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

  public void usar(Jugador jugador, String nombre){
    if (esConsumible && efecto != null){
      efecto.aplicarEfecto(jugador, nombre);
    }
  }

  @Override
  public boolean esConsumible() {
      return esConsumible;
  }

  @Override
  public EfectoItem getEfecto() {
      return efecto;
  }
}