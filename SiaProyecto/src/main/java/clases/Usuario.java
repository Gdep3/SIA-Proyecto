package clases;

import excepciones.NameException;
import excepciones.RutException;


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
        String newRut = rut.formatted(".");
        if(newRut.length() == 10 || newRut.length() == 9)
            this.rut = rut;
        else
            throw new RutException();
    }
    public String getNombre(){
        return nombre;
    }
    public String getRut(){
        return rut;
    }
}
