package models;

import java.util.List;

public class Ubicacion {
    private String nombre;
    private String descripcion;
    private Enemigo enemigoActual;
    private InventarioUbicacion inventario;

    // Nuevos atributos para el sistema de viaje mejorado
    private List<Ubicacion> ubicacionesAdyacentes;
    private boolean esPuntoViajeRapido;
    private boolean puntoViajeRapidoDesbloqueado;

    public Ubicacion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.inventario = new InventarioUbicacion();
        this.esPuntoViajeRapido = false; // Por defecto, no es un punto de viaje rápido
        this.puntoViajeRapidoDesbloqueado = false;
    }

    // Métodos anteriores
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

    public InventarioUbicacion getInventario() {
        return inventario;
    }

    // Nuevos métodos para el sistema de viaje mejorado
    public List<Ubicacion> getUbicacionesAdyacentes() {
        return ubicacionesAdyacentes;
    }

    public void setUbicacionesAdyacentes(List<Ubicacion> ubicacionesAdyacentes) {
        this.ubicacionesAdyacentes = ubicacionesAdyacentes;
    }

    public boolean esPuntoViajeRapido() {
        return esPuntoViajeRapido;
    }

    public void setEsPuntoViajeRapido(boolean esPuntoViajeRapido) {
        this.esPuntoViajeRapido = esPuntoViajeRapido;
    }

    public boolean estaPuntoViajeRapidoDesbloqueado() {
        return puntoViajeRapidoDesbloqueado;
    }

    public void desbloquearPuntoViajeRapido() {
        if (esPuntoViajeRapido) {
            this.puntoViajeRapidoDesbloqueado = true;
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}
