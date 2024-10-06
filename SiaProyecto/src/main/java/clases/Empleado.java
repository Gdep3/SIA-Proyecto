    package clases;

public class Empleado extends Usuario{
    
    
    public Empleado() {
    }
    public Empleado(String nombre, String rut){
        super(nombre, rut);
    }
   
    @Override
    public String datosAString(){
        return getNombre() + ", " + getRut();
    }
}
