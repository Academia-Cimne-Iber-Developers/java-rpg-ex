package managers;

import models.Item;
import models.Jugador;
import models.ObjetoInventario;
import models.ResultadoUsoItem;

public class GestorInventario {
    private TransferenciaItem transferenciaItem;

    public GestorInventario() {
        this.transferenciaItem = new TransferenciaItem();
    }

    public boolean moverObjeto(Inventario origen, Inventario destino, String nombreItem, int cantidad) {
        return transferenciaItem.transferir(origen, destino, nombreItem, cantidad);
    }

    public String mostrarInventario(Inventario inventario) {
        return inventario.listarObjetos();
    }

    public ResultadoUsoItem usarObjeto(Jugador jugador, Inventario inventario, String nombreObjeto) {
        if (inventario.tieneObjeto(nombreObjeto, 1)) {
            ObjetoInventario objeto = inventario.removerObjeto(nombreObjeto, 1);
            if (objeto.esConsumible()) {
                ((Item)objeto).usar(jugador, objeto.getNombre());
                return new ResultadoUsoItem(true, "Usando " + nombreObjeto, (Item)objeto);
            } else {
                inventario.agregarObjeto(objeto);
                return new ResultadoUsoItem(false, nombreObjeto + " no es consumible.", (Item)objeto);
            }
        }
        return new ResultadoUsoItem(false, "No posees " + nombreObjeto, null);
    }

}
