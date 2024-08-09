package managers;

import models.Item;

public class TransferenciaItem {
    public boolean transferir(Inventario origen, Inventario destino, String nombreItem, int cantidad) {
        if (!origen.tieneItem(nombreItem, cantidad)) {
            System.out.println("El origen no tiene suficientes " + nombreItem);
            return false;
        }

        Item item = origen.removerItem(nombreItem, cantidad);
        if (item != null && destino.agregarItem(item)) {
            System.out.println("Transferencia exitosa de " + cantidad + " " + nombreItem);
            return true;
        } else if (item != null) {
            origen.agregarItem(item); // Devolver el item si no se pudo agregar al destino
            System.out.println("No se pudo agregar el item al destino");
        }
        return false;
    }
}