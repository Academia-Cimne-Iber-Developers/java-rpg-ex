package models;

public interface Mision {
    boolean estaCompleta();
    String getDescripcion();
    void actualizar(String ubicacionActual); // Por ejemplo, se pasa la ubicación actual del jugador
}
