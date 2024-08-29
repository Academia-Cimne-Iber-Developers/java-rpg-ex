package managers;

import models.AccionCombate;
import models.Enemigo;
import models.Jugador;
import models.Ubicacion;
import ui.Interfaz;

public class GestorCombate {
    private Jugador jugador;
    private Interfaz interfaz;
    private int porcentajeCritico; // Atributo para el porcentaje de crítico

    public GestorCombate(Jugador jugador, Interfaz interfaz) {
        this.jugador = jugador;
        this.interfaz = interfaz;
        this.porcentajeCritico = 0; // Inicializa el porcentaje de crítico
    }

    public void pelear(Enemigo enemigo) {
        while (jugador.estaVivo() && enemigo.estaVivo()) {
            interfaz.mostrarMensaje("Elige una acción: [A] Atacar, [B] Bloquear, [C] Cargar, [H] Curarse");
            String accion = interfaz.obtenerEntrada();

            switch (accion) {
                case "a":
                    ejecutarAccion(new AccionAtaque());
                    break;
                case "b":
                    ejecutarAccion(new AccionBloqueo());
                    break;
                case "c":
                    ejecutarAccion(new AccionCarga());
                    break;
                case "h":
                    ejecutarAccion(new AccionCuracion());
                    break;
                default:
                    interfaz.mostrarMensaje("Acción no reconocida.");
                    continue;
            }

            if (enemigo.estaVivo()) {
                interfaz.mostrarMensaje(enemigo.atacar(jugador));
            }

            interfaz.mostrarMensaje("Vida jugador: " + jugador.getVida() + " | Vida enemigo: " + enemigo.getVida());
        }

        if (jugador.estaVivo()) {
            interfaz.mostrarMensaje("Derrotaste a: " + enemigo.getNombre());
        } else {
            interfaz.mostrarMensaje("Moriste, fin del juego.");
        }
    }

    private void ejecutarAccion(AccionCombate accion) {
        accion.ejecutar(jugador, getEnemigoEnUbicacionActual(), this);
    }

    public void resetearPorcentajeCritico() {
        this.porcentajeCritico = 0;
    }

    public int getPorcentajeCritico() {
        return porcentajeCritico;
    }

    public void aumentarPorcentajeCritico(int aumento) {
        this.porcentajeCritico = Math.min(80, this.porcentajeCritico + aumento);
    }

    public Enemigo getEnemigoEnUbicacionActual() {
        Ubicacion ubicacionActual = jugador.getUbicacionActual();
        return ubicacionActual.getEnemigoActual();
    }

    public Interfaz getInterfaz() {
        return interfaz;
    }
}
