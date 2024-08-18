package models;

public class MisionAtesorar extends MisionBase {
    private String TesoroRequired;
    private int numRequired;
    private int numObtenido;

    public MisionAtesorar(String descripcion, String tesoroRequired, int numRequired) {
        super(descripcion, false, "m_atesorar");
        this.TesoroRequired = tesoroRequired;
        this.numRequired = numRequired;
        this.numObtenido = 0;
    }

    @Override
    public boolean estaCompleta() {
        return this.completada;
    }
    @Override
    public void actualizar(String tipo, String data) {
        System.out.println("Actualizando......");
        System.out.println(tipo);
        System.out.println(data);
        if(tipo == "m_atesorar"){
            if(data == this.TesoroRequired){
                this.numObtenido++;
                if(this.numObtenido >= this.numRequired){
                    this.completada = true;
                }
            }
           }
    }
    @Override
    public String getEstadoMision() {
        StringBuilder estado = new StringBuilder("Estado de Progeso:\n");
        estado.append("Numero de Tesoro Requerido: ").append(this.numRequired).append("\n");
        estado.append("Numero de Tesoro Obtenido: ").append(this.numObtenido).append("\n");
        return estado.toString();
    }


    public String getTesoroRequired() {
        return TesoroRequired;
    }

    public void setTesoroRequired(String tesoroRequired) {
        TesoroRequired = tesoroRequired;
    }

    public int getNumRequired() {
        return numRequired;
    }

    public void setNumRequired(int numRequired) {
        this.numRequired = numRequired;
    }

    public int getNumObtenido() {
        return numObtenido;
    }

    public void setNumObtenido(int numObtenido) {
        this.numObtenido = numObtenido;
    }

    
    
}
