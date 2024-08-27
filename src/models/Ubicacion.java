package models;


public class Ubicacion {
    private String nombre;
    private String descripcion;
    private Enemigo enemigoActual;
    private InventarioUbicacion inventario;

    public Ubicacion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.inventario = new InventarioUbicacion();
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

    public InventarioUbicacion getInventario() {
        return inventario;
    }
}

