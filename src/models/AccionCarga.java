package models;

public class AccionCarga implements AccionCombate {
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        gestorCombate.aumentarPorcentajeCritico(15);
        gestorCombate.getInterfaz().mostrarMensaje("Te has cargado, aumentando el porcentaje crítico.");
    }
}