package clases;

import java.util.*;

public class Cliente extends Usuario{
    
    private ArrayList<Producto> carroCompra;
    public Cliente(String nombre, String rut){
        super(nombre, rut);
    }
    
    @Override
    public void mostrarInfo(){
        System.out.println("Usuario: " + getNombre());
        System.out.println("Rut: " + getRut());
        System.out.println("Compras Realizadas: ");
        for (int i = 0; i < carroCompra.size(); i++) {
            Producto item = carroCompra.get(i);
            System.out.println("Producto: " + item.getNombre() + ", Precio: " + item.getPrecio());
        }
    }
    
    //public ArrayList agregarAlCarro(Producto item) {  
    //}
}
