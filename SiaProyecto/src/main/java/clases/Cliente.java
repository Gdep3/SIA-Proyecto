package clases;

import java.util.*;

/*
Esta clase se encarga de administrar los objetos cliente con sus respectivos 
constructures, metodos setter y getter, etc... Administra todo lo que tiene que
ver con los productos comprados por el cliente.
*/

public class Cliente extends Usuario{
    private ArrayList<String> compras;
    private ArrayList<String> historialCompras;
    private int totalComprasCantidad;
    
    //Constructures
    public Cliente(String nombre, String rut, String correo){
        super(nombre, rut, correo);
        compras = new ArrayList();
        historialCompras = new ArrayList();
    }
    public Cliente(){
        historialCompras = new ArrayList();
        compras = new ArrayList();
    }
    
    //Metodos
    @Override
    
    //metodo para convertir datos a string a traves de historialCompras
    public String datosAString(){
        
        String stringHistorial = "";
        for(int i = 0; i < historialCompras.size(); i++){
            stringHistorial += historialCompras.get(i);
        }

        return getNombre() + ";" + getRut() + ";" + getCorreo() + ";"
                + stringHistorial;
    }
    
    //metodos para guardar compras dentro de lista de productos comprados
    public void guardarCompras(String nombre, int precio, int cantidad){
        String compra = nombre + ", " + precio + ", " + cantidad + "\n";
        if(compras.contains(compra) == false){
            compras.add(compra);
        }
    }
    
    //metodo para administrar el total de las ventas
    public String totalComprasPrecio(){
        totalComprasCantidad = 0;
        int totalComprasPrecio = 0;
        String[] arr = comprasAString().split("\n");
        String totalCompras = "";
        if(arr.length >= 1 && !arr[0].equals("")){
            for (String arr1 : arr) {
                String[] data = arr1.split(",");
                totalComprasPrecio += (Double.parseDouble(data[1].trim()) * 
                                        Integer.parseInt(data[2].trim()));
                
                totalComprasCantidad += Integer.parseInt(data[2].trim());
            }
        }
        totalCompras += totalComprasPrecio;
        return totalCompras;
    }

    //metodo para añadir las compras al historialCompras
    public void añadirAHistorial(){
        historialCompras.addAll(compras);
    }
    public void eliminarCompra(String nombre){
        for(int i = 0; i < compras.size(); i++){
            if(compras.get(i).contains(nombre)){
                compras.remove(i);
            }
        }
    }
    
    //metodo para vaciar el carrito
    public void vaciarCarrito(){
        compras.removeAll(compras);
    }
    
    //metodo para buscar la compra dentro de compras
    public boolean buscarCompra(String nombre){
        for(int i = 0; i < compras.size(); i++){
            if(compras.get(i).contains(nombre))
                return true;
        }
        return false;
    }
    
    //metodo para convertir las compras a String
    public String comprasAString(){
        String comp = "";
        for(int i = 0; i < compras.size(); i++){
            comp += compras.get(i);
        }
        return comp;
    }
    
    public String buscarEnHistorial(int i){
        return historialCompras.get(i);
    }
    
    public int tallaHistorial(){
        return historialCompras.size();    
    }
    
    // metodos Getters
    public int getTotalComprasCantidad() {
        return totalComprasCantidad;
    }    
}
