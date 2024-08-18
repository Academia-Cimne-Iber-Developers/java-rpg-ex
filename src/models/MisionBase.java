package models;



public abstract class MisionBase implements IMision {
    protected String descripcion;
    protected boolean completada;
    protected String tipoMision;
    
    public MisionBase(String descripcion, boolean completada, String tipoMision) {
        this.descripcion = descripcion;
        this.completada = completada;
        this.tipoMision = tipoMision;
    }

    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public boolean isCompletada() {
        return completada;
    }
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }


    public String getTipoMision() {
        return tipoMision;
    }


    public void setTipoMision(String tipoMision) {
        this.tipoMision = tipoMision;
    }
    
}