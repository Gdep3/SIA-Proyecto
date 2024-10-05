package clases;

public class Empleado extends Usuario{
    private int codigoGerente;
    
    public Empleado(String nombre, String rut, int codigoGerente){
        super(nombre, rut);
        this.codigoGerente = codigoGerente;
    }
    
    @Override
    public String datosAString(){
        return getNombre() + ", " + getRut() + ", " + codigoGerente;
    }

    public void setCodigoGerente(int codigo){
        codigoGerente = codigo;
    }
    public int getCodigoGerente(){
        return codigoGerente;
    }
}
