package managers;

import models.ObjetoInventario;

public class TransferenciaItem {
    public boolean transferir(Inventario origen, Inventario destino, String nombreObjeto, int cantidad) {
        ObjetoInventario objeto = origen.removerObjeto(nombreObjeto, cantidad);
        
        if (objeto == null) {
            System.out.println("El origen no tiene suficientes " + nombreObjeto);
            return false;
        }
        
        if (destino.agregarObjeto(objeto)) {
            System.out.println("Transferencia exitosa de " + cantidad + " " + nombreObjeto);
            return true;
        } else {
            origen.agregarObjeto(objeto);
            System.out.println("No se pudo agregar el objeto al destino");
            return false;
        }
    }
}