
public class Producto {
    private String nombre;
    private String codigo;
    private String categoria;
    private int cantidad;
    private int precio;
    
    //Constructor.
    public Producto(){
    }
    public Producto(String nombre, String codigo, String categoria, int precio, int cantidad)
    {
        this.nombre = nombre;
        this.codigo = codigo;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    //Metodos Setters.
    public void setNombre(String nombre){
        if(nombre != null)
            this.nombre = nombre;
    }
    public void setCodigo(String codigo){
        if(codigo.length() == 13)
            this.codigo = codigo;
    }
    public void setCategoria(String categoria){
        if(categoria != null)
            this.categoria = categoria;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public void setPrecio(int precio){
        if(precio >= 0)
            this.precio = precio;
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
