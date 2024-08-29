package models;

import managers.GestorCombate;

public class AccionAtaque implements AccionCombate{
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        int danio = jugador.getAtaque();
        if (Math.random() * 100 < gestorCombate.getPorcentajeC()) {
            danio *= 1.5;
            gestorCombate.reiniciarPorcentajeC();
        }
        enemigo.recibirDanio(danio);
        interfaz.mostrarMensaje("Has atacado al enemigo causando " + danio + " de daño.");
    }
}
