package models;

public class AccionCuracion implements AccionCombate {
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        if (jugador.getInventario().tienePociones()) {
            jugador.curarse(50, "Poción");
            jugador.getInventario().usarPocion();
            gestorCombate.getInterfaz().mostrarMensaje("Has usado una poción y recuperado 50 puntos de vida.");
        } else {
            gestorCombate.getInterfaz().mostrarMensaje("No tienes pociones en tu inventario.");
        }
    }
}