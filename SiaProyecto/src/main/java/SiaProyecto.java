import java.io.*;

public class SiaProyecto{
    static Supermercado supermercado;
    
    public static void main(String[] args) throws IOException{
        supermercado = new Supermercado();
        
        menuPrincipal();
    }
    public static void textoMenu(){
        System.out.println("1. Llenar datos");

        System.out.println("2. Cambiar datos");

        System.out.println("3. Mostrar productos");
        
        System.out.println("4. Eliminar productos");
        
        System.out.println("Presione 'x' para salir");
    }
    public static void menuPrincipal()throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        
        while(true){
            
            CsvFileReader archivo = new CsvFileReader(";");
            supermercado = archivo.leerCsv("src/main/recursos/items.csv");
            
            textoMenu();
            
            String entrada = lector.readLine();
            if(entrada.equals("x")) break;
            
            switch(entrada){
                case "1":
                    añadirDatosPorConsola();
                    //supermercado.guardarEnCsv("src/main/recursos/items.csv");
                    break;
                case "2":
                    System.out.println("Función no implementada aún.");
                    System.out.println("Intente más tarde.");
                    break;
                case "3":
                    supermercado.listarPasillos();
                    break;
                case "4":
                    System.out.println("Función no implementada aún.");
                    System.out.println("Intente más tarde.");
                    break;
            }
        }
    }
    public static void añadirDatosPorConsola(){
        
    }
    public static void añadirDatosPorArchivo(){
        
    }
}
