package clases;

import excepciones.NameException;
import excepciones.RutException;
import java.util.regex.Pattern;


public class Usuario{
    private String nombre;
    private String rut;
    
    public Usuario() {
    }
    
    public Usuario(String nombre, String rut){
        this.nombre = nombre;
        this.rut = rut;
    }
    public void  mostrarInfo() {
        System.out.println("Menú Principal");
    }
    
    public void setNombre(String nombre) throws NameException{
        if(nombre != null && !nombre.equals(""))
            this.nombre = nombre;
        else
            throw new NameException("Nombre no válido");
    }
    public void setRut(String rut) throws RutException{
        if (rut == null || rut.isEmpty()) {
            throw new RutException();
        }

        if (rut.length() < 9 || rut.length() > 10) {
            throw new RutException();
        }
        char guion = rut.charAt(rut.length() - 2);
        if (guion != '-') {
            throw new RutException();
        }
        this.rut = rut;
        
    }
    public String getNombre(){
        return nombre;
    }
    public String getRut(){
        return rut;
    }
}
