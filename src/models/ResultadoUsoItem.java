package models;

public class ResultadoUsoItem {
    private boolean exito;
    private String mensaje;
    private Item itemUsado;

    // Constructor
    public ResultadoUsoItem(boolean exito, String mensaje, Item itemUsado) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.itemUsado = itemUsado;
    }

    // Getters
    public boolean isExito() {
        return exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Item getItemUsado() {
        return itemUsado;
    }

    // Setters
    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setItemUsado(Item itemUsado) {
        this.itemUsado = itemUsado;
    }
}
