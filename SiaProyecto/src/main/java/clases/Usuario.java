package clases;


import excepciones.RutException;


public abstract class Usuario{
    private String nombre;
    private String rut;
    
    public Usuario(String nombre, String rut){
        this.nombre = nombre;
        this.rut = rut;
    }
    public abstract void  mostrarMenu();
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setRut(String rut) throws RutException{
        String newRut = rut.formatted(".");
        if(newRut.length() == 10)
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
