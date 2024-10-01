package excepciones;

/*
Esta clase se encarga de manejar las excepciones que pertencen a los numeros
*/

public class NumberException extends Exception{
    public NumberException(String error){
        super(error);
    }
}
