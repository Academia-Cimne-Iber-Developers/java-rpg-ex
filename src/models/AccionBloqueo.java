package models;

public class AccionBloqueo implements AccionCombate {
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        jugador.setBloqueando(true);
        gestorCombate.getInterfaz().mostrarMensaje("Estás bloqueando el próximo ataque.");
    }
}