package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa {
    private Map<String, Ubicacion> ubicaciones;
    private List<Ubicacion> puntosViajeRapido;

    public Mapa() {
        ubicaciones = new HashMap<>();
        puntosViajeRapido = new ArrayList<>();
        inicializarMapa();
    }

    private void inicializarMapa() {

        agregarUbicacion("Pueblo Inicio", "Un pequeño pueblo donde comienza tu aventura.");
        agregarUbicacion("Bosque Oscuro", "Un denso bosque lleno de criaturas misteriosas.");
        agregarUbicacion("Montaña Nevada", "Una imponente montaña cubierta de nieve.");
        agregarUbicacion("Cueva Profunda", "Una cueva oscura que esconde secretos antiguos.");


        configurarUbicacionesAdyacentes();

        // Marcado de puntos de viaje rápido
        marcarPuntoViajeRapido("Pueblo Inicio");
        marcarPuntoViajeRapido("Montaña Nevada");


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

    private void configurarUbicacionesAdyacentes() {
        // Definir ubicaciones adyacentes
        Ubicacion puebloInicio = ubicaciones.get("Pueblo Inicio");
        Ubicacion bosqueOscuro = ubicaciones.get("Bosque Oscuro");
        Ubicacion montanaNevada = ubicaciones.get("Montaña Nevada");
        Ubicacion cuevaProfunda = ubicaciones.get("Cueva Profunda");

        puebloInicio.setUbicacionesAdyacentes(List.of(bosqueOscuro));
        bosqueOscuro.setUbicacionesAdyacentes(List.of(puebloInicio, montanaNevada));
        montanaNevada.setUbicacionesAdyacentes(List.of(bosqueOscuro, cuevaProfunda));
        cuevaProfunda.setUbicacionesAdyacentes(List.of(montanaNevada));
    }

    private void marcarPuntoViajeRapido(String nombreUbicacion) {
        Ubicacion ubicacion = ubicaciones.get(nombreUbicacion);
        if (ubicacion != null) {
            ubicacion.setEsPuntoViajeRapido(true);
            puntosViajeRapido.add(ubicacion);
        }
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

    public Ubicacion getUbicacion(String nombre) {
        return ubicaciones.get(nombre);
    }

    public List<Ubicacion> getPuntosViajeRapidoDesbloqueados() {
        return puntosViajeRapido.stream()
                .filter(Ubicacion::estaPuntoViajeRapidoDesbloqueado)
                .toList();
    }

    public void desbloquearPuntoViajeRapido(Ubicacion ubicacion) {
        if (ubicacion.esPuntoViajeRapido()) {
            ubicacion.desbloquearPuntoViajeRapido();
        }
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
