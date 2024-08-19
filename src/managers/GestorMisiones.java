package managers;

import models.Jugador;
import models.Mision;

import java.util.ArrayList;
import java.util.List;

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

    public void actualizarMisiones(String ubicacionActual) {
        List<Mision> misionesParaMover = new ArrayList<>();

        for (Mision mision : misionesActivas) {
            mision.actualizar(ubicacionActual);  // Se pasa la ubicación actual
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
