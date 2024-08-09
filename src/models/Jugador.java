package models;

public class Jugador extends Entidad {
  private Inventario inventario;
  private Ubicacion ubicacionActual;
  private int capacidadMax = 100;

  public Jugador(String nombre, Ubicacion ubicacionInicial) {
    super(nombre, 100, 15);
    this.inventario = new Inventario(capacidadMax, this);
    this.ubicacionActual = ubicacionInicial;
  }

  @Override
  public void recibirDanio(int danio) {
      this.vida -= danio;
      if (this.vida < 0) this.vida = 0;
  }

  @Override
  public String atacar(Entidad objetivo) {
    String resultado = "";
    objetivo.recibirDanio(this.ataque);
    resultado += (this.nombre + " ataca a " + objetivo.getNombre() + " causando " + this.ataque + " puntos de daño.");
    return resultado;
}

  public void curarse() {
    if (inventario.tieneItem("Pocion")) {
        this.vida += 20;
        if (this.vida > 100) this.vida = 100;
        inventario.removerItem("Pocion", 1);
        System.out.println("Usaste una poción, tu salud es: " + this.vida);
    } else {
        System.out.println("No te quedan pociones para curarte.");
    }
}

  public String mostrarInventario() {
      return inventario.mostrarInventario();
  }

  public Ubicacion getUbicacionActual() {
    return ubicacionActual;
  }

  public Inventario getInventario() {
    return inventario;
  }

  public void setInventario(Inventario inventario) {
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
}