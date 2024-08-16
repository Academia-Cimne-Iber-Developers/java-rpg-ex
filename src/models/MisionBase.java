public abstract class MisionBase implements IMision {
    protected String descripcion;
    protected boolean completada;
    public MisionBase(String descripcion, boolean completada) {
        this.descripcion = descripcion;
        this.completada = completada;
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
    
}