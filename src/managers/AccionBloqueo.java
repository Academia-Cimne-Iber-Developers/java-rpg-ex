package managers;

import models.Enemigo;
import models.Jugador;

public class AccionBloqueo implements AccionCombate {
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        jugador.setBloquear(true);
        gestorCombate.getInterfaz().mostrarMensaje("Te pusiste en posición de bloqueo. Recibiras la mitad del daño de tus enemigos");
        // if (enemigo.estaVivo()) {
        //     gestorCombate.getInterfaz().mostrarMensaje("El enemigo se prepara para atacar");
        //     gestorCombate.getInterfaz().mostrarMensaje(enemigo.atacar(jugador));
        //   }
        // gestorCombate.getInterfaz().mostrarMensaje("Como lo bloqueaste recibiste la mitad de su daño. Vida: " + jugador.getVida());
    }
}