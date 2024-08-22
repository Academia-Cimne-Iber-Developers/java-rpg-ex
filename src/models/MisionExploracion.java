package models;

public class MisionExploracion extends MisionBase {
    private String ubicacionObjetivo;
    private int vecesRequeridas;
    private int vecesVisitada;
    
    // Constructor y método actualizar
    public MisionExploracion(String descripcion, String ubicacionObjetivo, int vecesRequeridas
            ) {
        super(descripcion, false, "m_exploracion");
        this.ubicacionObjetivo = ubicacionObjetivo;
        this.vecesRequeridas = vecesRequeridas;
        this.vecesVisitada = 0;

    }

    public String getTipoMision() {
        return super.getTipoMision();
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
    @Override
    public boolean estaCompleta() {
        //return this.vecesVisitada >= this.vecesRequeridas;
        return this.completada;
    }
    @Override
    public void actualizar(String tipo, String data) {
       if("m_exploracion".equals(tipo)){
        if(data.equals(this.ubicacionObjetivo)){
            this.vecesVisitada++;
            if(this.vecesVisitada >= this.vecesRequeridas){
                this.completada = true;
            }
        }
       }
    }

    @Override
    public String getEstadoMision() {
        StringBuilder estado = new StringBuilder("Estado de Progeso:\n");
        estado.append("Numero de Visitas Requeridas: ").append(this.vecesRequeridas).append("\n");
        estado.append("Numero de Visitas Realizadas: ").append(this.vecesVisitada).append("\n");
        return estado.toString();
    }
    
}
