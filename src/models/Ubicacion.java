package models;
import java.util.List;


public class Ubicacion {
    private String nombre;
    private String descripcion;
    private Enemigo enemigoActual;
    private InventarioUbicacion inventario;
    private List<Ubicacion> ubicacionesAdyacentes;
    private boolean esPuntoViajeRapido;
    private boolean puntoViajeRapidoDesbloqueado;

    public Ubicacion(String nombre, String descripcion, List<Ubicacion> ubicacionesAdyacentes, boolean esPuntoViajeRapido) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.inventario = new InventarioUbicacion();
        this.ubicacionesAdyacentes = ubicacionesAdyacentes;
        this.esPuntoViajeRapido = esPuntoViajeRapido;
        this.puntoViajeRapidoDesbloqueado = false;

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

    public List<Ubicacion> getUbicacionesAdyacentes(){
        return ubicacionesAdyacentes;
    }

    public boolean esPuntoViajeRapido(){
        return esPuntoViajeRapido;
    }
    public boolean estaPuntoViajeRapidoDesbloqueado(){
        return puntoViajeRapidoDesbloqueado;
    }
    public void desbloquearPuntoViajeRapido(){
        if(esPuntoViajeRapido){
            this.puntoViajeRapidoDesbloqueado = true;
        }
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