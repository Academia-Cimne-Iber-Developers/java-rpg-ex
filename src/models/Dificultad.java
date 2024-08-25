package models;

public enum Dificultad implements NivelDificultad {
    FACIL(0.8) {
        @Override
        public String getDescription() {
            return "Facil: Los enemigos son débiles.";
        }
    },
    NORMAL(1.0) {
        @Override
        public String getDescription() {
            return "Normal: Nivel de dificultad estándar.";
        }
    },
    DIFICIL(1.4) {
        @Override
        public String getDescription() {
            return "Dificil: Los enemigos son más fuertes.";
        }
    };

    private final double multiplicador;

    Dificultad(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    @Override
    public double getMultiplicador() {
        return multiplicador;
    }
}
