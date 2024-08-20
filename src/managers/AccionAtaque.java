package managers;


import models.Enemigo;
import models.Jugador;
// import ui.Interfaz;


public class AccionAtaque implements AccionCombate {
//   private Interfaz interfaz;}

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        gestorCombate.getInterfaz().mostrarMensaje("Batalla contra " + enemigo.getNombre() + "| Vida: " + enemigo.getVida() + " Ataque: " + enemigo.getAtaque());
        int danio = jugador.getAtaque();
        if (Math.random() * 10 < gestorCombate.getPorcentajeCritico()) {
            danio *= 1.5;
            gestorCombate.resetearPorcentajeCritico();
        }
        enemigo.recibirDanio(danio);
        gestorCombate.getInterfaz().mostrarMensaje("Has atacado a " + enemigo.getNombre() + " el enemigo causando " + danio + " de daño.");
        // if (enemigo.estaVivo()) {
        //     gestorCombate.getInterfaz().mostrarMensaje("El enemigo se prepara para atacar");
        //     gestorCombate.getInterfaz().mostrarMensaje(enemigo.atacar(jugador));
        //   }
    }
}