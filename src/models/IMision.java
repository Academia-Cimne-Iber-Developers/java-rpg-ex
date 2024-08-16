package models;


public interface IMision {
    boolean estaCompleta();
    String getDescripcion();
    void actualizar(Juego juego);
}