package excepciones;

/*
Esta clase se encarga de manejar las excepciones del rut de cada clase
correspondiente
*/

public class RutException extends Exception{
    public RutException(){
        super("Rut inválido.");
    }
}
