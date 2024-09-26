import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


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
    public Supermercado(ArrayList<Pasillo> pasillos, HashMap<String, 
            Pasillo> pasillosPorCategoria){
        
        this.pasillos = new ArrayList<>(pasillos);  
        this.pasillosPorCategoria = new HashMap<>(pasillosPorCategoria);
    }
    public Supermercado(ArrayList<Pasillo> pasillos, 
            HashMap<String, Pasillo> pasillosPorCategoria, 
            int ventas, int stockTotal){
        
        pasillos = new ArrayList();
        this.pasillos.addAll(pasillos);
        
        pasillosPorCategoria = new HashMap();
        this.pasillosPorCategoria.putAll(pasillosPorCategoria);
        
        this.ventas = ventas;
        this.stockTotal = stockTotal;
    }
    
    //Metodos varios
    
    public void guardarEnCsv(String archivoDestino) throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDestino))){
            bw.write("Nombre;Categoria;Codigo;Precio;Cantidad");
            bw.newLine();
            
            for(Pasillo pasillo : pasillos){
                for(Producto producto : pasillo.getProductos()){
                    bw.write(producto.getNombre() + ";" +
                             producto.getCategoria()+ ";" +
                             producto.getCodigo() + ";" +
                             producto.getPrecio() + ";" +
                             producto.getCantidad());
                    bw.newLine();
                }
            }
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
    public void listarPasillos(){
        for(int i = 0; i < pasillos.size(); i++){
            Pasillo pasillo = pasillos.get(i);
            System.out.println("Categoria pasillo: " + 
                    pasillo.getCategoriaPasillo() + ", Stock del pasillo: " + 
                    pasillo.getStockPasillo());
             
            pasillo.listarProductos();
        }
    }
    public void listarPasillos(String categoria){
        for(int i = 0 ; i < pasillos.size() ; i++){
            Pasillo pasillo = pasillos.get(i);
            if(pasillo.getCategoriaPasillo().contains(categoria)){
                System.out.println("* Pasillo con categoria: " + categoria);
                System.out.println("* Stock del pasillo: " + pasillo.getStockPasillo());
            }
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
