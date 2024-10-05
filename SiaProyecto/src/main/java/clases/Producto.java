package clases;


import excepciones.CodeException;
import excepciones.NameException;
import excepciones.NumberException;
import excepciones.CategoryException;


/**
 * 
 * @author isica
 */

public class Producto {
    private String nombre;
    private String codigo;
    private String categoria;
    private int cantidad = -1;
    private double precio = -1;
    
    //Constructor.
    public Producto(){
    }
    public Producto(String nombre, String codigo, String categoria, double precio, int cantidad)
    {
        this.nombre = nombre;
        this.codigo = codigo;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public String obtenerStringAtributos() {
       return codigo+", "+nombre+", "+categoria+ ", "+cantidad + ", " + precio + "\n";
    }
    public String obtenerNombrePrecio(){
        return nombre + ", " + precio + "\n";
    }
    //Metodos Setters.
    public void setNombre(String nombre) throws NameException{
        if(nombre != null && !nombre.equals(""))
            this.nombre = nombre;
        else
            throw new NameException("Nombre no válido");
    }
    public void setCodigo(String codigo) throws CodeException{
        if(codigo.length() == 12)
            this.codigo = codigo;
        else
            throw new CodeException("Largo del codigo no valido.");
    }
    public void setCategoria(String categoria) throws CategoryException{
        if(categoria != null && !categoria.isEmpty())
            this.categoria = categoria;
        else
            throw new CategoryException("Categoria no valida.");
    }
    public void setCantidad(int cantidad)throws NumberException{
        if(cantidad >= 0)
            this.cantidad = cantidad;
        else
            throw new NumberException("Precio no valido.");
    }
    public void setPrecio(double precio) throws NumberException{
        if(precio >= 0)
            this.precio = precio;
        else
            throw new NumberException("Precio no valido.");
    }
    
    //Metodos Getters.
    public String getNombre(){
        return nombre;
    }
    public String getCodigo(){
        return codigo;
    }
    public String getCategoria(){
        return categoria;
    }
    public int getCantidad(){
        return cantidad;
    }
    public double getPrecio(){
        return precio;
    }        
}
