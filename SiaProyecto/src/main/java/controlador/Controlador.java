package controlador;

import clases.*;
import excepciones.*;
import java.awt.event.ActionEvent;
import ventanas.*;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
Esta clase se encarga de coecionar las funciones del programa con las ventanas
manejando distintos errores que puedan suceder, tambien de ultilizando distintos
metodos, tanto de otras clases como propios. Ultiliza libreirias para realizar
distintas tareas para las ventanas.
*/

public class Controlador implements ActionListener{
    private Supermercado supermercado;
    private Menu menu;
    private VentanaPrincipal menuMain;
    private VentanaEmpleado menuEmpleado;
    private VentanaCliente menuCliente;
    private VentanaBuscar menuBuscar;
    private VentanaListar_Modificar_Eliminar ventanaListar;
    private VentanaAgregar menuAgregar;
    
    //funcion para iniciar el programa 
    public void iniciar(){
        supermercado = new Supermercado();
        CsvFileReader archivo = new CsvFileReader(";");
        supermercado = archivo.leerCsv("src/main/recursos/datosSupermercado.csv");
        
        menu = new Menu(supermercado);
        
        menuMain = new VentanaPrincipal();
        
        menuMain.getBotonCliente().addActionListener(this);
        menuMain.getBotonGerente().addActionListener(this);
        
        menuMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuMain.setTitle("Menu Inicio");
        menuMain.setLocationRelativeTo(null);
        menuMain.setResizable(false);
        menuMain.setVisible(true);
    }
    //Se deberian hacer dos controladores para las opciones de menu cliente y otro para de empleado.
    @Override
    public void actionPerformed(ActionEvent ee){
        //Acciones menu principal.
        if(ee.getSource() == menuMain.getBotonCliente()){
            menuCliente = new VentanaCliente();
            
            menuCliente.getBotonListar().addActionListener(this);
            menuCliente.getBotonBuscar().addActionListener(this);
            menuCliente.getBotonComprar().addActionListener(this);
            menuCliente.getBotonVolver().addActionListener(this);
            
            menuCliente.setAlwaysOnTop(true);
            menuCliente.setTitle("Menu Cliente");
            menuCliente.setLocationRelativeTo(null);
            menuCliente.setResizable(false);
            menuCliente.setVisible(true);
            return;
        }
        if(ee.getSource() == menuMain.getBotonGerente()){
            menuEmpleado = new VentanaEmpleado();
            
            menuEmpleado.getBotonAgregar().addActionListener(this);
            menuEmpleado.getBotonListar().addActionListener(this);
            menuEmpleado.getBotonReporte().addActionListener(this);
            menuEmpleado.getBotonVolver().addActionListener(this);
            
            menuEmpleado.setAlwaysOnTop(true);
            menuEmpleado.setTitle("Menu Empleado");
            menuEmpleado.setLocationRelativeTo(null);
            menuEmpleado.setResizable(false);
            menuEmpleado.setVisible(true);
            return;
        }
        //Acciones menu cliente.
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonBuscar()){
            menuBuscar = new VentanaBuscar();
            
            menuBuscar.getBotonBuscarCategoria().addActionListener(this);
            menuBuscar.getBotonBuscarNombre().addActionListener(this);
            menuBuscar.getBotonBuscarPrecio().addActionListener(this);
            menuBuscar.getBotonBuscarOfertas().addActionListener(this);
            menuBuscar.getBotonBuscarCodigo().addActionListener(this);
            menuBuscar.getBotonVolverMenuBuscar().addActionListener(this);
            
            menuBuscar.setAlwaysOnTop(true);
            menuBuscar.setTitle("Menu Busqueda");
            menuBuscar.setLocationRelativeTo(null);
            menuBuscar.setResizable(false);
            menuBuscar.setVisible(true);
            return;
        }
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonComprar()){
            return;
        }
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonListar()){
            ventanaListar = new VentanaListar_Modificar_Eliminar(menu.listaProductos());
            
            ventanaListar.getBotonVolverVentanaListar().addActionListener(this);
            
            ventanaListar.setAlwaysOnTop(true);
            ventanaListar.setTitle("Listar Productos");
            ventanaListar.setLocationRelativeTo(null);
            ventanaListar.setResizable(false);
            ventanaListar.setVisible(true);
            return;
        }
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonVolver()){
            menuCliente.dispose();
            return;
        }
        //Acciones menu empleado.
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonAgregar()){
            menuAgregar = new VentanaAgregar();
            
            menuAgregar.getBotonVolverVentanaAgregarProducto().addActionListener(this);
            menuAgregar.getBotonAceptarVentanaAgregarProducto().addActionListener(this);
            
            menuAgregar.setAlwaysOnTop(true);
            menuAgregar.setTitle("Menu Agregar");
            menuAgregar.setLocationRelativeTo(null);
            menuAgregar.setResizable(false);
            menuAgregar.setVisible(true);
            return;
        }
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonListar()){
            ventanaListar = new VentanaListar_Modificar_Eliminar(menu.listaProductos());
            
            ventanaListar.getBotonVolverVentanaListar().addActionListener(this);
            ventanaListar.getBotonEliminarVentanaListar().addActionListener(this);
            ventanaListar.getBotonModificarVentanaListar().addActionListener(this);
            
            ventanaListar.setAlwaysOnTop(true);
            ventanaListar.setTitle("Listar Productos");
            ventanaListar.setLocationRelativeTo(null);
            ventanaListar.setResizable(false);
            ventanaListar.setVisible(true);
            return;
        }
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonReporte()){
            return;
        }
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonVolver()){
            menuEmpleado.dispose();
            return;
        }
        //Acciones menu agregar producto.
        if(menuAgregar != null && ee.getSource() == menuAgregar.getBotonAceptarVentanaAgregarProducto()){
            Producto producto1 = new Producto();
           
            //Agregar nombre a producto nuevo.
            try{
               producto1.setNombre(menuAgregar.getCampoNombre().getText());
            }catch(NameException e){
                JOptionPane.showMessageDialog(menuAgregar, "Campo en blanco.\nPor favor ingresar un nombre.", "Error al ingresar el nombre", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(Exception e){
               JOptionPane.showMessageDialog(menuAgregar, "Nombre Invalido.\nPor favor ingresar un nombre valido,", "Error al ingresar el nombre", JOptionPane.ERROR_MESSAGE);
               return;
            }
            //Agregar codigo a producto nuevo.
            try{
                producto1.setCodigo(menuAgregar.getCampoCodigo().getText());
            }catch(CodeException e){
                JOptionPane.showMessageDialog(menuAgregar, "Codigo Invalido.\nDebe contener 12 caracteres", "Error al ingresar el codigo", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(Exception e){
                JOptionPane.showMessageDialog(menuAgregar, "Error al ingresar el codigo.\nIntente nuevamente.", "Error al ingresar el codigo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Agregar categoria a producto nuevo.
            try{
                producto1.setCategoria(menuAgregar.getCampoCategoria().getText());                
                System.out.println(menuAgregar.getCampoCategoria().getText());
            }catch(CategoryException e){
                JOptionPane.showMessageDialog(menuAgregar, "Campo en blanco.\nPor favor ingresar una categoria.", "Error al ingresar la categoria", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(Exception e){
                JOptionPane.showMessageDialog(menuAgregar, "Error al ingresar la categoria.\nIntente nuevamente.", "Error al ingresar la categoria", JOptionPane.ERROR_MESSAGE);
               return;
            }
            //Agregar precio a producto nuevo.
            try{

                producto1.setPrecio(Integer.parseInt(menuAgregar.getCampoPrecio().getText()));
                producto1.setPrecio(Integer.parseInt(menuAgregar.getCampoPrecio().getText()));
            }catch(NumberException e){
                JOptionPane.showMessageDialog(menuAgregar, "Precio invalido.\nIngrese un número entero positivo.", "Error al ingresar el precio", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(menuAgregar, "Error al ingresa el precio.\nIntente nuevamente.", "Error al ingresar el precio", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Agregar cantidad a producto nuevo.
            try{
                producto1.setCantidad(Integer.parseInt(menuAgregar.getCampoCantidad().getText()));
            }catch(NumberException e){
                JOptionPane.showMessageDialog(menuAgregar, "Cantidad invalida.\nIngrese un número entero positivo.", "Error al ingresar la cantidad", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(menuAgregar, "Error al ingresa la cantidad.\nIntente nuevamente.", "Error al ingresar la cantidad", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Agregar pasillo nuevo, si el usuario asi lo quiere.
            if(supermercado.buscarPasillo(menuAgregar.getCampoCategoria().getText()) == null){
                int respuesta = JOptionPane.showConfirmDialog(menuAgregar, "Pasillo ingresado no valido.\n¿Desea crear un pasillo nuevo?", "Error al ingresar el producto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(respuesta == JOptionPane.YES_OPTION)
                    supermercado.agregarPasillo(menuAgregar.getCampoCategoria().getText());
            }
            //Agregar producto a un pasillo.       
            try{
                menu.añadirProducto(producto1);
            }catch(Exception e){
                JOptionPane.showMessageDialog(menuAgregar, "Error al añadir producto.\nIntente nuevamente.", "Error al ingresar el producto", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Cerrar ventana.
            menuAgregar.dispose();
            return;
        }
        if(menuAgregar != null && ee.getSource() == menuAgregar.getBotonVolverVentanaAgregarProducto()){
            menuAgregar.dispose();
            return;
        }
        //Acciones ventana listar/modificar/eliminar.
        if(ventanaListar != null && ee.getSource() == ventanaListar.getBotonVolverVentanaListar()){
            ventanaListar.dispose();
        }
    }
}
