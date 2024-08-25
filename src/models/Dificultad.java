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

  public String getNombreFormateado() {
    return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
  }

  public String getClave() {
    return name().substring(0, 1).toUpperCase();
  }

  public static Dificultad fromString(String texto) {
    for (Dificultad dificultad : Dificultad.values()) {
      if (dificultad.getClave().equalsIgnoreCase(texto)) {
        return dificultad;
      }
    }
    return null;
  }
}
