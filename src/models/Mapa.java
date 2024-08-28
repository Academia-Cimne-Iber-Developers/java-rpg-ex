package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Mapa {
    private Map<String, Ubicacion> ubicaciones;
    private List<Ubicacion> puntosViajeRapido;

    public Mapa() {
        ubicaciones = new HashMap<>();
        puntosViajeRapido = new ArrayList<>();
        inicializarMapa();
    }

    private void inicializarMapa() {
        // Inicialización de ubicaciones
        agregarUbicacion("Pueblo Inicio", "Un pequeño pueblo donde comienza tu aventura.");
        agregarUbicacion("Bosque Oscuro", "Un denso bosque lleno de criaturas misteriosas.");
        agregarUbicacion("Montaña Nevada", "Una imponente montaña cubierta de nieve.");
        agregarUbicacion("Cueva Profunda", "Una cueva oscura que esconde secretos antiguos.");
        agregarUbicacion("Llanura Ventosa", "Una extensa llanura con fuertes vientos.");
        agregarUbicacion("Castillo Abandonado", "Un antiguo castillo en ruinas lleno de misterios.");

        // Establecer puntos de viaje rápido
        Ubicacion puebloInicio = getUbicacion("Pueblo Inicio");
        puebloInicio.setEsPuntoViajeRapido(true);
        desbloquearPuntoViajeRapido(puebloInicio); 

        Ubicacion castilloAbandonado = getUbicacion("Castillo Abandonado");
        castilloAbandonado.setEsPuntoViajeRapido(true);

        // Establecer ubicaciones adyacentes
        Ubicacion bosqueOscuro = getUbicacion("Bosque Oscuro");
        puebloInicio.agregarUbicacionAdyacente(bosqueOscuro);
        bosqueOscuro.agregarUbicacionAdyacente(puebloInicio);

        Ubicacion montañaNevada = getUbicacion("Montaña Nevada");
        bosqueOscuro.agregarUbicacionAdyacente(montañaNevada);
        montañaNevada.agregarUbicacionAdyacente(bosqueOscuro);

        Ubicacion cuevaProfunda = getUbicacion("Cueva Profunda");
        montañaNevada.agregarUbicacionAdyacente(cuevaProfunda);
        cuevaProfunda.agregarUbicacionAdyacente(montañaNevada);

        Ubicacion llanuraVentosa = getUbicacion("Llanura Ventosa");
        cuevaProfunda.agregarUbicacionAdyacente(llanuraVentosa);
        llanuraVentosa.agregarUbicacionAdyacente(cuevaProfunda);

        llanuraVentosa.agregarUbicacionAdyacente(castilloAbandonado);
        castilloAbandonado.agregarUbicacionAdyacente(llanuraVentosa);

        // Agregar enemigos e ítems a las ubicaciones
        Item pocionCuracion = new Item("Poción", 2, 10, new EfectoCuracion(20), true); // Consumible
        agregarItemAUbicacion("Pueblo Inicio", pocionCuracion);

        Item espada = new Item("Espada de madera", 1, 15, null, false); // No consumible
        agregarItemAUbicacion("Bosque Oscuro", espada);

        Item pociones = new Item("Poción", 3, 10, new EfectoCuracion(20), true); // Consumible
        agregarItemAUbicacion("Bosque Oscuro", pociones);

        Item botasNieve = new Item("Botas de nieve", 1, 5, null, false); // No consumible
        agregarItemAUbicacion("Montaña Nevada", botasNieve);

        Item gemaMagica = new Item("Gema mágica", 1, 5, new EfectoCuracion(50), true); // Consumible
        agregarItemAUbicacion("Cueva Profunda", gemaMagica);

        Item escudoAntiguo = new Item("Escudo Antiguo", 1, 20, null, false); // No consumible
        agregarItemAUbicacion("Castillo Abandonado", escudoAntiguo);

        Enemigo lobo = new Enemigo("Lobo", 20, 40);
        bosqueOscuro.setEnemigoActual(lobo);

        Enemigo dragon = new Enemigo("Dragón", 100, 200);
        castilloAbandonado.setEnemigoActual(dragon);
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

    // Métodos para manejar puntos de viaje rápido
    public List<Ubicacion> getPuntosViajeRapidoDesbloqueados() {
        List<Ubicacion> puntosDesbloqueados = new ArrayList<>();
        for (Ubicacion punto : puntosViajeRapido) {
            if (punto.estaPuntoViajeRapidoDesbloqueado()) {
                puntosDesbloqueados.add(punto);
            }
        }
        return puntosDesbloqueados;
    }

    public void desbloquearPuntoViajeRapido(Ubicacion ubicacion) {
        if (ubicacion.esPuntoViajeRapido() && !puntosViajeRapido.contains(ubicacion)) {
            ubicacion.desbloquearPuntoViajeRapido();
            puntosViajeRapido.add(ubicacion);
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
