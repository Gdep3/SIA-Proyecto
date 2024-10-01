package clases;

import excepciones.CodeException;
import excepciones.NameException;
import excepciones.NumberException;
import excepciones.CategoryException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
Esta clase se encarga de crear el menu del programa y la interfaz
, tambien implementa algunas funcionescomo el mostrar manejando las respectivas 
    excepciones para cada funcion
*/

public class Menu {
    //varaible supermercado para usar nuestro supermercado
    private Supermercado supermercado;
    
    //contructor
    public Menu(Supermercado supermercado){
        this.supermercado = supermercado;
    }
    //funcion para mostrar el menu
    public void textoMenu(){
        System.out.println("1. Agregar datos");

        System.out.println("2. Cambiar datos");
        
        System.out.println("3. Agregar datos por consola");

        System.out.println("4. Mostrar productos");
        
        System.out.println("5. Eliminar productos");
        
        System.out.println("Presione 'x' para salir");
    }
    
    //Funcion para el menu principal y elegir cada funcion del menu
    public void menuPrincipal()throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        
        while(true){
            textoMenu();
            
            String entrada = lector.readLine();
            if(entrada.equalsIgnoreCase("x")) break;
            
            switch(entrada){
                case "1":
                    añadirDatos();
                    break;
                case "2":
                    System.out.println("Función no implementada aún.");
                    System.out.println("Intente más tarde.");
                    break;
                case "3":
                    menuAñadirDatosPorConsola();
                    break;
                case "4":
                    listarProductos();
                    break;
                case "5":
                    eliminarDatos();
                    break;
            }
        }
    }
    //funcion para añadir datos al supermercado
    public void añadirDatos(){
        
    }
    //funcion para eliminar los datos del supermercado
    public void eliminarDatos()throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Ingrese la categoria del producto: ");
        String entrada = lector.readLine();
        
        Pasillo pasillo1 = supermercado.buscarPasillo(entrada);
        if(pasillo1 == null){
            System.out.println("Pasillo no encontrado intente nuevamente.");
            return;
        }
        
        System.out.println("Ingrese el nombre del producto: ");
        entrada = lector.readLine();
        
        pasillo1.eliminarProducto(entrada);
        
        System.out.println("Producto eliminado correctamente.");
       
    }
    //funcion para mostrar el menu de añadir datos (submenu)
    public void textoMenuAñadirDatos(){
        System.out.println("1. Agregar producto");

        System.out.println("2. Agregar pasillo");
        
        System.out.println("Presione 'x' para salir");
    }
    
    //menu para añadir datos por consola
    public void menuAñadirDatosPorConsola() throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        
         while(true){
            textoMenuAñadirDatos();
            
            String entrada = lector.readLine();
            if(entrada.equalsIgnoreCase("x")) break;
            
            switch(entrada){
                case "1":
                    añadirProductoPorConsola();
                    break;
                case "2":
                    añadirPasilloPorConsola();
                    break;
            }
        }
    }
    
    //funcion para mostrar los productos por pantalla
    public void listarProductos(){
        ArrayList pasillos = supermercado.obtenerPasillos();
        for(int i = 0; i < pasillos.size(); i++){
            ((Pasillo)pasillos.get(i)).listarProductos();
        }
    }
    
    //funcion para mostrar los productos por pantalla pero retorna una string
    public String listaProductos(){
        ArrayList pasillos = supermercado.obtenerPasillos();
        String ret;
        ret = "";
        for(int i = 0; i < pasillos.size(); i++){
            Pasillo pasillo = (Pasillo)pasillos.get(i);
            ArrayList productos = pasillo.obtenerProductos();
            for(int k = 0; k < productos.size(); k++){
                ret += ((Producto)productos.get(k)).aString();
            }
        }
        return ret;
    }
    
    //funcion para añadir un producto
    public void añadirProducto(Producto producto1){
        Pasillo pasillo1 = supermercado.buscarPasillo(producto1.getCategoria());
        
        pasillo1.agregarProducto(producto1);
    }
    
    //funcion para añadir un producto por consola
    public void añadirProductoPorConsola()throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Producto producto1 = new Producto();
        
        String entrada;
        Pasillo pasillo1 = supermercado.buscarPasillo(producto1.getCategoria());
        
        while(producto1.getNombre() == null){
            System.out.println("Ingrese nombre del producto: ");
            entrada = lector.readLine();
            
            try{
                producto1.setNombre(entrada);
            }
            catch(NameException e){
                System.out.println("Nombre invalido, intente nuevamente.");
            }
        }
        
        while(producto1.getCodigo() == null){
            System.out.println("Ingrese codigo del producto: ");
            entrada = lector.readLine();
            
            try{
                producto1.setCodigo(entrada);            
            }
            catch(CodeException e){
                System.out.println("Codigo invalido, igrese un condigo con 13 caracteres.");
            }
        }

        while(producto1.getCategoria() == null || pasillo1 == null){
            System.out.println("Ingrese categoria del producto: ");
            entrada = lector.readLine();
            
            try{
                producto1.setCategoria(entrada);            
            }
            catch(CategoryException e){
                System.out.println("Categoria invalida, intente nuevamente.");
            }
            pasillo1 = supermercado.buscarPasillo(producto1.getCategoria());
 
        }
        
        while(producto1.getCantidad() == -1){
            System.out.println("Ingrese cantidad del producto: ");
            entrada = lector.readLine();  
            
            try{
                producto1.setCantidad(Integer.parseInt(entrada));
            }
            catch(NumberException e){
                System.out.println("Cantidad invalida, intente nuevamente.");
            }
            catch(Exception e){
                System.out.println("Entrada invalida, intente nuevamente.");
            }
        }

        while(producto1.getPrecio() == -1){
            System.out.println("Ingrese precio del producto: ");
            entrada = lector.readLine();
            try{
                producto1.setPrecio(Integer.parseInt(entrada));        
            }
            catch(NumberException e)
            {
                System.out.println("Precio invalido, intente nuevamente.");
            }
            catch(Exception e){
                System.out.println("Entrada invalida, intente nuevamente.");
            }
        }
        
        pasillo1.agregarProducto(producto1);
                
        System.out.println("Producto agregado correctamente.");
    }
    
    //fuincion para añadir pasillos por consola
    public void añadirPasilloPorConsola()throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese la categoria del nuevo pasillo: ");
        String entrada = lector.readLine();
        
        supermercado.agregarPasillo(entrada);
    }
    public static void añadirDatosPorArchivo(){
        
    }
}
