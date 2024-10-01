package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CsvFileReader {
    private String delimiter;

    public CsvFileReader(String delimiter) {
        this.delimiter = delimiter;
    }

    public Supermercado leerCsv(String archivo) {
        Map<String, Pasillo> pasillos_Categoria = new HashMap<>();
        ArrayList<Pasillo> pasillos = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                
                String nombre = values[0];
                String codigo = values[4];
                String categoria = values[1];
                int cantidad = Integer.parseInt(values[2]);
                int precio = Integer.parseInt(values[3]);
                
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
}
