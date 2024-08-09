package managers;

import models.Item;

public class GestorInventario {
  private TransferenciaItem transferenciaItem;

  public GestorInventario() {
      this.transferenciaItem = new TransferenciaItem();
  }

  public boolean moverItem(Inventario origen, Inventario destino, String nombreItem, int cantidad) {
    return transferenciaItem.transferir(origen, destino, nombreItem, cantidad);
  }

  public String mostrarInventario(Inventario inventario) {
    return inventario.listaItems();
  }

  public boolean usarItem(Inventario inventario, String nombreItem){
    if (inventario.tieneItem(nombreItem, 1)){
      Item item = inventario.removerItem(nombreItem, 1);
      System.out.println("Estoy usando: " + item.getNombre());
      return true;
    }
    return false;
  }
  
}
