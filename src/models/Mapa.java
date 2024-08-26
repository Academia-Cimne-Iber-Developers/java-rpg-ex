package models;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private Map<String, Ubicacion> ubicaciones;
    Private List<Ubicación> puntoViajeRapido = new ArrayList<>();
    public Mapa() {
        ubicaciones = new HashMap<>();
        inicializarMapa();
    }
        private void inicializarMapa() {
        agregarUbicacion("Pueblo Inicio", "Un pequeño pueblo donde comienza tu aventura.");
        agregarUbicacion("Bosque Oscuro", "Un denso bosque lleno de criaturas misteriosas.");
        agregarUbicacion("Montaña Nevada", "Una imponente montaña cubierta de nieve.");
        agregarUbicacion("Cueva Profunda", "Una cueva oscura que esconde secretos antiguos.");

        Item pocionCuracion = new Item("Pocion", 2, 10, new EfectoCuracion(20), true); // Consumible
        agregarItemAUbicacion("Pueblo Inicio", pocionCuracion);

        Item espada = new Item("Espada de madera", 1, 15, null, false); // No consumible
        agregarItemAUbicacion("Bosque Oscuro", espada);

        Item pociones = new Item("Pocion", 3, 10, new EfectoCuracion(20), true); // Consumible
        agregarItemAUbicacion("Bosque Oscuro", pociones);

        Item botasNieve = new Item("Botas de nieve", 1, 5, null, false); // No consumible
        agregarItemAUbicacion("Montaña Nevada", botasNieve);

        Item gemaMagica = new Item("Gema magica", 1, 5, new EfectoCuracion(50), true); // Consumible
        agregarItemAUbicacion("Cueva Profunda", gemaMagica);

        Enemigo lobo = new Enemigo("Lobo", 20, 40);
        ubicaciones.get("Bosque Oscuro").setEnemigoActual(lobo);
    }

    public void agregarUbicacion(String nombre, String descripcion) {
        Ubicacion ubicacionNueva = new Ubicacion(nombre, descripcion);
        ubicaciones.put(nombre, ubicacionNueva);
    }

    private void agregarItemAUbicacion(String nombreUbicacion, Item item) {
        Ubicacion ubicacion = ubicaciones.get(nombreUbicacion);
        if (ubicacion != null) {
            ubicacion.getInventario().agregarObjeto(item);
        }
    }

    public void desbloquearPuntoViajeRapido(Ubicacion ubicacion) {
        if (ubicacion.esPuntoViajeRapido() && !puntosViajeRapido.contains(ubicacion)) 
        {
            ubicacion.desbloquearPuntoViajeRapido();
            puntosViajeRapido.add(ubicacion);
        }
    }
    
    public List<Ubicacion> getPuntosViajeRapidoDesbloqueados(){
        return puntosViajeRapido;

    }
    public Ubicacion getUbicacion(String nombre) {
        return ubicaciones.get(nombre);
    }

    public String mostrarMapa() {
        StringBuilder mapa = new StringBuilder();
        mapa.append("Mapa del mundo:\n");
        for (String ubicacion : ubicaciones.keySet()) {
            mapa.append("- ")
                    .append(ubicacion)
                    .append("\n");
        }
        return mapa.toString();
    }
}