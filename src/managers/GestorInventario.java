package managers;

import models.Item;
import models.Jugador;
import models.ObjetoInventario;

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

  public String usarObjeto(Jugador jugador, Inventario inventario, String nombreObjeto){
    StringBuilder resultado = new StringBuilder("");
    if (inventario.tieneObjeto(nombreObjeto, 1)){
      ObjetoInventario objeto = inventario.removerObjeto(nombreObjeto, 1);
      if (objeto.esConsumible()){
        ((Item)objeto).usar(jugador, objeto.getNombre());
        resultado.append("Usando ").append(nombreObjeto);
        return resultado.toString();
      } else {
        inventario.agregarObjeto(objeto);
        resultado.append(nombreObjeto).append(" no es consumible.");
        return resultado.toString();
      }
    }
    resultado.append("No posees ").append(nombreObjeto);
    return resultado.toString();
  }
  
}
