package excepciones;


public class RutException extends Exception{
    public RutException(){
        super("Rut de largo no válido.");
    }
}
