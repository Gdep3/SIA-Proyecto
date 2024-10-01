package controlador;

import clases.*;
import excepciones.*;
import java.awt.event.ActionEvent;
import ventanas.*;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
/**
 *
 * @author Isidora Osorio
 */
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

        
        Pasillo pasillo1 = new Pasillo("Lacteos");
        pasillo1.agregarProducto("Leche", "1234567891234", "Lacteos", 910, 12);
        pasillo1.agregarProducto("Yogurt", "8743673138888", "Lacteos", 800, 10);
        pasillo1.agregarProducto("Queso Mozzarella", "7879003434360", "Lacteos", 1000, 9);
        pasillo1.agregarProducto("Leche desnatada", "4857352850339", "Lacteos", 940, 30);
        supermercado.agregarPasillo(pasillo1);
        
        pasillo1 = new Pasillo("Liquidos");
        pasillo1.agregarProducto("Coca-cola", "1898413626054", "Liquidos", 500, 12);
        pasillo1.agregarProducto("Jugo de naranja", "4505026055525", "Liquidos", 450, 10);
        pasillo1.agregarProducto("Jugo de durazno", "0076964145370", "Liquidos", 500, 13);
        pasillo1.agregarProducto("Pepsi", "7670856898723", "Liquidos", 450, 10);
        supermercado.agregarPasillo(pasillo1);

        pasillo1 = new Pasillo("Congelados");
        pasillo1.agregarProducto("Calamar", "3762166803090", "Congelador", 1200, 12);
        pasillo1.agregarProducto("Nuggets de pollo", "2379247108455", "Congelador", 500, 30);
        pasillo1.agregarProducto("Reineta", "5573775502958", "Congelador", 2000, 25);
        pasillo1.agregarProducto("Chuleta de cerdo", "2943275647464", "Congelador", 2500, 40);
        supermercado.agregarPasillo(pasillo1);

        pasillo1 = new Pasillo("Cuidado personal");
        pasillo1.agregarProducto("Shampoo", "8055018996603", "Cuidado personal", 1250, 20);
        pasillo1.agregarProducto("Jabón", "7485067539126", "Cuidado personal", 1200, 30);
        pasillo1.agregarProducto("Perfume", "4273094929419", "Cuidado personal", 2500, 25);
        pasillo1.agregarProducto("Pasta de dientes", "0849435039922", "Cuidado personal", 1000, 40);
        supermercado.agregarPasillo(pasillo1);
        
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
                producto1.setPrecio(Double.parseDouble(agregarProducto.getCampoPrecio().getText()));
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
            ventanaListar.dispose();;
            return; 
        }
    }
}
