
package models;

// import models.Ubicacion;
import java.util.ArrayList;
import java.util.List;

public class FeaturesMap {

    private List<Ubicacion> puntosViajeRapido = new ArrayList<>();
       
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

    
}
