package controlador;

import clases.*;
import excepciones.*;
import java.awt.event.ActionEvent;
import ventanas.*;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    private VentanaListar_Modificar_Eliminar ventanaListar;
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
        pasillo1.agregarProducto("Calamar", "376216680309", "Congelados", 1200, 12);
        pasillo1.agregarProducto("Nuggets de pollo", "237924710845", "Congelados", 500, 30);
        pasillo1.agregarProducto("Reineta", "557377550295", "Congelados", 2000, 25);
        pasillo1.agregarProducto("Chuleta de cerdo", "294327564746", "Congelados", 2500, 40);
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
        menuMain.setSize(500, 400);
        menuMain.setResizable(false);
        menuMain.setLocationRelativeTo(null);
        menuMain.setVisible(true);
    }
    //Se deberian hacer dos controladores para las opciones de menu cliente y otro para de empleado.
    @Override
    public void actionPerformed(ActionEvent ee){
        //Acciones menu principal.
        if(ee.getSource() == menuMain.getBotonCliente()){
            menuCliente = new VentanaCliente();

            menuCliente.getBotonVolver().addActionListener(this);
            
            menuCliente.setAlwaysOnTop(true);
            menuCliente.setTitle("Menu Cliente");
            menuCliente.setSize(500, 400);
            menuCliente.setResizable(false);
            menuCliente.setLocationRelativeTo(null);
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
            menuEmpleado.setSize(500, 400);
            menuEmpleado.setResizable(false);
            menuEmpleado.setLocationRelativeTo(null);
            menuEmpleado.setVisible(true);
            return;
        }
        //Acciones menu cliente.
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
            menuAgregar.setSize(500, 400);
            menuAgregar.setResizable(false);
            menuAgregar.setLocationRelativeTo(null);
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
            ventanaListar.setSize(500, 400);
            ventanaListar.setResizable(false);
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
                producto1.setPrecio(Double.parseDouble(menuAgregar.getCampoPrecio().getText()));
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
            }catch(CorridorException e){
                JOptionPane.showMessageDialog(menuAgregar, "Pasillo ingresado no valido.\nPor favor ingrese un pasillo valido.", "Error al ingresar el producto", JOptionPane.ERROR_MESSAGE);
                return;
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
            return; 
        }
        if(ventanaListar != null && ee.getSource() == ventanaListar.getBotonEliminarVentanaListar()){
            DefaultTableModel model = (DefaultTableModel) (ventanaListar.getListTable()).getModel();

            if((ventanaListar.getListTable()).getSelectedRowCount() == 1){
                //Se obtiene el nombre del producto en esa columna.
                String nombre = (ventanaListar.getListTable()).getValueAt(ventanaListar.getListTable().getSelectedRow(), 1).toString().trim();
                //Se muestra una ventana preguntando si el usuario de verdad quiere eliminar ese producto.
                int respuesta = JOptionPane.showConfirmDialog(ventanaListar, "Esta seguro de que quiere eliminar este producto?\nEsta operacion es permanente.", "Eliminando producto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                //Se comprueba que esta sea igual a si
                if(respuesta == JOptionPane.YES_OPTION)
                {
                    //Se elimina de la tabla.
                    model.removeRow((ventanaListar.getListTable()).getSelectedRow());
                    //Se elimina del supermercado.
                    menu.eliminarProducto(nombre);
                }else
                {
                    //Mensaje de que no se elimino el producto.
                    JOptionPane.showMessageDialog(ventanaListar, "Producto no fue eliminado.", "Cancelando", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                //Se revisa que se haya eliminado del supermercado.
                if(menu.buscarProducto(nombre) == false)
                    //Mensaje de que se elimino correctamente.
                    JOptionPane.showMessageDialog(ventanaListar, "Producto eliminado correctamente.", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                else
                    //Mesansaje de que no se pudo concretar la eliminacion del producto.
                    JOptionPane.showMessageDialog(ventanaListar,"Error, el producto no fue eliminado correctamente.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
            }else
            {
                //Si el usuario no tiene seleccionada ninguna columna.
                if((ventanaListar.getListTable()).getSelectedRowCount() == 0)
                    JOptionPane.showMessageDialog(ventanaListar, "Error, por favor seleccione solo una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
                //Si tiene seleccionada más de una columna.
                else
                    JOptionPane.showMessageDialog(ventanaListar, "Error, seleccione una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
            }
            return; 
        }        
        if(ventanaListar != null && ee.getSource() == ventanaListar.getBotonModificarVentanaListar()){
            DefaultTableModel model = (DefaultTableModel) (ventanaListar.getListTable()).getModel();
            
            if((ventanaListar.getListTable()).getSelectedRowCount() == 1){
                String categoria = (ventanaListar.getListTable()).getValueAt(ventanaListar.getListTable().getSelectedRow(), 2).toString().trim();
            
                String[] opciones = {"Nombre", "Categoria", "Precio", "Cantidad", "Todos"};
                String opcion = JOptionPane.showInputDialog(ventanaListar, "Qué desea cambiar?", "Opciones cambiar",JOptionPane.INFORMATION_MESSAGE, null , opciones, "").toString();

                if(opcion.equalsIgnoreCase("Nombre")){
                    String nuevoNombre = JOptionPane.showInputDialog(ventanaListar,"Ingrese el nuevo nombre", "Cambio de nombre", JOptionPane.INFORMATION_MESSAGE);
                    Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                    try{
                        Producto producto1 = pasillo1.buscarProducto((ventanaListar.getListTable()).getValueAt(ventanaListar.getListTable().getSelectedRow(), 1).toString().trim());
                        pasillo1.cambiarNombre(producto1, nuevoNombre);
                        
                        ventanaListar.getListTable().setValueAt(nuevoNombre,ventanaListar.getListTable().getSelectedRow(), 1);
                    }catch(NameException e){
                        JOptionPane.showMessageDialog(ventanaListar, "Nombre invalido.\nIngrese nuevamente.", "Error nombre", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }else if(opcion.equalsIgnoreCase("Categoria")){
                    String nuevaCategoria = JOptionPane.showInputDialog(ventanaListar, "Ingrese la nueva categoria", "Cambio categoria", JOptionPane.INFORMATION_MESSAGE).trim();
                    Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                    try{
                        System.out.println(nuevaCategoria);
                        if(supermercado.buscarPasillo(nuevaCategoria) != null)
                        {
                            Producto producto1 = pasillo1.buscarProducto((ventanaListar.getListTable()).getValueAt(ventanaListar.getListTable().getSelectedRow(), 1).toString().trim());
                            pasillo1.cambiarCategoria(producto1, nuevaCategoria);
                        }else{
                            int respuesta = JOptionPane.showConfirmDialog(ventanaListar, "Pasillo ingresado no valido.\n¿Desea crear un pasillo nuevo?", "Error al ingresar categoria", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                             if(respuesta == JOptionPane.YES_OPTION){
                                Producto producto1 = pasillo1.buscarProducto((ventanaListar.getListTable()).getValueAt(ventanaListar.getListTable().getSelectedRow(), 1).toString().trim()); 
                                menu.eliminarProducto(producto1.getNombre());
                                supermercado.agregarPasillo(nuevaCategoria);
                                producto1.setCategoria(nuevaCategoria);
                                supermercado.buscarPasillo(nuevaCategoria).agregarProducto(producto1);
                             }
                        }
                        ventanaListar.getListTable().setValueAt(nuevaCategoria,ventanaListar.getListTable().getSelectedRow(), 2);
                    }catch(CategoryException e){
                        JOptionPane.showMessageDialog(ventanaListar, "Categoria invalida.\nIngrese nuevamente.", "Error categoria", JOptionPane.ERROR_MESSAGE);
                    }
                }else if(opcion.equalsIgnoreCase("Precio")){
                    String nuevoPrecio = JOptionPane.showInputDialog(ventanaListar, "Ingrese nuevo precio", "Cambio precio", JOptionPane.INFORMATION_MESSAGE);
                    Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                    try{
                        Producto producto1 = pasillo1.buscarProducto((ventanaListar.getListTable()).getValueAt(ventanaListar.getListTable().getSelectedRow(), 1).toString().trim());
                        pasillo1.cambiarPrecio(producto1, nuevoPrecio);
                        
                        ventanaListar.getListTable().setValueAt(nuevoPrecio,ventanaListar.getListTable().getSelectedRow(), 4); 
                    }catch(NumberException e){
                        JOptionPane.showMessageDialog(ventanaListar, "Precio invalido.\nIngrese nuevamente.", "Error Precio", JOptionPane.ERROR_MESSAGE);
                    }
                }else if(opcion.equalsIgnoreCase("Cantidad")){
                    String nuevaCantidad = JOptionPane.showInputDialog(ventanaListar, "Ingrese nueva cantidad", "Cambio cantidad", JOptionPane.INFORMATION_MESSAGE);
                    Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                    try{
                        Producto producto1 = pasillo1.buscarProducto((ventanaListar.getListTable()).getValueAt(ventanaListar.getListTable().getSelectedRow(), 1).toString().trim());
                        pasillo1.cambiarCantidad(producto1, nuevaCantidad);
                        
                        ventanaListar.getListTable().setValueAt(nuevaCantidad,ventanaListar.getListTable().getSelectedRow(), 3);
                    }catch(NumberException e){
                        JOptionPane.showMessageDialog(ventanaListar, "Cantidad invalida.\nIngrese nuevamente.", "Error Cantidad", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    String nuevoNombre = JOptionPane.showInputDialog(ventanaListar,"Ingrese el nuevo nombre", "Cambio de nombre", JOptionPane.INFORMATION_MESSAGE);
                    String nuevaCategoria = JOptionPane.showInputDialog(ventanaListar, "Ingrese la nueva categoria", "Cambio categoria", JOptionPane.INFORMATION_MESSAGE);
                    String nuevoPrecio = JOptionPane.showInputDialog(ventanaListar, "Ingrese nuevo precio", "Cambio precio", JOptionPane.INFORMATION_MESSAGE);
                    String nuevaCantidad = JOptionPane.showInputDialog(ventanaListar, "Ingrese nueva cantidad", "Cambio cantidad", JOptionPane.INFORMATION_MESSAGE);
                
                    Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                    try{
                        Producto producto1 = pasillo1.buscarProducto((ventanaListar.getListTable()).getValueAt(ventanaListar.getListTable().getSelectedRow(), 1).toString().trim());
                        pasillo1.cambiarNombre(producto1, nuevoNombre);
                        try{
                        //!!!!!Se debe cambiar el pasillo al que pertenece el producto!!!!!
                        System.out.println(nuevaCategoria);
                        if(supermercado.buscarPasillo(nuevaCategoria) != null)
                        {
                            pasillo1.cambiarCategoria(producto1, nuevaCategoria);
                        }else{
                            int respuesta = JOptionPane.showConfirmDialog(ventanaListar, "Pasillo ingresado no valido.\n¿Desea crear un pasillo nuevo?", "Error al ingresar categoria", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                             if(respuesta == JOptionPane.YES_OPTION){
                                menu.eliminarProducto(producto1.getNombre());
                                supermercado.agregarPasillo(nuevaCategoria);
                                producto1.setCategoria(nuevaCategoria);
                                supermercado.buscarPasillo(nuevaCategoria).agregarProducto(producto1);
                             }
                        }
                        ventanaListar.getListTable().setValueAt(nuevaCategoria,ventanaListar.getListTable().getSelectedRow(), 2);
                        pasillo1.cambiarPrecio(producto1, nuevoPrecio);
                        pasillo1.cambiarCantidad(producto1, nuevaCantidad);  
                        }catch(CategoryException e){
                            JOptionPane.showMessageDialog(ventanaListar, "Categoria invalida.\nIngrese nuevamente.", "Error categoria", JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(NameException e){
                        JOptionPane.showMessageDialog(ventanaListar, "Nombre invalido.\nIngrese nuevamente.", "Error nombre", JOptionPane.ERROR_MESSAGE);
                    }catch(NumberException e){
                        JOptionPane.showMessageDialog(ventanaListar, "Precio/Cantidad invalido/a.\nIngrese nuevamente.", "Error Precio/Cantidad", JOptionPane.ERROR_MESSAGE);
                    }
                    ventanaListar.getListTable().setValueAt(nuevoNombre,ventanaListar.getListTable().getSelectedRow(), 1);
                    ventanaListar.getListTable().setValueAt(nuevaCategoria,ventanaListar.getListTable().getSelectedRow(), 2);
                    ventanaListar.getListTable().setValueAt(nuevoPrecio,ventanaListar.getListTable().getSelectedRow(), 4);
                    ventanaListar.getListTable().setValueAt(nuevaCantidad,ventanaListar.getListTable().getSelectedRow(), 3);
                }
            }
            return; 
        }
    }
}