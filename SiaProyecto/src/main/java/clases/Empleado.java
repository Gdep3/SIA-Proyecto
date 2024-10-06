package clases;

import java.util.ArrayList;

public class Empleado extends Usuario{
    private int codigoGerente;
    private ArrayList<String> productoSinStock;
    
    public Empleado(String nombre, String rut, int codigoGerente){
        super(nombre, rut);
        this.codigoGerente = codigoGerente;
        productoSinStock = new ArrayList();
    }
    
    @Override
    public String datosAString(){
        String stringProductosSinStock = "";
        for(int i = 0; i < productoSinStock.size(); i++){
            stringProductosSinStock += productoSinStock.get(i);
        }
        
        return getNombre() + "; " + getRut() + "; " + codigoGerente +";"+ stringProductosSinStock;
    }
    public void vaciarProductosSinStock(){
        productoSinStock.removeAll(productoSinStock);
    }
    public void agregarProductosSinStock(String datos){
        if(productoSinStock.contains(datos) == false)
            productoSinStock.add(datos);
    }

    
    public void setCodigoGerente(int codigo){
        codigoGerente = codigo;
    }
    public int getCodigoGerente(){
        return codigoGerente;
    }
}
