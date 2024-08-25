package configurations;

import models.Dificultad;

public class ConfiguracionJuego {
    private ConfiguracionJuego() {
        dificultadActual = Dificultad.NORMAL;
    }

    private static class SingletonHolder {
        private static final ConfiguracionJuego INSTANCIA = new ConfiguracionJuego();        
    }

    public static ConfiguracionJuego getInstancia() {
        return SingletonHolder.INSTANCIA;
    }

    private Dificultad dificultadActual;
    public void setDificultad(Dificultad dificultad) {
        this.dificultadActual = dificultad;
    }

    public Dificultad getDificultad() {
        return dificultadActual;
    }
}
