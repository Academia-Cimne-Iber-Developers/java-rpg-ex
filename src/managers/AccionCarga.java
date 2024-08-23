package managers;

import models.Enemigo;
import models.Jugador;

public class AccionCarga implements AccionCombate {
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        gestorCombate.aumentarPorcentajeCritico(15);
        gestorCombate.getInterfaz().mostrarMensaje("Has cargado tu ataque, aumentando la probabilidad de crítico.");
    }
}