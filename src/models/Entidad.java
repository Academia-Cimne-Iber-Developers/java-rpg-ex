package models;

public abstract class Entidad {
  protected String nombre;
  protected int vida;
  protected int ataque;

  public Entidad(String nombre, int vida, int ataque) {
      this.nombre = nombre;
      this.vida = vida;
      this.ataque = ataque;
  }

  public String getNombre() {
      return nombre;
  }

  public void setNombre(String nombre) {
      this.nombre = nombre;
  }

  public int getVida() {
      return vida;
  }

  public void setVida(int vida) {
      this.vida = vida;
  }

  public int getAtaque() {
      return ataque;
  }

  public void setAtaque(int ataque) {
      this.ataque = ataque;
  }

  public abstract void recibirDanio(int danio);
  public abstract String atacar(Entidad objetivo);

  public boolean estaVivo() {
      return this.vida > 0;
  }
}