package models;
import ui.Juego;
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

    public abstract void actualizar(Juego juego);
}
