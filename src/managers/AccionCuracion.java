package managers;

import models.Enemigo;
import models.Jugador;
import models.InventarioJugador;
import models.Item;

public class AccionCuracion implements AccionCombate {
    private Item item;
    
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        
        InventarioJugador inventario = jugador.getInventario();
        gestorCombate.getInterfaz().mostrarInventario();
        String itemInput = gestorCombate.getInterfaz().pedirEntrada("Escribi el item que queres usar... ");
        
        if(inventario.tieneObjeto(itemInput, 1)) { 
            
            this.item.usar(jugador, itemInput);
            
            gestorCombate.getInterfaz().mostrarMensaje("Uso la pocion");
            // EfectoCuracion efecto;
            // efecto.aplicarEfecto(jugador, itemInput);

            gestorCombate.getInterfaz().mostrarMensaje("Has usado una " + itemInput +" para curarte.. Vida actual: " + jugador.getVida());
            inventario.removerObjeto(itemInput, 1);
        
        } else {
            gestorCombate.getInterfaz().mostrarMensaje("No tienes pociones en el inventario.");
        }
    }
}