package managers;

import models.Enemigo;
import models.Jugador;
import models.ResultadoUsoItem;
import models.InventarioJugador;
import models.Item;
import managers.GestorInventario;


public class AccionCuracion implements AccionCombate {
    private GestorInventario gestorInventario = new GestorInventario();
    
    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo, GestorCombate gestorCombate) {
        
        InventarioJugador inventario = jugador.getInventario();
        gestorCombate.getInterfaz().mostrarInventario();
        //Previene la entrada vacia
        String itemInput = "";
        do {
            itemInput = gestorCombate.getInterfaz().pedirEntrada("Escribi el item que queres usar... ");
        } while (itemInput.isEmpty());

        ResultadoUsoItem resultado = gestorInventario.usarObjeto(jugador, inventario, itemInput);
        gestorCombate.getInterfaz().mostrarMensaje(resultado.getMensaje());
    }
}