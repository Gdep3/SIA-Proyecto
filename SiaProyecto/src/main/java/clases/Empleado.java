package clases;

public class Empleado extends Usuario{
    private int codigoGerente;
    
    public Empleado(String nombre, String rut, int codigoGerente){
        super(nombre, rut);
        this.codigoGerente = codigoGerente;
    }
    
    @Override
    public void mostrarMenu(){
        System.out.println("Mostrando menu gerente");
    }
    
    public void setCodigoGerente(int codigo){
        codigoGerente = codigo;
    }
    public int getCodigoGerente(){
        return codigoGerente;
    }
}
