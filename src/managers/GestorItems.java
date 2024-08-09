package managers;

import java.util.HashMap;
import java.util.Map;

import models.Item;
import models.Jugador;

public class GestorItems {
    private Map<String, Item> items;
    private Jugador jugador;

    public GestorItems(Jugador jugador){
      this.items = new HashMap<>();
      this.jugador = jugador;
    }

    public void recogerItem(String nombreItem) {
      Item itemTomado = jugador.getUbicacionActual().obtenerItem(nombreItem);
      if (itemTomado == null){
        System.out.println("El item no existe en la ubicación");
      } else {
        int cantidad = itemTomado.getCantidad();
        String nombre = itemTomado.getNombre();
        int peso = itemTomado.getPeso();
        if (!jugador.getInventario().hayEspacio(peso)) {
            System.out.println("No hay espacio disponible para cargar el item");
            jugador.getUbicacionActual().agregarItem(nombre, cantidad, peso);
            return;
        }

        System.out.println("Has recogido " + cantidad + " " + nombre + "(s).");
        jugador.getInventario().agregarItem(nombre, cantidad, peso);
      }
  }

  public Item obtenerItem(String nombre){
    return items.remove(nombre);
  }

  public void removerItem(String nombre, int cantidad) {
    if (items.containsKey(nombre)) {
        Item item = items.get(nombre);
        int cantidadActual = item.getCantidad();
        if (cantidadActual > cantidad) {
            item.setCantidad(cantidadActual - cantidad);
            int carga = cantidad * item.getPeso();
            int cargaActual = jugador.getInventario().getCargaActual();
            jugador.getInventario().SetCargaActual(carga + cargaActual);
            
        } else if (cantidadActual == cantidad) {
            items.remove(nombre);
            int carga = cantidad * item.getPeso();
            int cargaActual = jugador.getInventario().getCargaActual();
            jugador.getInventario().SetCargaActual(carga + cargaActual);
        } else {
            System.out.println("No tenés suficientes " + nombre + "(s) en tu inventario");
            return;
        }
        System.out.println(cantidad + " " + nombre + "(s) ha(n) sido removido(s) del inventario");
    } else {
        System.out.println("Ese item no existe en tu inventario");
    }
  }

  public int agregarItem(String nombre, int cantidad, int peso) {
    Item existente = items.get(nombre);
    
    if (existente != null){
        int cantExiste = existente.getCantidad();
        Item itemActualizado = new Item(nombre, cantExiste + cantidad, peso);
        items.put(nombre, itemActualizado);
    } else {
        items.put(nombre, new Item(nombre, cantidad, peso));
    }
    int pesoAgregado = cantidad * peso;
    return pesoAgregado;
  }
  
  public boolean tieneItem(String nombre){
    return items.containsKey(nombre);
  }

  public String listarItems(){
    String resultado = "";
    for (String item : items.keySet()) {
      resultado += "\n" + item + ": " + items.get(item).getCantidad() + " (Peso unitario: " + items.get(item).getPeso() + ")";
    }
    return resultado;
  }

  public boolean estaVacio(){
    return items.isEmpty();
  }

  public Map<String, Item> getItems() {
    return items;
  } 
}
