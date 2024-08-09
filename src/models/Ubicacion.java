package models;

import managers.GestorItems;

public class Ubicacion {
    private String nombre;
    private String descripcion;
    private GestorItems gestorItems;
    private Enemigo enemigoActual;

    public Ubicacion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.gestorItems = new GestorItems(null);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean hayEnemigo(){
        return enemigoActual != null;
    }

    public void eliminarEnemigo(){
        this.enemigoActual = null;
    }

    public void agregarItem(String nombre, int cantidad, int peso) {
        gestorItems.agregarItem(nombre, cantidad, peso);
    }

    public Item obtenerItem(String nombre) {
        return gestorItems.obtenerItem(nombre);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Enemigo getEnemigoActual() {
        return enemigoActual;
    }

    public void setEnemigoActual(Enemigo enemigoActual) {
        this.enemigoActual = enemigoActual;
    }

    

    @Override
    public String toString() {
        return nombre;
    }

    public GestorItems getGestorItems() {
        return gestorItems;
    }
}