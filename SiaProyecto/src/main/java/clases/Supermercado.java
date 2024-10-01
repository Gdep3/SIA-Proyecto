package clases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

    public Supermercado(ArrayList<Pasillo> pasillos, HashMap<String, 
            Pasillo> pasillosPorCategoria){
        this.pasillos = new ArrayList<>(pasillos);  
        this.pasillosPorCategoria = new HashMap<>(pasillosPorCategoria);
    }
    
    //Metodos varios.
    
    public void guardarEnCsv(String archivoDestino, Producto producto) throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDestino, true))){
            
            bw.write(producto.getNombre() + ";" +
                    producto.getCategoria()+ ";" +
                    producto.getCodigo() + ";" +
                    producto.getPrecio() + ";" +
                    producto.getCantidad());
            bw.newLine();
            
        } catch (IOException e){
            System.out.println("Error al escribir en el archivo Csv");
        }
    }
    
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
    /*public Pasillo buscarPasillo(String categoria){
        for(int i = 0; i < pasillos.size();i++){
            if(pasillos.get(i).getCategoriaPasillo().equals(categoria))
                return pasillos.get(i);
        }
        return null;
    }*/
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
