package clases;

import java.util.*;

public class Cliente extends Usuario{
    
    private ArrayList<Producto> carroCompra;
    public Cliente(String nombre, String rut){
        super(nombre, rut);
    }
    
    @Override
    public void mostrarMenu(){
        System.out.println("Mostrando menu cliente");
    }
}
