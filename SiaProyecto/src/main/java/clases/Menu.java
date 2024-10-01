package clases;


import excepciones.CodeException;
import excepciones.NameException;
import excepciones.NumberException;
import excepciones.CategoryException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
    private Supermercado supermercado;
    
    public Menu(Supermercado supermercado){
        this.supermercado = supermercado;
    }
    
    public void textoMenu(){
        System.out.println("1. Agregar datos");

        System.out.println("2. Cambiar datos");
        
        System.out.println("3. Agregar datos por consola");

        System.out.println("4. Mostrar productos");
        
        System.out.println("5. Eliminar productos");
        
        System.out.println("Presione 'x' para salir");
    }
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
    
    public void añadirDatos(){
        Pasillo pasillo1 = new Pasillo("Lacteos");
        pasillo1.agregarProducto("Leche", "1234567891234", "Lacteos", 910, 12);
        pasillo1.agregarProducto("Yogurt", "8743673138888", "Lacteos", 800, 10);
        pasillo1.agregarProducto("Queso Mozzarella", "7879003434360", "Lacteos", 1000, 9);
        pasillo1.agregarProducto("Leche desnatada", "4857352850339", "Lacteos", 940, 30);
        supermercado.agregarPasillo(pasillo1);
        
        pasillo1 = new Pasillo("Liquidos");
        pasillo1.agregarProducto("Coca-cola", "1898413626054", "Liquidos", 500, 12);
        pasillo1.agregarProducto("Jugo de naranja", "4505026055525", "Liquidos", 450, 10);
        pasillo1.agregarProducto("Jugo de durazno", "0076964145370", "Liquidos", 500, 13);
        pasillo1.agregarProducto("Pepsi", "7670856898723", "Liquidos", 450, 10);
        supermercado.agregarPasillo(pasillo1);

        pasillo1 = new Pasillo("Congelados");
        pasillo1.agregarProducto("Calamar", "3762166803090", "Congelador", 1200, 12);
        pasillo1.agregarProducto("Nuggets de pollo", "2379247108455", "Congelador", 500, 30);
        pasillo1.agregarProducto("Reineta", "5573775502958", "Congelador", 2000, 25);
        pasillo1.agregarProducto("Chuleta de cerdo", "2943275647464", "Congelador", 2500, 40);
        supermercado.agregarPasillo(pasillo1);

        pasillo1 = new Pasillo("Cuidado personal");
        pasillo1.agregarProducto("Shampoo", "8055018996603", "Cuidado personal", 1250, 20);
        pasillo1.agregarProducto("Jabón", "7485067539126", "Cuidado personal", 1200, 30);
        pasillo1.agregarProducto("Perfume", "4273094929419", "Cuidado personal", 2500, 25);
        pasillo1.agregarProducto("Pasta de dientes", "0849435039922", "Cuidado personal", 1000, 40);
        supermercado.agregarPasillo(pasillo1);
    }

    public void eliminarDatos()throws IOException{
        //Se debe mejorar la logica de eliminacion para que sea mas intuitiva, es decir, que tal vez solo dependa del nombre del producto.
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
    public void textoMenuAñadirDatos(){
        System.out.println("1. Agregar producto");

        System.out.println("2. Agregar pasillo");
        
        System.out.println("Presione 'x' para salir");
    }
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
    public void listarProductos(){
        ArrayList pasillos = supermercado.obtenerPasillos();
        for(int i = 0; i < pasillos.size(); i++){
            ((Pasillo)pasillos.get(i)).listarProductos();
        }
    }
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
    public void añadirProducto(Producto producto1){
        Pasillo pasillo1 = supermercado.buscarPasillo(producto1.getCategoria());
        
        pasillo1.agregarProducto(producto1);
    }
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
                producto1.setPrecio(Double.parseDouble(entrada));        
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
    public void añadirPasilloPorConsola()throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese la categoria del nuevo pasillo: ");
        String entrada = lector.readLine();
        
        supermercado.agregarPasillo(entrada);
    }
    public static void añadirDatosPorArchivo(){
        
    }
}
