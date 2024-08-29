package managers;

import models.AccionAtaque;
import models.AccionBloqueo;
import models.AccionCarga;
import models.AccionCombate;
import models.Enemigo;
import models.Jugador;
import models.Ubicacion;
import ui.Interfaz;


public class GestorCombate {
  private Jugador jugador;
  private Interfaz interfaz;
  private int porcentajeCritico;

  public GestorCombate(Jugador jugador,Interfaz interfaz){
    this.jugador = jugador;
    this.interfaz = interfaz;
    this.porcentajeCritico = 0;
  }

  public void pelear(Enemigo enemigo){
    reiniciarPorcentajeC();

    while (jugador.estaVivo() && enemigo.estaVivo()){
        AccionCombate accion = elegirAccion();
        if (accion != null){
          accion.ejecutar(jugador, enemigo, this);
          if (enemigo.estaVivo()) {
            interfaz.mostrarMensaje(enemigo.atacar(jugador));
          }
          interfaz.mostrarMensaje("Vida jugador: " + jugador.getVida() + " | Vida enemigo: " + enemigo.getVida());
        }else {
          interfaz.mostrarMensaje("No se pudo realizar la accion. Eliga una opcion valida");
        }
    }
    if (jugador.estaVivo()){
      interfaz.mostrarMensaje("Derrotaste a: " + enemigo.getNombre());
    } else {
      interfaz.mostrarMensaje("Moriste, fin del juego.");
    }
  }

  public Enemigo getEnemigoEnUbicacionActual() {
    // Implementación para buscar enemigo en la ubicación actual
    Ubicacion ubicacionActual = jugador.getUbicacionActual();
    return ubicacionActual.getEnemigoActual(); // Devuelve el enemigo encontrado
  }

  public void reiniciarPorcentajeC() {
    this.porcentajeCritico = 0;
  }
   public int getPorcentajeC(){
    return porcentajeCritico;  //Para obtener el porcentaje critico
   }

   public void aumentarPorcentajeC(int incremento){
    this.porcentajeCritico = Math.min(80, this.porcentajeCritico + incremento );
   }
  

  public AccionCombate elegirAccion(){
    interfaz.opcionesCombate();
    String opcion = interfaz.obtenerEntrada();
    switch (opcion) {
      case "1":
        return new AccionAtaque();        
      case "2":
        return new AccionBloqueo();
      case "3":
        return  new AccionCarga();
      case "4":
        return  new AccionCuracion();
    
      default:
        interfaz.mostrarMensaje("Opcion invalida");
        return null ;    
    }
  }
    }

