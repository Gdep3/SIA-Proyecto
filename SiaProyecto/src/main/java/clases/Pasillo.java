package clases;

import excepciones.NameException;
import excepciones.NumberException;
import excepciones.CategoryException;
import java.util.ArrayList;
import java.io.*;

public class Pasillo {
    private int stockPasillo;
    private String categoriaPasillo;
    
    private ArrayList<Producto> productosPasillo;

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

    //Metodos.
    public boolean agregarProducto(Producto producto){
        if(productosPasillo.contains(producto)){
            System.out.println("El producto ya se encuentra en la lista.");
            return false;
        }
        stockPasillo += producto.getCantidad();
        productosPasillo.add(producto);
        return true;
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
    /*public void cambiarAtributosProducto(Producto producto, String nombre, String codigo, String categoria, String precio, String cantidad) throws NameException {
        int index = productosPasillo.indexOf(producto);
        Producto productoPasillo = productosPasillo.get(index);
        
        if(productoPasillo != null){
            cambiarNombre(productoPasillo, nombre)
        }
        else
            System.out.println("No se encuentra ese producto.");
    }*/
    public boolean cambiarNombre(Producto producto, String nombre) throws NameException{
        if(productosPasillo.contains(producto)){
           int index = productosPasillo.indexOf(producto);
            productosPasillo.get(index).setNombre(nombre);
            return true;
        }
        else
            return false;
    }
    public boolean cambiarCategoria(Producto producto, String categoria) throws CategoryException{
        if(productosPasillo.contains(producto)){
           int index = productosPasillo.indexOf(producto);
            productosPasillo.get(index).setCategoria(categoria);  
            return true;
        }
        else
            return false;
    }
    public boolean cambiarPrecio(Producto producto, String precio) throws NumberException{
        if(productosPasillo.contains(producto)){
           int index = productosPasillo.indexOf(producto);
            productosPasillo.get(index).setPrecio(Double.parseDouble(precio));
            return true;
        }
        else
            return false;
    }
    public boolean cambiarCantidad(Producto producto, String cantidad) throws NumberException{
        if(productosPasillo.contains(producto)){
            int index = productosPasillo.indexOf(producto);
            productosPasillo.get(index).setCantidad(Integer.parseInt(cantidad));
            return true;
        }
        else
            return false;
    }
    public Producto buscarProducto(String nombre){
        for(int i = 0; i < productosPasillo.size(); i++){
            Producto producto = productosPasillo.get(i);
            if(producto.getNombre().equalsIgnoreCase(nombre))
                return producto;
        }
        return null;
    }
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
    public boolean eliminarProducto(Producto producto1){
        for(int i = 0; i < productosPasillo.size(); i++){
            Producto producto = productosPasillo.get(i);
            if(producto.equals(producto1))
            {
                stockPasillo -= producto.getCantidad();
                productosPasillo.remove(i);
                return true;
            }
        }
        return false;
    }
    public void listarProductos(){
        for(int i = 0; i < productosPasillo.size(); i++){
            Producto producto = productosPasillo.get(i);
            System.out.println("Nombre: "+producto.getNombre()+", Codigo: "+producto.getCodigo()+", Categoria: "+producto.getCategoria()+", Precio: "+producto.getPrecio()+", Cantidad: "+producto.getCantidad());
        }
    }
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
} 