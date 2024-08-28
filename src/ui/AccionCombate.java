package ui;

import managers.GestorCombate;
import models.Enemigo;
import models.Jugador;

public interface AccionCombate {
    void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate);

    
}

