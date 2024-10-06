package excepciones;

/*
manejo de excepciones para correo
*/

public class MailException extends Exception{
    public MailException(){
        super("Correo inválido");
    }
}
