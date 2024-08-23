package models;


public interface IMision {
    boolean estaCompleta();
    String getDescripcion();    
    void actualizar(String tipo, String data);
    String getEstadoMision();
}