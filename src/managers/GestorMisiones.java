package managers;

import java.util.ArrayList;
import java.util.List;
import models.IMision;

public class GestorMisiones {
    private List<IMision> misionesActivas;
    private List<IMision> misionesCompletadas;
    // Métodos para agregar, actualizar y obtener misiones

    public GestorMisiones() {
        this.misionesActivas = new ArrayList<>();
        this.misionesCompletadas = new ArrayList<>();
    }

    //Metodo para agregar una mision
    public void agregarMision(IMision mision) {
        this.misionesActivas.add(mision);
    }

    //Metodo para actualizar una mision
    public void actualizarMisiones(String tipo, String data) {
        List<IMision> misionesParaEliminar = new ArrayList<>();

        for (IMision mision : this.misionesActivas) {
            mision.actualizar(tipo, data);
            if(mision.estaCompleta()){
                this.misionesCompletadas.add(mision);
                misionesParaEliminar.add(mision);
            }
        }

        this.misionesActivas.removeAll(misionesParaEliminar);
    }

    public String listarMisionesActivas() {
        StringBuilder sb = new StringBuilder("Lista de Misiones Activas:\n");
      if (this.misionesActivas.isEmpty()) {
          sb.append("---No tiene misiones activas.---\n");
          return sb.toString();
      }
      for (IMision misionActiva : this.misionesActivas) {
          sb.append(misionActiva.getDescripcion()).append("\n");
          sb.append(misionActiva.getEstadoMision()).append("\n");
          sb.append("\n");
      }
      
      return sb.toString();
    }

    public String listarMisionesCompletadas() {
        StringBuilder sb = new StringBuilder("Lista de Misiones Completadas:\n");
      if (this.misionesCompletadas.isEmpty()) {
          sb.append("---No tiene misiones completadas.---\n");
          return sb.toString();
      }
      for (IMision misionCompletada : this.misionesCompletadas) {
          sb.append(misionCompletada.getDescripcion()).append("\n");
        //   sb.append(misionCompletada.getEstadoMision()).append("\n");
          sb.append("\n");
      }
      
      return sb.toString();
    }




}//fin de clase
