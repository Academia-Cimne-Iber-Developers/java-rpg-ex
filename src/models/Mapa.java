package models;

import java.util.HashMap;
import java.util.Map;

public class Mapa {
    private Map<String, Ubicacion> ubicaciones;

    public Mapa() {
        ubicaciones = new HashMap<>();
        inicializarMapa();
    }

    private void inicializarMapa() {
        agregarUbicacion("Pueblo Inicio", "Un pequeño pueblo donde comienza tu aventura.");
        agregarUbicacion("Bosque Oscuro", "Un denso bosque lleno de criaturas misteriosas.");
        agregarUbicacion("Montaña Nevada", "Una imponente montaña cubierta de nieve.");
        agregarUbicacion("Cueva Profunda", "Una cueva oscura que esconde secretos antiguos.");

        ubicaciones.get("Pueblo Inicio").agregarItem("Pocion", 2, 10);
        ubicaciones.get("Bosque Oscuro").agregarItem("Espada de madera", 1, 5);
        ubicaciones.get("Bosque Oscuro").agregarItem("Pocion", 3, 1);
        ubicaciones.get("Montaña Nevada").agregarItem("Botas de nieve", 1, 3);
        ubicaciones.get("Cueva Profunda").agregarItem("Gema magica", 1, 1);

        Enemigo lobo = new Enemigo("Lobo", 20, 5);
        ubicaciones.get("Bosque Oscuro").setEnemigoActual(lobo);
    }

    public void agregarUbicacion(String nombre, String descripcion) {
        Ubicacion ubicacionNueva = new Ubicacion(nombre, descripcion);
        ubicaciones.put(nombre, ubicacionNueva);
    }

    public Ubicacion getUbicacion(String nombre) {
        return ubicaciones.get(nombre);
    }

    public void mostrarMapa() {
        System.out.println("Mapa del mundo:");
        for (String ubicacion : ubicaciones.keySet()) {
            System.out.println("- " + ubicacion);
        }
    }
}