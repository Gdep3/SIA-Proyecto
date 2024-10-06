    package clases;

public class Empleado extends Usuario{
    private int codigoGerente;
    
    public Empleado(String nombre, String rut, int codigoGerente){
        super(nombre, rut);
        this.codigoGerente = codigoGerente;
    }
    
    @Override
    public void mostrarInfo(){
        System.out.println("Usuario: " + getNombre());
        System.out.println("Rut: " + getRut());
        System.out.println("Código del empleado: " + getCodigoGerente());
    }
    
    public void setCodigoGerente(int codigo){
        codigoGerente = codigo;
    }
    public int getCodigoGerente(){
        return codigoGerente;
    }
}
