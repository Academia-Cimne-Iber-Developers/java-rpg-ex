package models;

public abstract class MisionBase implements Mision {
    protected String descripcion;
    protected boolean completada;

    public MisionBase(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }

    public boolean estaCompleta() {
        return completada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    protected void completar() {
        this.completada = true;
    }

    // Ahora recibe información abstracta en lugar de un objeto Juego
    public abstract void actualizar(String ubicacionActual);
}
