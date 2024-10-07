package clases;

import excepciones.NameException;
import excepciones.RutException;
import excepciones.MailException;

/*
Esta clase se encargad de manejar los objetos usuario y sus distintos metodos
nos sirve como clase padre para cliente.
*/

public class Usuario{
    private String nombre;
    private String rut;
    private String correo;
    
    //Constructores
    public Usuario() {
    }
    
    public Usuario(String nombre, String rut, String correo){
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
    }
    
    //Metodos
    
    //metodo para convertir de dato a Strinf nombre y rut
    public String datosAString(){
        return nombre + ", " + rut + ", " + correo;
    }
    
    //Metodos setter
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
        if (!rut.matches("[0-9-]+")) { // Es una forma para verificar que no tenga letras
            throw new RutException();
        }
        char guion = rut.charAt(rut.length() - 2);
        if (guion != '-') {
            throw new RutException();
        } 
        this.rut = rut;
        
    }
    
    public void setCorreo(String correo) throws MailException{
        if (correo == null || correo.isEmpty())  {
            throw new MailException();
        }
        if (!correo.contains("@gmail.com")) {
            throw new MailException();
        }
        String institucion = correo.substring(correo.indexOf('@'), correo.length());
        if (institucion.equals("@gmail.com")) {
            this.correo = correo;
        }
        else {
            throw new MailException();
        }
    }
    
    //Metodos getter
    public String getNombre(){
        return nombre;
    }
    public String getRut(){
        return rut;
    }
    public String getCorreo() {
        return correo;
    }
}