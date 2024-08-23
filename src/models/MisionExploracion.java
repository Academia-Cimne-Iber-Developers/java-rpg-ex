package models;

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

    @Override
    public void actualizar(String ubicacionActual) {
        verificarYActualizar(ubicacionActual);
    }

    // Método para verificar y actualizar el estado de la misión
    public void verificarYActualizar(String ubicacionActual) {
        if (ubicacionActual.equals(ubicacionObjetivo)) {
            vecesVisitada++;
            if (vecesVisitada >= vecesRequeridas) {
                completar();
            }
        }
    }

    // Método para verificar si la misión está completada
    public boolean esCompletada() {
        return estaCompleta();
    }

    // Método para obtener cuántas veces es necesario visitar la ubicación
    public int getVecesRequeridas() {
        return vecesRequeridas;
    }

    // Método para obtener cuántas veces se ha visitado la ubicación
    public int getVecesVisitadas() {
        return vecesVisitada;
    }

    // Métodos adicionales, getters, etc.
    public String getUbicacionObjetivo() {
        return ubicacionObjetivo;
    }

    public String getVecesVisitada() {
        return vecesVisitada + "/" + vecesRequeridas;
    }

}
