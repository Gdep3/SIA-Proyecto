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

public class Controlador implements ActionListener{
    private Supermercado supermercado;
    private Menu menu;
    private VentanaPrincipal menuMain;
    private VentanaEmpleado menuEmpleado;
    private VentanaCliente menuCliente;
    private VentanaBuscar menuBuscar;
    private VentanaAgregarProducto agregarProducto;
    private VentanaListar ventanaListar;
    private VentanaAgregar menuAgregar;
    
    public void iniciar(){
        supermercado = new Supermercado();
        CsvFileReader archivo = new CsvFileReader(";");
        supermercado = archivo.leerCsv("src/main/recursos/items.csv");
        
        menu = new Menu(supermercado);
        
        menuMain = new VentanaPrincipal();
        
        menuMain.getBotonCliente().addActionListener(this);
        menuMain.getBotonGerente().addActionListener(this);
        menuMain.getBotonSalir().addActionListener(this);
        
        menuMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuMain.setTitle("Menu Inicio");
        menuMain.setLocationRelativeTo(null);
        menuMain.setVisible(true);
    }
    //Se deberian hacer dos controladores para las opciones de menu cliente y otro para de empleado.
    @Override
    public void actionPerformed(ActionEvent ee){
        if(ee.getSource() == menuMain.getBotonCliente()){
            menuCliente = new VentanaCliente();
            
            menuCliente.getBotonListar().addActionListener(this);
            menuCliente.getBotonBuscar().addActionListener(this);
            menuCliente.getBotonComprar().addActionListener(this);
            menuCliente.getBotonVolver().addActionListener(this);
            
            menuCliente.setAlwaysOnTop(true);
            menuCliente.setTitle("Menu Cliente");
            menuCliente.setLocationRelativeTo(null);
            menuCliente.setVisible(true);
            return;
        }
        if(ee.getSource() == menuMain.getBotonGerente()){
            menuEmpleado = new VentanaEmpleado();
            
            menuEmpleado.getBotonAgregar().addActionListener(this);
            menuEmpleado.getBotonEliminar().addActionListener(this);
            menuEmpleado.getBotonListar().addActionListener(this);
            menuEmpleado.getBotonReporte().addActionListener(this);
            menuEmpleado.getBotonVolver().addActionListener(this);
            
            menuEmpleado.setAlwaysOnTop(true);
            menuEmpleado.setTitle("Menu Empleado");
            menuEmpleado.setLocationRelativeTo(null);
            menuEmpleado.setVisible(true);
            return;
        }
        if(ee.getSource() == menuMain.getBotonSalir()){
            menuMain.dispose();
            return;
        }
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
            menuBuscar.setVisible(true);
            return;
        }
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonComprar()){
            return;
        }
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonListar()){
            ventanaListar = new VentanaListar(menu.listaProductos());
            
            ventanaListar.getBotonVolverVentanaListar().addActionListener(this);
            
            ventanaListar.setAlwaysOnTop(true);
            ventanaListar.setTitle("Listar Productos");
            ventanaListar.setLocationRelativeTo(null);
            ventanaListar.setVisible(true);
            return;
        }
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonVolver()){
            menuCliente.dispose();
            return;
        }
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonAgregar()){
            menuAgregar = new VentanaAgregar();
            
            menuAgregar.getBotonAgregarPasillo().addActionListener(this);
            menuAgregar.getBotonAgregarProducto().addActionListener(this);
            menuAgregar.getBotonVolverMenuAgregar().addActionListener(this);
            
            menuAgregar.setAlwaysOnTop(true);
            menuAgregar.setTitle("Menu Agregar");
            menuAgregar.setLocationRelativeTo(null);
            menuAgregar.setVisible(true);
            return;
        }
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonEliminar()){
            return;
        }
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonListar()){
            ventanaListar = new VentanaListar(menu.listaProductos());
            
            ventanaListar.getBotonVolverVentanaListar().addActionListener(this);
            
            ventanaListar.setAlwaysOnTop(true);
            ventanaListar.setTitle("Listar Productos");
            ventanaListar.setLocationRelativeTo(null);
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
        if(agregarProducto != null && ee.getSource() == agregarProducto.getBotonAceptarVentanaAgregarProducto()){
            Producto producto1 = new Producto();
           
            try{
               producto1.setNombre(agregarProducto.getCampoNombre().getText());
            }catch(NameException e){
                System.out.println("Nombre invalido.");
            }catch(Exception e){
               System.out.println("Error al ingresar el nombre.");
            }
            
            try{
                producto1.setCodigo(agregarProducto.getCampoCodigo().getText());
            }catch(CodeException e){
                System.out.println("Codigo Invalido.");
            }catch(Exception e){
                System.out.println("Error al ingresar el codigo.");
            }
            
            try{
                producto1.setCategoria(agregarProducto.getCampoCategoria().getText());
            }catch(CategoryException e){
                System.out.println("Categoria invalida.");
            }catch(Exception e){
                System.out.println("Error al ingresar la categoria.");
            }
            
            try{
                producto1.setPrecio(Integer.parseInt(agregarProducto.getCampoPrecio().getText()));
            }catch(NumberException e){
                System.out.println("Precio invalido.");
            }catch(Exception e){
                System.out.println("Error al ingresar el precio.");
            }
            
            try{
                producto1.setCantidad(Integer.parseInt(agregarProducto.getCampoCantidad().getText()));
            }catch(NumberException e){
                System.out.println("Cantidad invalida.");
            }catch(Exception e){
                System.out.println("Error al ingresar la cantidad.");
            }

            menu.añadirProducto(producto1);
            agregarProducto.dispose();
            try {
                supermercado.guardarEnCsv("src/main/recursos/items.csv",producto1);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        if(agregarProducto != null && ee.getSource() == agregarProducto.getBotonVolverVentanaAgregarProducto()){
            agregarProducto.dispose();
            return;
        }
        if(menuAgregar != null && ee.getSource() == menuAgregar.getBotonAgregarProducto()){
            agregarProducto = new VentanaAgregarProducto();
            
            agregarProducto.getBotonAceptarVentanaAgregarProducto().addActionListener(this);
            agregarProducto.getBotonVolverVentanaAgregarProducto().addActionListener(this);
            
            agregarProducto.setAlwaysOnTop(true);
            agregarProducto.setTitle("Agregar Producto");
            agregarProducto.setLocationRelativeTo(null);
            agregarProducto.setVisible(true);
        }
        if(menuAgregar != null && ee.getSource() == menuAgregar.getBotonAgregarPasillo()){
            return;
        }
        if(menuAgregar != null && ee.getSource() == menuAgregar.getBotonVolverMenuAgregar()){
            menuAgregar.dispose();
            return;
        }
        if(ventanaListar != null && ee.getSource() == ventanaListar.getBotonVolverVentanaListar()){
            ventanaListar.dispose();
        }
    }
}
