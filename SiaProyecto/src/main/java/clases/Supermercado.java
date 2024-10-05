package clases;

import excepciones.CorridorException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Isidora
 */

public class Supermercado {
    private int stockTotal;
    private int ventas;
    
    private ArrayList<Pasillo> pasillos; 
    private HashMap<String, Pasillo> pasillosPorCategoria;
    
    //Constructores
    public Supermercado(){
        pasillos = new ArrayList();
        pasillosPorCategoria = new HashMap();
    }
    public Supermercado(int ventas, int stockTotal){
        pasillos = new ArrayList();
        pasillosPorCategoria = new HashMap();

        this.ventas = ventas;
        this.stockTotal = stockTotal;
    }
    //Metodos varios.
    public void agregarPasillo(Pasillo pasillo1){
        if(pasillosPorCategoria.containsKey(pasillo1.getCategoriaPasillo())){
            System.out.println("Este pasillo ya existe en el supermercado.");
        }
        pasillosPorCategoria.put(pasillo1.getCategoriaPasillo(), pasillo1);
        pasillos.add(pasillo1);
        
        stockTotal += pasillo1.getStockPasillo();
    }
    public void agregarPasillo(String categoria){
        if(pasillosPorCategoria.containsKey(categoria)){
            System.out.println("Este pasillo ya existe en el supermercado.");
        }
        Pasillo pasillo1 = new Pasillo(categoria);
        
        pasillosPorCategoria.put(pasillo1.getCategoriaPasillo(), pasillo1);
        pasillos.add(pasillo1);
        
        stockTotal += pasillo1.getStockPasillo();
    }
    public ArrayList obtenerPasillos(){
        ArrayList copiaPasillos = new ArrayList();
        copiaPasillos.addAll(pasillos);
        
        return copiaPasillos;
    }

    public Pasillo buscarPasillo(String categoria){
        if(pasillosPorCategoria.containsKey(categoria)){
            return pasillosPorCategoria.get(categoria);
        }
        return null;
    }
    public void listarPasillos(){
        for(int i = 0; i < pasillos.size(); i++){
            Pasillo pasillo = pasillos.get(i);
            System.out.println("Categoria pasillo: " + pasillo.getCategoriaPasillo() + ", Stock del pasillo: " + pasillo.getStockPasillo());
            //pasillo.listarProductos();
        }
    }
    //Metodos sobre productos.
    public void añadirProductoASupermercado(Producto producto1) throws CorridorException{
        Pasillo pasillo1 = pasillosPorCategoria.get(producto1.getCategoria());
        if(pasillo1 != null)
            pasillo1.agregarProducto(producto1);
        else
            throw new CorridorException("Pasillo invalido.");
    }
    public String listaDeProductos(){
        String ret;
        ret = "";
        for(int i = 0; i < pasillos.size(); i++){
            Pasillo pasillo = (Pasillo)pasillos.get(i);
            ArrayList productos = pasillo.obtenerProductos();
            for(int k = 0; k < productos.size(); k++){
                ret += ((Producto)productos.get(k)).obtenerStringAtributos();
            }
        }
        return ret;
    }    
    public String listaDeProductosNombrePrecio(){
        String ret;
        ret = "";
        for(int i = 0; i < pasillos.size(); i++){
            Pasillo pasillo = (Pasillo)pasillos.get(i);
            ArrayList productos = pasillo.obtenerProductos();
            for(int k = 0; k < productos.size(); k++){
                ret += ((Producto)productos.get(k)).obtenerNombrePrecio();
            }
        }
        return ret;
    }
    public void listarProductosEnSupermercado(){
        for(int i = 0; i < pasillos.size(); i++){
            ((Pasillo)pasillos.get(i)).listarProductos();
        }
    }
    public boolean buscarProductoEnSupermercado(String nombre){
        for(int  i = 0; i < pasillos.size(); i++){
            Producto producto1 = ((Pasillo)pasillos.get(i)).buscarProducto(nombre);
            if(producto1 != null)
                return true;
        }
        return false;
    }
    public void eliminarProductoDelSupermercado(String nombre){
        for(int i = 0; i < pasillos.size(); i++){
            Producto producto1 = ((Pasillo)pasillos.get(i)).buscarProducto(nombre);
            if(producto1 != null){
                pasillos.get(i).eliminarProducto(nombre);
            }   
        }
    }
    public void eliminarProductoDelSupermercado(String categoria, String nombre){
        Pasillo pasillo1 = pasillosPorCategoria.get(categoria);

        pasillo1.eliminarProducto(nombre);

    }
    //Setters
    public void setVentas(int ventas){
        this.ventas = ventas;
    }
    public void setStockTotal(int stockTotal){
        this.stockTotal = stockTotal;
    }
    //Getters
    public int getVentas(){
        return ventas;
    }
    public int getStockTotal(){
        return stockTotal;
    }
}
