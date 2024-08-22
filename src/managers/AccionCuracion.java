package managers;

import models.Enemigo;
import models.Jugador;
import models.InventarioJugador;

public class AccionCuracion implements AccionCombate {
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        InventarioJugador inventario = jugador.getInventario();
        if(inventario.tieneObjeto("Pocion", 1)) { 
            //Suponemos que la pocion cura 15 puntos
            jugador.curarse(15);
            gestorCombate.getInterfaz().mostrarMensaje("Has usado una poción para curarte. + 15 de Vida: " + jugador.getVida());
            inventario.removerObjeto("Pocion", 1);
        } else {
            gestorCombate.getInterfaz().mostrarMensaje("No tienes pociones en el inventario.");
        }
    }
}