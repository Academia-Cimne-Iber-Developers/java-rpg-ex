package managers;

import java.util.ArrayList;
import java.util.List;
import models.Jugador;
import models.Mision;
import models.MisionCombate;
import models.MisionExploracion;

public class GestorMisiones {
    private List<Mision> misionesActivas;
    private List<Mision> misionesCompletadas;
    private Jugador jugador;

    public GestorMisiones(Jugador jugador) {
        this.misionesActivas = new ArrayList<>();
        this.misionesCompletadas = new ArrayList<>();
        this.jugador = jugador;
    }

    public void agregarMision(Mision mision) {
        misionesActivas.add(mision);
    }

    public void actualizarMisiones(String ubicacionActual, String enemigoDerrotado){
        List<Mision> misionesParaMover = new ArrayList<>();
        for (Mision mision : misionesActivas) {
            if (mision instanceof MisionCombate) {
                ((MisionCombate) mision).actualizar(enemigoDerrotado);
            } else if (mision instanceof MisionExploracion) {
                ((MisionExploracion) mision).actualizar(ubicacionActual);
            }
            if (mision.estaCompleta()) {
                misionesParaMover.add(mision);
            }
        }
        misionesActivas.removeAll(misionesParaMover);
        misionesCompletadas.addAll(misionesParaMover);
    }

    public List<Mision> getMisionesActivas() {
        return misionesActivas;
    }

    public List<Mision> getMisionesCompletadas() {
        return misionesCompletadas;
    }
}
