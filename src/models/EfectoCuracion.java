package models;

public class EfectoCuracion implements EfectoItem {

  private int cantidadCuracion;

  public EfectoCuracion(int cantidadCuracion){
    this.cantidadCuracion = cantidadCuracion;
  }

  @Override
  public void aplicarEfecto(Jugador jugador, String nombre) {
    // jugador.curarse(cantidadCuracion, nombre);
  }
  
}
