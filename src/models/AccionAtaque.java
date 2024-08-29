package models;

public class AccionAtaque implements AccionCombate {
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        int danio = jugador.getAtaque();
        if (Math.random() * 100 < gestorCombate.getPorcentajeCritico()) {
            danio *= 1.5;
            gestorCombate.resetearPorcentajeCritico();
        }
        enemigo.recibirDanio(danio);
        gestorCombate.getInterfaz().mostrarMensaje("Has atacado al enemigo causando " + danio + " de daño.");
    }
}
