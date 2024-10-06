package excepciones;

/*
Esta categoria se encarga de manejar las excepciones de Codigo del producto
*/

public class CodeException extends Exception{
    public CodeException(String error){
        super(error);
    }
}
