package clases;

import excepciones.NameException;
import excepciones.NumberException;
import excepciones.CategoryException;
import java.util.ArrayList;

/*
Esta clase se encarga de administrar los distintos productos del supermercado,
utiliza colecciones para poder guardar varios productos dentro de esta. Esta 
clase se encarga de gestionar las colecciones con productos mediantes distintas 
funciones que administran bien las colecciones.
*/

public class Pasillo {
    //variables
    private int stockPasillo;
    private String categoriaPasillo;
    
    //arrayslist con los productos
    private ArrayList<Producto> productosPasillo;
    
    //constructores
    public Pasillo(ArrayList<Producto> totalProductos, String categoriaPasillo, int stockPasillo) {
        this.categoriaPasillo = categoriaPasillo;
        this.stockPasillo = stockPasillo;
        
        this.productosPasillo = new ArrayList();
        this.productosPasillo.addAll(totalProductos);
    }
    public Pasillo(ArrayList<Producto> totalProductos, String categoriaPasillo) {
        this.categoriaPasillo = categoriaPasillo;
        
        this.productosPasillo = new ArrayList();
        this.productosPasillo.addAll(totalProductos);
    }
    public Pasillo(String categoriaPasillo){
        this.categoriaPasillo = categoriaPasillo;
        this.productosPasillo = new ArrayList();    
    }
    public Pasillo(){
        this.productosPasillo = new ArrayList();    
    }

    //metodo para agregar productos mediante un producto
    public void agregarProducto(Producto producto){
        if(productosPasillo.contains(producto)){
            System.out.println("El producto ya se encuentra en la lista.");
            return;
        }
        stockPasillo += producto.getCantidad();
        productosPasillo.add(producto);
    }
    
    //metodo agregar producto mediante los componentes de producto
    public void agregarProducto(String nombre, String codigo, String categoria, int precio, int cantidad){
        Producto producto = new Producto(nombre, codigo, categoria, precio, cantidad);
        
        if(productosPasillo.contains(producto)){
            System.out.println("El producto ya se encuentra en la lista.");
            return;
        }
        stockPasillo += cantidad;
        productosPasillo.add(producto);
    }
    /*public void cambiarAtributosProducto(Producto producto, String nombre, String codigo, String categoria, String precio, String cantidad) throws NameException {
        int index = productosPasillo.indexOf(producto);
        Producto productoPasillo = productosPasillo.get(index);
        
        if(productoPasillo != null){
            cambiarNombre(productoPasillo, nombre)
        }
        else
            System.out.println("No se encuentra ese producto.");
    }*/
    
    //metodo para cambiar el nombre de un producto
    public void cambiarNombre(Producto producto, String nombre) throws NameException{
        if(productosPasillo.contains(producto)){
           int index = productosPasillo.indexOf(producto);
           
            productosPasillo.get(index).setNombre(nombre);
        }
        else
            System.out.println("No se encuentra ese producto.");
    }
    
    //metodo para cambiar la categoria de un producto
    public void cambiarCategoria(Producto producto, String categoria) throws CategoryException{
        if(productosPasillo.contains(producto)){
           int index = productosPasillo.indexOf(producto);
           
            productosPasillo.get(index).setCategoria(categoria);  
        }
        else
            System.out.println("No se encuentra ese producto.");
    }
    
    //metodo para cambiar el precio de un producto
    public void cambiarPrecio(Producto producto, String precio) throws NumberException{
        if(productosPasillo.contains(producto)){
           int index = productosPasillo.indexOf(producto);
            
            productosPasillo.get(index).setPrecio(Integer.parseInt(precio));
        }
        else
            System.out.println("No se encuentra ese producto.");
    }
    
    //metodo para cambiar la cantidad de un producto
    public void cambiarCantidad(Producto producto, String cantidad) throws NumberException{
        if(productosPasillo.contains(producto)){
            int index = productosPasillo.indexOf(producto);
            
            productosPasillo.get(index).setCantidad(Integer.parseInt(cantidad));
        }
        else
            System.out.println("No se encuentra ese producto.");
    }
    
    //metodo para eliminar un producto
    public boolean eliminarProducto(String nombre){
        for(int i = 0; i < productosPasillo.size(); i++)
        {
            Producto producto = productosPasillo.get(i);
            
             if(producto.getNombre().equalsIgnoreCase(nombre)){
                stockPasillo -= producto.getCantidad();
                productosPasillo.remove(i);
                return true;
            }
        } 
        return false;  
    }
    //Tal vez no se utilize.
   /* public boolean eliminarProducto(Producto producto1){
        for(int i = 0; i < productosPasillo.size(); i++){
            Producto producto = productosPasillo.get(i);
            if(producto.equals(producto1)
            {
                stockPasillo -= producto.getStock();
                productosPasillo.remove(i);
                return true;
            }
        }
        return false;
    }*/
    
    //metodo para mostrar los productos dentro del arraylist
    public void listarProductos(){
        for(int i = 0; i < productosPasillo.size(); i++){
            Producto producto = productosPasillo.get(i);
            System.out.println("Nombre: "+producto.getNombre()+", Codigo: "+producto.getCodigo()+", Categoria: "+producto.getCategoria()+", Precio: "+producto.getPrecio()+", Cantidad: "+producto.getCantidad());
        }
    }
    
    //metodo para obtener los productos dentro del arraylist
    public ArrayList obtenerProductos(){
        ArrayList productosCopia = new ArrayList();
        productosCopia.addAll(productosPasillo);
        return productosCopia;
    }
    
    //Metodos Setters.
    public void setCategoriaPasillo(String categoriaPasillo) throws CategoryException{
        if(categoriaPasillo != null)
            this.categoriaPasillo = categoriaPasillo;
        else{
            throw new CategoryException("Categoria invalida.");
        }
    }
    public void setStockPasillo(int stockPasillo) throws NumberException{
        if(stockPasillo >= 0)
            this.stockPasillo = stockPasillo;
        else
            throw new NumberException("Stock invalido.");
    }
    //Metodos Getters.
    public String getCategoriaPasillo(){
        return categoriaPasillo;
    }
    public int getStockPasillo(){
        return stockPasillo;
    }

    public ArrayList<Producto> getProductos() {
        return productosPasillo;
    }
} 