package models;

public class MisionCombate extends MisionBase {
    private String enemigoObjetivo;
    private int cantidadRequerida;
    private int cantidadDerrotada;

    public MisionCombate(String descripcion, String enemigoObjetivo, int cantidadRequerida) {
        super(descripcion);
        this.enemigoObjetivo = enemigoObjetivo;
        this.cantidadRequerida = cantidadRequerida;
        this.cantidadDerrotada = 0;
    }

    public void actualizar(String enemigoDerrotado) {
        if (enemigoDerrotado.trim().equalsIgnoreCase(enemigoObjetivo.trim())) {
            cantidadDerrotada++;
            if (cantidadDerrotada >= cantidadRequerida) {
                completar();
            }
        } else {
            System.out.println("No coinciden.");
        }
    }

    public int getCantidadDerrotada() {
        return cantidadDerrotada;
    }

    public int getCantidadRequerida() {
        return cantidadRequerida;
    }

    public boolean esCompletada() {
        return estaCompleta();
    }
}
