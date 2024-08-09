package models;

import managers.GestorItems;

public class Inventario {
    private GestorItems gestorItems;
    private int capacidadMax;
    private int cargaActual;

    public Inventario(int capacidadMax, Jugador jugador) {
        this.gestorItems = new GestorItems(jugador);
        this.capacidadMax = capacidadMax;
        this.cargaActual = 0;
    }

    public void SetCargaActual(int peso){
        this.cargaActual = peso;
    }

    public boolean hayEspacio(int peso){
        return this.capacidadMax - (this.cargaActual + peso) >= 0;
    }

    public void agregarItem(String nombre, int cantidad, int peso){
        gestorItems.agregarItem(nombre, cantidad, peso);
    }

    public void removerItem(String nombre, int cantidad){
        gestorItems.removerItem(nombre, cantidad);
    }

    public boolean tieneItem(String nombre){
        return gestorItems.tieneItem(nombre);
    }

    public String mostrarInventario() {
        String itemsInventario = "";
        if (gestorItems.estaVacio()) {
            itemsInventario = "El inventario está vacío";
        } else {
            itemsInventario = "Inventario actual:";
            itemsInventario = gestorItems.listarItems();
            
            itemsInventario += "\nCarga actual: " + cargaActual + "/" + capacidadMax;
        }
        return itemsInventario;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public int getCargaActual() {
        return cargaActual;
    }

}