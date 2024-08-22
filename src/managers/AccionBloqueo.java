package managers;

import models.Enemigo;
import models.Jugador;

public class AccionBloqueo implements AccionCombate {
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        jugador.setBloquear(true);
        gestorCombate.getInterfaz().mostrarMensaje("Te pusiste en posición de bloqueo. Recibiras la mitad del daño de tus enemigos");
    }
}