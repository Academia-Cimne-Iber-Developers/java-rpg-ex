package models;

public class Jugador extends Entidad {
  private InventarioJugador inventario;
  private Ubicacion ubicacionActual;
  private int capacidadMax = 100;
  private boolean bloquear;

  public Jugador(String nombre, Ubicacion ubicacionInicial) {
    super(nombre, 100, 15);
    this.inventario = new InventarioJugador(capacidadMax);
    this.ubicacionActual = ubicacionInicial;
  }

  @Override
  public void recibirDanio(int danio) {
      this.vida -= danio;
      if (this.vida < 0) this.vida = 0;

      if (bloquear) {
        this.vida += danio / 2;
        bloquear = false;
      }
  }

  @Override
  public String atacar(Entidad objetivo) {
    String resultado = "";
    objetivo.recibirDanio(this.ataque);
    resultado += (this.nombre + " ataca a " + objetivo.getNombre() + " causando " + this.ataque + " puntos de daño.");
    return resultado;
}

  public void curarse(int cantidadCuracion, String nombre) {
    
    if (inventario.tieneObjeto("Pocion", 1)) {
      System.out.println("Hay pociones en el inventario.");
      System.out.println("Hay pociones en el inventario."+ inventario.listarObjetos());
    } else {
      System.out.println("No hay pociones en el inventario.");
    }

      // }
    // this.vida = Math.min(100, getVida() + cantidadCuracion);
  }

  public void usarPocion(){
    if (inventario.tieneObjeto("Pocion", 1)) {
      System.out.println("Hay pociones en el inventario.");
      System.out.println("Hay pociones en el inventario."+ inventario.listarObjetos());
    } else {
      System.out.println("No hay pociones en el inventario.");
    }
  }

  public String mostrarInventario() {
    return inventario.listarObjetos();
  }

  public Ubicacion getUbicacionActual() {
    return ubicacionActual;
  }

  public InventarioJugador getInventario() {
    return inventario;
  }

  public void setInventario(InventarioJugador inventario) {
    this.inventario = inventario;
  }

  public void setUbicacionActual(Ubicacion ubicacionActual) {
    this.ubicacionActual = ubicacionActual;
  }

  public int getCapacidadMax() {
    return capacidadMax;
  }

  public void setCapacidadMax(int capacidadMax) {
    this.capacidadMax = capacidadMax;
  }

  public void setBloquear(boolean bool) {
    this.bloquear = bool;
  }
}