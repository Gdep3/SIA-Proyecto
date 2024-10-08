package clases;

import java.util.ArrayList;

/*
Esta clase que se extiende de usuario se encarga administrar los metodos del
usuario y crear el objeto como tal.
*/

public class Empleado extends Usuario{
    private ArrayList<String> productoSinStock;
    
    //Constructor
    public Empleado(String nombre, String rut, String correo){
        super(nombre, rut, correo);
        productoSinStock = new ArrayList();
    }
    
    public Empleado(){
        this.productoSinStock = new ArrayList<>();
    }
    
    //Metodos
    @Override
    
    //metodo para pasar de datos a String el nombre, rut y codigo
    public String datosAString(){
        String stringProductosSinStock = "";
        for(int i = 0; i < productoSinStock.size(); i++){
            stringProductosSinStock += productoSinStock.get(i);
        }
        
        return getNombre() + "; " + getRut()+";"+ getCorreo() + ";" +stringProductosSinStock;
    }
    
    //metodo para vaciar los productos sin stock
    public void vaciarProductosSinStock(){
        productoSinStock.removeAll(productoSinStock);
    }
    
    //metodo para agregar Productos sin stock
    public void agregarProductosSinStock(String datos){
        if(productoSinStock.contains(datos) == false)
            productoSinStock.add(datos);
    }
}
