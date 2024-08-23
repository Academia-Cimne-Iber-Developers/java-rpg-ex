package managers;

import models.Enemigo;
import models.Jugador;
import models.InventarioJugador;
import models.Item;
import managers.GestorInventario;


public class AccionCuracion implements AccionCombate {
    private GestorInventario gestorInventario = new GestorInventario();
    
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        
        InventarioJugador inventario = jugador.getInventario();
        gestorCombate.getInterfaz().mostrarInventario();
        String itemInput = gestorCombate.getInterfaz().pedirEntrada("Escribi el item que queres usar... ");
        if (itemInput != "") {
            gestorInventario.usarObjeto(jugador, inventario, itemInput);
            
            gestorCombate.getInterfaz().mostrarMensaje("Consumiste 1 de " + itemInput + " y te curaste vida. " + "Vida actual: " + jugador.getVida());
        } else{
            gestorCombate.getInterfaz().mostrarMensaje("No se encontro el item en tu inventario. Perdiste el turno.");
        }
    }
}