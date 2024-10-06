package clases;

import excepciones.NameException;
import excepciones.RutException;
import excepciones.MailException;


public class Usuario{
    private String nombre;
    private String rut;
    private String correo;
    
    public Usuario() {
    }
    
    public Usuario(String nombre, String rut){
        this.nombre = nombre;
        this.rut = rut;
    }
    public String datosAString(){
        return nombre + ", " + rut;
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
