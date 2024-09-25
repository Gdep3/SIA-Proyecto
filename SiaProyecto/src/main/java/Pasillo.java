import java.util.ArrayList;
import java.io.*;

public class Pasillo {
    int stockPasillo;
    private String categoriaPasillo;
    private ArrayList<Producto> productosPasillo;

    public Pasillo(ArrayList<Producto> totalProductos, String categoriaPasillo) {

        this.categoriaPasillo = categoriaPasillo;
        
        this.productosPasillo = new ArrayList();
        this.productosPasillo.addAll(totalProductos);
    }
    public Pasillo(String categoriaPasillo)
    {
        this.categoriaPasillo = categoriaPasillo;
        this.productosPasillo = new ArrayList();    
    }
    public Pasillo()
    {
        this.productosPasillo = new ArrayList();    
    }

    //Metodos.
    public void agregarProducto(Producto producto){
        if(productosPasillo.contains(producto)){
            System.out.println("El producto ya se encuentra en la lista.");
            return;
        }
        stockPasillo += producto.getCantidad();
        productosPasillo.add(producto);
    }
    public void agregarProducto(String nombre, String codigo, String categoria, double precio, int cantidad){
        Producto producto = new Producto(nombre, codigo, categoria, precio, cantidad);
        
        if(productosPasillo.contains(producto)){
            System.out.println("El producto ya se encuentra en la lista.");
            return;
        }
        stockPasillo += cantidad;
        productosPasillo.add(producto);
    }
    public void cambiarAtributosProducto(Producto producto, String nombre, String codigo, String categoria, String precio, String cantidad){
        int index = productosPasillo.indexOf(producto);
        Producto productoPasillo = productosPasillo.get(index);
        
        if(productoPasillo != null){
            productoPasillo.setNombre(nombre);
            
            productoPasillo.setCategoria(codigo);  
            
            productoPasillo.setPrecio(Double.parseDouble(precio));
        }
        else
            System.out.println("No se encuentra ese producto.");
    }
    public void cambiarNombre(Producto producto, String nombre){
        if(productosPasillo.contains(producto)){
           int index = productosPasillo.indexOf(producto);
           
            productosPasillo.get(index).setNombre(nombre);
        }
        else
            System.out.println("No se encuentra ese producto.");
    }
    public void cambiarCategoria(Producto producto, String categoria){
        if(productosPasillo.contains(producto)){
           int index = productosPasillo.indexOf(producto);
           
            productosPasillo.get(index).setCategoria(categoria);  
        }
        else
            System.out.println("No se encuentra ese producto.");
    }
    public void cambiarPrecio(Producto producto, String precio){
        if(productosPasillo.contains(producto)){
           int index = productosPasillo.indexOf(producto);
            
            productosPasillo.get(index).setPrecio(Double.parseDouble(precio));
        }
        else
            System.out.println("No se encuentra ese producto.");
    }
    public void cambiarCantidad(Producto producto, String cantidad){
        if(productosPasillo.contains(producto)){
            int index = productosPasillo.indexOf(producto);
            
            productosPasillo.get(index).setCantidad(Integer.parseInt(cantidad));
        }
        else
            System.out.println("No se encuentra ese producto.");
    }
    public void eliminarProducto(int index){
        Producto producto1 = productosPasillo.get(index);
     
        if(productosPasillo.contains(producto1))
            productosPasillo.remove(producto1);
        else
            System.out.println("Producto no encontrado.");
    }
    public void eliminarProducto(Producto producto1){
        if(productosPasillo.contains(producto1))
            productosPasillo.remove(producto1);
        else
            System.out.println("Producto no encontrado.");
    }
    public void listarProductos(){
        for(int i = 0; i < productosPasillo.size(); i++){
            Producto producto = productosPasillo.get(i);
            System.out.println("Producto " + (i + 1) + ":");
            System.out.println("Nombre: "+producto.getNombre()+", Codigo: "+producto.getCodigo()+", Categoria: "+producto.getCategoria()+", Precio: "+producto.getPrecio()+", Cantidad: "+producto.getCantidad());
        }
        System.out.println("");
    }
    //Metodos Setters.
    public void setCategoriaPasillo(String categoriaPasillo){
        if(categoriaPasillo != null)
            this.categoriaPasillo = categoriaPasillo;
    }
    public void setStockPasillo(int stockPasillo){
        this.stockPasillo = stockPasillo;
    }
    //Metodos Getters.
    public String getCategoriaPasillo(){
        return categoriaPasillo;
    }
    public int getStockPasillo(){
        return stockPasillo;
    }
} 