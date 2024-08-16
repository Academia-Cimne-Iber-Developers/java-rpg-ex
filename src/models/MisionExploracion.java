package models;

public class MisionExploracion extends MisionBase {
    private String ubicacionObjetivo;
    private int vecesRequeridas;
    private int vecesVisitada;
    // Constructor y método actualizar
    public MisionExploracion(String descripcion, boolean completada, String ubicacionObjetivo, int vecesRequeridas,
            int vecesVisitada) {
        super(descripcion, completada);
        this.ubicacionObjetivo = ubicacionObjetivo;
        this.vecesRequeridas = vecesRequeridas;
        this.vecesVisitada = vecesVisitada;
    }
    public String getUbicacionObjetivo() {
        return ubicacionObjetivo;
    }
    public void setUbicacionObjetivo(String ubicacionObjetivo) {
        this.ubicacionObjetivo = ubicacionObjetivo;
    }
    public int getVecesRequeridas() {
        return vecesRequeridas;
    }
    public void setVecesRequeridas(int vecesRequeridas) {
        this.vecesRequeridas = vecesRequeridas;
    }
    public int getVecesVisitada() {
        return vecesVisitada;
    }
    public void setVecesVisitada(int vecesVisitada) {
        this.vecesVisitada = vecesVisitada;
    }
    
}
