import controlador.*;
import java.io.IOException;
/*
Clase main del programa donde se ejecuta todo el funcionamiento
*/

public class SiaProyecto{
    public static void main(String[] args) throws IOException{
        
        //se crea el controlador y se inicia
        Controlador controlador = new Controlador();
        controlador.iniciar();
    }   
}
