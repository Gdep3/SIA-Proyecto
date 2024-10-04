package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
Esta clase se encarga de las funciones de leer el csv mediante el bufferedReader
y el FileReader, esta retorna una coleccion supermercado la cual sera el 
supermercado con todos los productos dentro para poder modificar
con cualquier funcion del supermercado este archivo tipo csv.
*/

public class CsvFileReader {
    //variable que sera el delimitador en nuestro archivo csv
    private String delimiter;
    
    //Constructor
    public CsvFileReader(String delimiter) {
        this.delimiter = delimiter;
    }
    
    //Funcion para leer el csv
    public Supermercado leerCsv(String archivo) {
        Map<String, Pasillo> pasillos_Categoria = new HashMap<>();
        ArrayList<Pasillo> pasillos = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                
                String nombre = values[0];
                String codigo = values[5];
                String categoria = values[1];
                int cantidad = Integer.parseInt(values[3]);
                int precio = Integer.parseInt(values[4]);
                
                Pasillo pasillo = pasillos_Categoria.getOrDefault(categoria, 
                                                        new Pasillo(categoria));
                
                pasillo.agregarProducto(nombre,codigo,categoria,precio,cantidad);
                if(!pasillos_Categoria.containsKey(categoria)){
                    pasillos_Categoria.put(categoria, pasillo);
                    pasillos.add(pasillo);
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new Supermercado(pasillos, (HashMap<String, Pasillo>) pasillos_Categoria);
    }
    
    //funcion para guardar los producto dentro del csv
    public void guardarEnCsv(String archivoDestino, Producto producto) throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDestino, true))){
            
            bw.write(producto.getNombre() + ";" +
                    producto.getCategoria()+ ";" +
                    producto.getCantidad() + ";" +
                    producto.getPrecio() + ";" +
                    producto.getCodigo());
            bw.newLine();
            
        } catch (IOException e){
            System.out.println("Error al escribir en el archivo Csv");
        }
    }
    
    //funcion para hacer el reporte de productos vendidos
    public void reportar(Producto producto) throws IOException{
        String archivoDestino = "src/main/recursos/Reporte.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDestino,true))){
            bw.write("Producto vendido: " + producto.getNombre() + "\n" +
                    "Codigo: " + producto.getCodigo() + "\n" +
                    "Categoria: " + producto.getCategoria() + "\n" +
                    "Precio: " + producto.getPrecio() + "\n" +
                    "Cantidad vendida: " + producto.getCantidad());
            bw.write("===========================================\n");
        } catch(IOException e){
            System.out.println("Error al escribir en el archivo de ventas: " + e.getMessage());
        }
    }
}
