package models;

import ui.Juego;

public class MisionExploracion extends MisionBase {
    private String ubicacionObjetivo;
    private int vecesRequeridas;
    private int vecesVisitada;

    public MisionExploracion(String descripcion, String ubicacionObjetivo, int vecesRequeridas) {
        super(descripcion);
        this.ubicacionObjetivo = ubicacionObjetivo;
        this.vecesRequeridas = vecesRequeridas;
        this.vecesVisitada = 0;
    }

    public void verificarYActualizar(Juego juego) {
        if (juego.getJugador().getUbicacionActual().getNombre().equals(ubicacionObjetivo)) {
            vecesVisitada++;
            System.out.println("Ubicación objetivo alcanzada: " + ubicacionObjetivo);  // Depuración
            if (vecesVisitada >= vecesRequeridas) {
                completar();  // Marca la misión como completada
                System.out.println("Misión completada: " + getDescripcion());  // Depuración
            }
        } else {
            System.out.println("Ubicación actual: " + juego.getJugador().getUbicacionActual().getNombre());
            System.out.println("Ubicación objetivo: " + ubicacionObjetivo);
        }
    }

    @Override
    public void actualizar(Juego juego) {
        verificarYActualizar(juego);
    }
}
