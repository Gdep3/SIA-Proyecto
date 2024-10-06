package clases;

/*
Esta clase que se extiende de usuario se encarga administrar los metodos del
usuario y crear el objeto como tal.
*/

public class Empleado extends Usuario{
    private int codigoGerente;
    
    //Constructor
    public Empleado(String nombre, String rut, int codigoGerente){
        super(nombre, rut);
        this.codigoGerente = codigoGerente;
    }
    
    //Metodos
    @Override
    
    //metodo para pasar de datos a String el nombre, rut y codigo
    public String datosAString(){
        return getNombre() + ", " + getRut() + ", " + codigoGerente;
    }
    
    //metodo setter
    public void setCodigoGerente(int codigo){
        codigoGerente = codigo;
    }
    
    //metodo getter
    public int getCodigoGerente(){
        return codigoGerente;
    }
}
