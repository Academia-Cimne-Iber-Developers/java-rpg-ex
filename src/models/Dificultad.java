package models;

public enum Dificultad {
    FACIL(0.8),
    NORMAL(1.0),
    DIFICIL(1.4);

    private final double multiplicador;

    Dificultad(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public double getMultiplicador() {
        return multiplicador;
    }
}