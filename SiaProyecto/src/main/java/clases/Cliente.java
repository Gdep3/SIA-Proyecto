package clases;

import java.util.*;

public class Cliente extends Usuario{
    private ArrayList<String> compras;
    private ArrayList<String> historialCompras;
    private int totalComprasCantidad;
    
    public Cliente(String nombre, String rut){
        super(nombre, rut);
        compras = new ArrayList();
        historialCompras = new ArrayList();
    }
    public Cliente(){
        historialCompras = new ArrayList();
        compras = new ArrayList();
    }
    
    @Override
    public String datosAString(){
        
        String stringHistorial = "";
        for(int i = 0; i < historialCompras.size(); i++){
            stringHistorial += historialCompras.get(i);
        }

        return getNombre() + ";" + getRut() + ";" + stringHistorial;
    }
        
    public void guardarCompras(String nombre, int precio, int cantidad){
        String compra = nombre + ", " + precio + ", " + cantidad + "\n";
        if(compras.contains(compra) == false){
            compras.add(compra);
        }
    }
    public String totalComprasPrecio(){
        totalComprasCantidad = 0;
        int totalComprasPrecio = 0;
        String[] arr = comprasAString().split("\n");
        String totalCompras = "";

       //Se prodia mejorar la comprobacion del arreglo.
        if(arr.length >= 1 && !arr[0].equals("")){
            for (String arr1 : arr) {
                String[] data = arr1.split(",");
                totalComprasPrecio += (Double.parseDouble(data[1].trim()) * Integer.parseInt(data[2].trim()));
                totalComprasCantidad += Integer.parseInt(data[2].trim());
            }
        }
        totalCompras += totalComprasPrecio;
        return totalCompras;
    }
    //No se si esto esta bien, si se puede o no hacer.
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
    public void vaciarCarrito(){
        compras.removeAll(compras);
    }
    public boolean buscarCompra(String nombre){
        for(int i = 0; i < compras.size(); i++){
            if(compras.get(i).contains(nombre))
                return true;
        }
        return false;
    }
    public String comprasAString(){
        String comp = "";
        for(int i = 0; i < compras.size(); i++){
            comp += compras.get(i);
        }
        return comp;
    }
    
    public ArrayList<String> getHistorialCompras(){
        return historialCompras;
    }
    
    public int getTotalComprasCantidad() {
        return totalComprasCantidad;
    }    
}
