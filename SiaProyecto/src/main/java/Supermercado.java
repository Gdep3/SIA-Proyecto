import java.util.ArrayList;
import java.util.HashMap;

import java.io.*;

public class Supermercado {
    int stockTotal;
    int ventas;
    
    private ArrayList<Pasillo> pasillos; 
    private HashMap<String, Pasillo> pasillosPorCategoria;
    
    //Constructores
    public Supermercado(){
        pasillos = new ArrayList();
        pasillosPorCategoria = new HashMap();
    }
    public Supermercado(ArrayList<Pasillo> pasillos, HashMap<String, Pasillo> pasillosPorCategoria){
        pasillos = new ArrayList();
        this.pasillos.addAll(pasillos);
        
        pasillosPorCategoria = new HashMap();
        this.pasillosPorCategoria.putAll(pasillosPorCategoria);
    }
    public Supermercado(ArrayList<Pasillo> pasillos, HashMap<String, Pasillo> pasillosPorCategoria, int ventas, int stockTotal){
        pasillos = new ArrayList();
        this.pasillos.addAll(pasillos);
        
        pasillosPorCategoria = new HashMap();
        this.pasillosPorCategoria.putAll(pasillosPorCategoria);
        
        this.ventas = ventas;
        this.stockTotal = stockTotal;
    }
    //Metodos varios
    public void agregarPasillo(Pasillo pasillo1){
        if(pasillosPorCategoria.containsKey(pasillo1.getCategoriaPasillo())){
            System.out.println("Este pasillo ya existe en el supermercado.");
        }
        pasillosPorCategoria.put(pasillo1.getCategoriaPasillo(), pasillo1);
        pasillos.add(pasillo1);
        
        stockTotal += pasillo1.getStockPasillo();
    }
    public void listarPasillos(){
        for(int i = 0; i < pasillos.size(); i++){
            Pasillo pasillo = pasillos.get(i);
            System.out.println("Categoria pasillo: " + pasillo.getCategoriaPasillo() + ", Stock del pasillo: " + pasillo.getStockPasillo());
            pasillo.listarProductos();
        }
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
