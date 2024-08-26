package models;

public class ConfiguracionJuego {
    private static ConfiguracionJuego instancia;
    private Dificultad dificultadActual;

    private ConfiguracionJuego() {
        dificultadActual = Dificultad.NORMAL; // Dificultad por defecto
    }

    public static ConfiguracionJuego getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionJuego();
        }
        return instancia;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultadActual = dificultad;
    }

    public Dificultad getDificultadActual() {
        return dificultadActual;
    }
}