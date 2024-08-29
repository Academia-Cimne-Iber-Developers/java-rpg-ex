package models;

import managers.GestorCombate;

public interface AccionCombate {
    void ejecutar (Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate);
}
