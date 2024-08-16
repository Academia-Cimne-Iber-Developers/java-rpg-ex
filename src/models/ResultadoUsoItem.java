package models;

public class ResultadoUsoItem {
    private boolean exito;
    private String mensaje;
    private Item itemUsado;

    public ResultadoUsoItem(boolean exito, String mensaje, Item itemUsado) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.itemUsado = itemUsado;
    }

    public boolean isExito() {
        return exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Item getItemUsado() {
        return itemUsado;
    }

    @Override
    public String toString() {
        return "ResultadoUsoItem{" +
                "exito=" + exito +
                ", mensaje='" + mensaje + '\'' +
                ", itemUsado=" + itemUsado +
                '}';
    }
}
