package models;

public class Enemigo extends Entidad {

  public Enemigo(String nombre, int vida, int ataque) {
    super(nombre, vida, ataque);
  }


  public void luchar(int ataque){
    this.vida -= ataque;
    if (this.vida < 0) this.vida = 0;
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
}
