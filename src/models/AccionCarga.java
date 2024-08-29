package models;

import managers.GestorCombate;

public class AccionCarga implements AccionCombate {

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        gestorCombate.aumentarPorcentajeC(15); 
        GestorCombate.getInterfaz().mostrarMensaje("Estás cargando, el porcentaje crítico ha aumentado a " + gestorCombate.getPorcentajeC() + "%.");
    }
}
