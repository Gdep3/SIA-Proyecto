package excepciones;

/*
Esta clase se encarga de manejar las excepciones de las categorias
*/

public class CategoryException extends Exception{
    public CategoryException(String error){
        super(error);
    }
}
