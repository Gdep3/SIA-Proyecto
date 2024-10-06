package excepciones;

/*
Esta clase se encarga de manejar las excepciones del nombre del producto
*/

public class NameException extends Exception{
    public NameException(String error){
        super(error);
    }
}
