package clases;

import excepciones.CorridorException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Esta clase representa al supermercado, por lo que contiene distintas colecciones
que representan los pasillos organizados por categoria, que a la vez cada pasillo
tiene productos con su respectiva categoria.
Esta clase se encarga de organizar y acomodar cada pasillos con sus productos
mediante distintos metodos que ayudan a realizar distintas tareas.
*/

public class Supermercado {
    private int stockTotal;
    private int ventas;
    
    //Colecciones para guardar los pasillos con los producto
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
    
    //metodos
    
    //metodo para guardar los producto dentro del csv
    public void guardarEnCsv(Producto producto) throws IOException{
        String archivoDestino = "src/main/recursos/datosSupermercado.csv";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDestino, true))){
            bw.newLine();
            bw.write(producto.getNombre() + ";" +
                    producto.getCategoria()+ ";" +
                    "00-00-0000" + ";" +
                    producto.getCantidad() + ";" +
                    producto.getPrecio() + ";" +
                    producto.getCodigo());
            
        } catch (IOException e){
            System.out.println("Error al escribir en el archivo Csv");
        }
    }
   
    //metodo para hacer el reporte de productos vendidos
    public void reportar(Cliente cliente) throws IOException{
        String archivoDestino = "src/main/recursos/Reporte.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDestino,true))){
            bw.newLine();
            for(int i = 0 ; i < cliente.tallaHistorial(); i++){
                String[] arr = cliente.buscarEnHistorial(i).split(",");
                bw.write("Producto: " + arr[0] + "\n" + " - Precio: " + arr[1] + "\n" + " - Cantidad: " + arr[2] + "\n");
                bw.write("===========================================\n\n");
            }
        } catch(IOException e){
            System.out.println("Error al escribir en el archivo de ventas: " + e.getMessage());
        }
    }
    
    //metodo para eliminar un producto del csv
    public void eliminarProductoDeCsv(String nombreProductoAEliminar) {
        List<String> lineasRestantes = new ArrayList<>();
        String linea;
        String archivoCsv = "src/main/recursos/datosSupermercado.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCsv))) {
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");
                String nombreProducto = valores[0];
                
                if (!nombreProducto.equalsIgnoreCase(nombreProductoAEliminar)) {
                    lineasRestantes.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCsv))) {
            for (String lineaRestante : lineasRestantes) {
                bw.write(lineaRestante);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }
    
    //metodo para agregar pasillos al supermercado
    public void agregarPasillo(Pasillo pasillo1){
        if(pasillosPorCategoria.containsKey(pasillo1.getCategoriaPasillo())){
            System.out.println("Este pasillo ya existe en el supermercado.");
        }
        pasillosPorCategoria.put(pasillo1.getCategoriaPasillo(), pasillo1);
        pasillos.add(pasillo1);
        
        stockTotal += pasillo1.getStockPasillo();
    }
    
    //metodo para agregar un pasillo al supermercado ingresando una categoria
    public void agregarPasillo(String categoria){
        if(pasillosPorCategoria.containsKey(categoria)){
            System.out.println("Este pasillo ya existe en el supermercado.");
        }
        Pasillo pasillo1 = new Pasillo(categoria);
        
        pasillosPorCategoria.put(pasillo1.getCategoriaPasillo(), pasillo1);
        pasillos.add(pasillo1);
        
        stockTotal += pasillo1.getStockPasillo();
    }
    
    //funcion para obtener pasillos 
    public ArrayList obtenerPasillos(){
        ArrayList copiaPasillos = new ArrayList();
        copiaPasillos.addAll(pasillos);
        
        return copiaPasillos;
    }
    
    //funcion para buscar pasillos dentro del supermercado
    public Pasillo buscarPasillo(String categoria){
        if(pasillosPorCategoria.containsKey(categoria)){
            return pasillosPorCategoria.get(categoria);
        }
        return null;
    }
    
    //funcion para mostrar los distintos pasillos del supermercado
    public void listarPasillos(){
        for(int i = 0; i < pasillos.size(); i++){
            Pasillo pasillo = pasillos.get(i);
            System.out.println("Categoria pasillo: " + pasillo.getCategoriaPasillo() 
                                + ", Stock del pasillo: " + pasillo.getStockPasillo());
            //pasillo.listarProductos();
        }
    }
    
    //Metodos sobre productos.
    
    //metodo para añadir productos al supermercado
    public void añadirProductoASupermercado(Producto producto1) throws CorridorException{
        Pasillo pasillo1 = pasillosPorCategoria.get(producto1.getCategoria());
        if(pasillo1 != null){
            pasillo1.agregarProducto(producto1);
        }
        else
            throw new CorridorException("Pasillo invalido.");
    }
    
    //metodo para listar productos dentro del sepermercado por sus atributos
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
    
    //metodo para mostrar la lista de productos por nombre y precio
    public String listaDeProductosNombrePrecio(){
        String ret;
        ret = "";
        for(int i = 0; i < pasillos.size(); i++){
            Pasillo pasillo = (Pasillo)pasillos.get(i);
            ArrayList productos = pasillo.obtenerProductos();
            for(int k = 0; k < productos.size(); k++){
                ret += ((Producto)productos.get(k)).obtenerNombrePrecioCantidad();
            }
        }
        return ret;
    }
    
    //metodo para listar los productos en el supermercado
    public void listarProductosEnSupermercado(){
        for(int i = 0; i < pasillos.size(); i++){
            ((Pasillo)pasillos.get(i)).listarProductos();
        }
    }
    
    //metodo para buscar productos en el supermercado a traves del nombre
    public boolean buscarProductoEnSupermercado(String nombre){
        for(int  i = 0; i < pasillos.size(); i++){
            Producto producto1 = ((Pasillo)pasillos.get(i)).buscarProducto(nombre);
            if(producto1 != null)
                return true;
        }
        return false;
    }
    
    //metodo para obtener producto en el supermercado por el nombre
    public Producto obtenerProductoEnSupermercado(String nombre){
        for(int i = 0; i < pasillos.size(); i++){
            Producto producto1 = ((Pasillo)pasillos.get(i)).buscarProducto(nombre);
            if(producto1 != null)
                return producto1;
        }
        return null;
    }
    
    //metodo de elminar productos del supermercado por su nombre
    public void eliminarProductoDelSupermercado(String nombre){
        for(int i = 0; i < pasillos.size(); i++){
            Producto producto1 = ((Pasillo)pasillos.get(i)).buscarProducto(nombre);
            if(producto1 != null){
                pasillos.get(i).eliminarProducto(producto1);
                stockTotal -= producto1.getCantidad();
                return;
            }   
        }
    }
    
    //metodo de eliminar productos del supermercado por categoria y nombre
    public void eliminarProductoDelSupermercado(String categoria, String nombre){
        Pasillo pasillo1 = pasillosPorCategoria.get(categoria);

        pasillo1.eliminarProducto(nombre);
        stockTotal -= pasillo1.getStockPasillo();

    }

    //Setters
    public void setVentas(int ventas){
        this.ventas += ventas;
    }
    public void setStockTotal(int stockTotal){
        this.stockTotal += stockTotal;
    }
    
    //Getters
    public int getVentas(){
        return ventas;
    }
    public int getStockTotal(){
        return stockTotal;
    }
}
