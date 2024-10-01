package clases;

public class Cliente extends Usuario{
    
    public Cliente(String nombre, String rut){
        super(nombre, rut);
    }
    
    @Override
    public void mostrarMenu(){
        System.out.println("Mostrando menu cliente");
    }
}
