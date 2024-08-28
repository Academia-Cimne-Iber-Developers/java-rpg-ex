package managers;

import models.Jugador;
import models.Ubicacion;
import models.Mapa;
import java.util.ArrayList;
import java.util.List;

public class GestorExploracion {
    private Jugador jugador;
    private GestorMisiones gestorMisiones;

    public GestorExploracion(Jugador jugador, GestorMisiones gestorMisiones) {
        this.jugador = jugador;
        this.gestorMisiones = gestorMisiones;
    }

    public String viajar(Ubicacion nuevaUbicacion) {
        if (puedeViajarA(nuevaUbicacion)) {
            jugador.setUbicacionActual(nuevaUbicacion);

            // Desbloquea el punto de viaje rápido si aplica
            if (nuevaUbicacion.esPuntoViajeRapido()) {
                nuevaUbicacion.desbloquearPuntoViajeRapido();
            }

            return "Has viajado a " + jugador.getUbicacionActual().getNombre();
        } else {
            return "No puedes viajar a esa ubicación. No está adyacente ni es un punto de viaje rápido desbloqueado.";
        }
    }

    public boolean puedeViajarA(Ubicacion destino) {
        Ubicacion ubicacionActual = jugador.getUbicacionActual();
        
        
        if (ubicacionActual.getUbicacionesAdyacentes().contains(destino)) {
            return true;
        }
        
        
        return destino.estaPuntoViajeRapidoDesbloqueado();
    }

    public List<Ubicacion> getOpcionesViajeDisponibles(Mapa mapa) {
        Ubicacion ubicacionActual = jugador.getUbicacionActual();
        List<Ubicacion> opciones = new ArrayList<>(ubicacionActual.getUbicacionesAdyacentes());

        opciones.addAll(mapa.getPuntosViajeRapidoDesbloqueados());

        return opciones;
    }

    public String verMapa(Mapa mapa) {
        return mapa.mostrarMapa();
    }
    
    // Nuevo método para explorar una ubicación
    public Ubicacion explorarUbicacion() {
        Ubicacion ubicacionActual = jugador.getUbicacionActual();
        
        return ubicacionActual;
    }
}
