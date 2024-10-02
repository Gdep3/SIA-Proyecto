package controlador;

import clases.*;
import excepciones.*;
import java.awt.event.ActionEvent;
import ventanas.*;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
        pasillo1.agregarProducto("Leche", "123456789123", "Lacteos", 910, 12);
        pasillo1.agregarProducto("Yogurt", "874367313888", "Lacteos", 800, 10);
        pasillo1.agregarProducto("Queso Mozzarella", "787900343436", "Lacteos", 1000, 9);
        pasillo1.agregarProducto("Leche desnatada", "485735285033", "Lacteos", 940, 30);
        supermercado.agregarPasillo(pasillo1);
        
        pasillo1 = new Pasillo("Liquidos");
        pasillo1.agregarProducto("Coca-cola", "189841362605", "Liquidos", 500, 12);
        pasillo1.agregarProducto("Jugo de naranja", "450502605552", "Liquidos", 450, 10);
        pasillo1.agregarProducto("Jugo de durazno", "007696414537", "Liquidos", 500, 13);
        pasillo1.agregarProducto("Pepsi", "767085689872", "Liquidos", 450, 10);
        supermercado.agregarPasillo(pasillo1);

        pasillo1 = new Pasillo("Congelados");
        pasillo1.agregarProducto("Calamar", "376216680309", "Congelador", 1200, 12);
        pasillo1.agregarProducto("Nuggets de pollo", "237924710845", "Congelador", 500, 30);
        pasillo1.agregarProducto("Reineta", "557377550295", "Congelador", 2000, 25);
        pasillo1.agregarProducto("Chuleta de cerdo", "294327564746", "Congelador", 2500, 40);
        supermercado.agregarPasillo(pasillo1);

        pasillo1 = new Pasillo("Cuidado personal");
        pasillo1.agregarProducto("Shampoo", "805501899660", "Cuidado personal", 1250, 20);
        pasillo1.agregarProducto("Jabón", "748506753912", "Cuidado personal", 1200, 30);
        pasillo1.agregarProducto("Perfume", "427309492941", "Cuidado personal", 2500, 25);
        pasillo1.agregarProducto("Pasta de dientes", "084943503992", "Cuidado personal", 1000, 40);
        supermercado.agregarPasillo(pasillo1);
        
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
            menuEmpleado.getBotonEliminar().addActionListener(this);
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
            ventanaListar = new VentanaListar(menu.listaProductos());
            
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
            
            menuAgregar.getBotonAgregarPasillo().addActionListener(this);
            menuAgregar.getBotonAgregarProducto().addActionListener(this);
            menuAgregar.getBotonVolverMenuAgregar().addActionListener(this);
            
            menuAgregar.setAlwaysOnTop(true);
            menuAgregar.setTitle("Menu Agregar");
            menuAgregar.setLocationRelativeTo(null);
            menuAgregar.setResizable(false);
            menuAgregar.setVisible(true);
            return;
        }
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonEliminar()){
            return;
        }
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonListar()){
            ventanaListar = new VentanaListar(menu.listaProductos());
            
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
        if(agregarProducto != null && ee.getSource() == agregarProducto.getBotonAceptarVentanaAgregarProducto()){
            Producto producto1 = new Producto();
           
            //Agregar nombre a producto nuevo.
            try{
               producto1.setNombre(agregarProducto.getCampoNombre().getText());
            }catch(NameException e){
                JOptionPane.showMessageDialog(agregarProducto, "Campo en blanco.\nPor favor ingresar un nombre.", "Error al ingresar el nombre", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(Exception e){
               JOptionPane.showMessageDialog(agregarProducto, "Nombre Invalido.\nPor favor ingresar un nombre valido,", "Error al ingresar el nombre", JOptionPane.ERROR_MESSAGE);
               return;
            }
            //Agregar codigo a producto nuevo.
            try{
                producto1.setCodigo(agregarProducto.getCampoCodigo().getText());
            }catch(CodeException e){
                JOptionPane.showMessageDialog(agregarProducto, "Codigo Invalido.\nDebe contener 12 caracteres", "Error al ingresar el codigo", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(Exception e){
                JOptionPane.showMessageDialog(agregarProducto, "Error al ingresar el codigo.\nIntente nuevamente.", "Error al ingresar el codigo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Agregar categoria a producto nuevo.
            try{
                producto1.setCategoria(agregarProducto.getCampoCategoria().getText());                
                System.out.println(agregarProducto.getCampoCategoria().getText());
            }catch(CategoryException e){
                JOptionPane.showMessageDialog(agregarProducto, "Campo en blanco.\nPor favor ingresar una categoria.", "Error al ingresar la categoria", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(Exception e){
                JOptionPane.showMessageDialog(agregarProducto, "Error al ingresar la categoria.\nIntente nuevamente.", "Error al ingresar la categoria", JOptionPane.ERROR_MESSAGE);
               return;
            }
            //Agregar precio a producto nuevo.
            try{
                producto1.setPrecio(Double.parseDouble(agregarProducto.getCampoPrecio().getText()));
            }catch(NumberException e){
                JOptionPane.showMessageDialog(agregarProducto, "Precio invalido.\nIngrese un número entero positivo.", "Error al ingresar el precio", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(Exception e){
                JOptionPane.showMessageDialog(agregarProducto, "Error al ingresa el precio.\nIntente nuevamente.", "Error al ingresar el precio", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Agregar cantidad a producto nuevo.
            try{
                producto1.setCantidad(Integer.parseInt(agregarProducto.getCampoCantidad().getText()));
            }catch(NumberException e){
                JOptionPane.showMessageDialog(agregarProducto, "Cantidad invalida.\nIngrese un número entero positivo.", "Error al ingresar la cantidad", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(Exception e){
                JOptionPane.showMessageDialog(agregarProducto, "Error al ingresa la cantidad.\nIntente nuevamente.", "Error al ingresar la cantidad", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Agregar pasillo nuevo, si el usuario asi lo quiere.
            if(supermercado.buscarPasillo(agregarProducto.getCampoCategoria().getText()) == null){
                int respuesta = JOptionPane.showConfirmDialog(agregarProducto, "Pasillo ingresado no valido.\n¿Desea crear un pasillo nuevo?", "Error al ingresar el producto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(respuesta == JOptionPane.YES_OPTION)
                    supermercado.agregarPasillo(agregarProducto.getCampoCategoria().getText());
            }
            //Agregar producto a un pasillo.       
            try{
                menu.añadirProducto(producto1);
            }catch(CorridorException e){
                JOptionPane.showMessageDialog(agregarProducto, "Pasillo ingresado no valido.\nPor favor ingrese un pasillo valido.", "Error al ingresar el producto", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return;
            }catch(Exception e){
                JOptionPane.showMessageDialog(agregarProducto, "Error al añadir producto.\nIntente nuevamente.", "Error al ingresar el producto", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return;
            }
            //Cerrar ventana.
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
            agregarProducto.setResizable(false);
            agregarProducto.setVisible(true);
        }
        if(menuAgregar != null && ee.getSource() == menuAgregar.getBotonAgregarPasillo()){
            return;
        }
        if(menuAgregar != null && ee.getSource() == menuAgregar.getBotonVolverMenuAgregar()){
            menuAgregar.dispose();
            return;
        }
        //Acciones ventana listar/modificar/eliminar.
        if(ventanaListar != null && ee.getSource() == ventanaListar.getBotonVolverVentanaListar()){
            ventanaListar.dispose();
            return; 
        }
        if(ventanaListar != null && ee.getSource() == ventanaListar.getBotonEliminarVentanaListar()){
            DefaultTableModel model = (DefaultTableModel) (ventanaListar.getListTable()).getModel();
            
            if((ventanaListar.getListTable()).getSelectedRowCount() == 1){
                model.removeRow((ventanaListar.getListTable()).getSelectedRow());
            }else
            {
                if((ventanaListar.getListTable()).getSelectedRowCount() == 0)
                    JOptionPane.showMessageDialog(ventanaListar, "Error, por favor seleccione solo una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(ventanaListar, "Error, seleccione una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
            }
            return; 
        }        
        if(ventanaListar != null && ee.getSource() == ventanaListar.getBotonModificarVentanaListar()){
            System.out.println("Modificando.");
            return; 
        }
    }
}
