package models;

import ui.Juego;

public interface Mision {
    boolean estaCompleta();
    String getDescripcion();
    void actualizar(Juego juego);
}

