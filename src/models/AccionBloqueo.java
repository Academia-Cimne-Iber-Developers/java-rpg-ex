package models;

import managers.GestorCombate;

public class AccionBloqueo implements AccionCombate {

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        jugador.setBloqueando(true); // Activa el bloqueo
        GestorCombate.getInterfaz().mostrarMensaje("Estás bloqueando el próximo ataque.");
    }
}
