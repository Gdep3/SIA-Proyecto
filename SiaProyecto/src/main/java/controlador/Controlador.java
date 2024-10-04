package controlador;

import clases.Pasillo;
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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*
Esta clase se encarga de coecionar las funciones del programa con las ventanas
manejando distintos errores que puedan suceder, tambien de ultilizando distintos
metodos, tanto de otras clases como propios. Ultiliza libreirias para realizar
distintas tareas para las ventanas.
*/

public class Controlador implements ActionListener{
    private Supermercado supermercado;
    private VentanaPrincipal menuMain;
    private VentanaEmpleado menuEmpleado;
    private VentanaCliente menuCliente;
    private VentanaListar_Modificar_Eliminar ventanaListarModificarEliminar;
    private VentanaAgregar menuAgregar;
    
    //funcion para iniciar el programa 
    public void iniciar(){
        supermercado = new Supermercado();
        CsvFileReader archivo = new CsvFileReader(";");
        supermercado = archivo.leerCsv("src/main/recursos/datosSupermercado.csv");
        
        menuMain = new VentanaPrincipal();
        
        menuMain.getBotonCliente().addActionListener(this);
        menuMain.getBotonEmpleado().addActionListener(this);
        
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
            menuCliente = new VentanaCliente(supermercado.listaDeProductosNombrePrecio());

            menuCliente.getBotonVolverMenuCliente().addActionListener(this);
            menuCliente.getBotonBuscar().addActionListener(this);
            menuCliente.getBotonAceptarMenuCliente().addActionListener(this);
            menuCliente.getBotonCarrito().addActionListener(this);
            
            menuCliente.setAlwaysOnTop(true);
            menuCliente.setTitle("Menu Cliente");
            menuCliente.setSize(500, 400);
            menuCliente.setResizable(false);
            menuCliente.setLocationRelativeTo(null);
            menuCliente.setVisible(true);
            return;
        }
        if(ee.getSource() == menuMain.getBotonEmpleado()){
            menuEmpleado = new VentanaEmpleado();
            
            menuEmpleado.getBotonAgregar().addActionListener(this);
            menuEmpleado.getBotonListar_Modificar_Eliminar().addActionListener(this);
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
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonVolverMenuCliente()){
            menuCliente.dispose();
            return;
        }
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonBuscar()){
            DefaultTableModel model = (DefaultTableModel) (menuCliente.getListaCliente()).getModel();
            TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
            menuCliente.getListaCliente().setRowSorter(obj);

            if(!menuCliente.getBarraBuscarVentanaCliente().getText().equals("")){
                String filtro = menuCliente.getBarraBuscarVentanaCliente().getText().substring(0,1).toUpperCase() + menuCliente.getBarraBuscarVentanaCliente().getText().substring(1);
                obj.setRowFilter(RowFilter.regexFilter(filtro));
            } 
            return;
        }
        
        //Acciones menu empleado.
        //Agregar.
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
            //supermercado.guardarEnCsv(producto);
            return;
        }
        //Listar/Modificar/Eliminar
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonListar_Modificar_Eliminar()){
            ventanaListarModificarEliminar = new VentanaListar_Modificar_Eliminar(supermercado.listaDeProductos());
            
            ventanaListarModificarEliminar.getBotonVolverVentanaListar().addActionListener(this);
            ventanaListarModificarEliminar.getBotonEliminarVentanaListar().addActionListener(this);
            ventanaListarModificarEliminar.getBotonModificarVentanaListar().addActionListener(this);
            ventanaListarModificarEliminar.getBotonBuscarVentanaListar().addActionListener(this);
            
            ventanaListarModificarEliminar.setAlwaysOnTop(true);
            ventanaListarModificarEliminar.setTitle("Listar Productos");
            ventanaListarModificarEliminar.setSize(500, 400);
            ventanaListarModificarEliminar.setResizable(false);
            ventanaListarModificarEliminar.setLocationRelativeTo(null);
            ventanaListarModificarEliminar.setVisible(true);
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
                String categoria = (menuAgregar.getCampoCategoria().getText().substring(0,1).toUpperCase() + menuAgregar.getCampoCategoria().getText().substring(1)).trim();
                producto1.setCategoria(categoria);                
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
            if(supermercado.buscarPasillo(producto1.getCategoria()) == null){
                int respuesta = JOptionPane.showConfirmDialog(menuAgregar, "Pasillo ingresado no valido.\n¿Desea crear un pasillo nuevo?", "Error al ingresar el producto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(respuesta == JOptionPane.YES_OPTION)
                    supermercado.agregarPasillo(producto1.getCategoria());
            }
            //Agregar producto a un pasillo.       
            try{
                supermercado.añadirProductoASupermercado(producto1);
            }catch(CorridorException e){
                JOptionPane.showMessageDialog(menuAgregar, "Pasillo ingresado no valido.\nPor favor ingrese un pasillo valido.", "Error al ingresar el producto", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(Exception e){
                JOptionPane.showMessageDialog(menuAgregar, "Error al añadir producto.\nIntente nuevamente.", "Error al ingresar el producto", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                supermercado.guardarEnCsv(producto1);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
        //Volver a ventana anterior.
        if(ventanaListarModificarEliminar != null && ee.getSource() == ventanaListarModificarEliminar.getBotonVolverVentanaListar()){
            ventanaListarModificarEliminar.dispose();
            return; 
        }
        //Eliminar producto.
        if(ventanaListarModificarEliminar != null && ee.getSource() == ventanaListarModificarEliminar.getBotonEliminarVentanaListar()){
            DefaultTableModel model = (DefaultTableModel) (ventanaListarModificarEliminar.getListTable()).getModel();

            if((ventanaListarModificarEliminar.getListTable()).getSelectedRowCount() == 1){
                //Se obtiene el nombre del producto en esa columna.
                String nombre = (ventanaListarModificarEliminar.getListTable()).getValueAt(ventanaListarModificarEliminar.getListTable().getSelectedRow(), 1).toString().trim();
                //Se muestra una ventana preguntando si el usuario de verdad quiere eliminar ese producto.
                int respuesta = JOptionPane.showConfirmDialog(ventanaListarModificarEliminar, "Esta seguro de que quiere eliminar este producto?\nEsta operacion es permanente.", "Eliminando producto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                //Se comprueba que esta sea igual a si
                if(respuesta == JOptionPane.YES_OPTION){     
                    //Se elimina del supermercado.
                    supermercado.eliminarProductoDelSupermercado(nombre);
                    supermercado.eliminarProductoDeCsv(nombre);
                    //Se elimina de la tabla.
                    model.removeRow(ventanaListarModificarEliminar.getListTable().convertRowIndexToModel((ventanaListarModificarEliminar.getListTable().getSelectedRow())));

                }else{
                    //Mensaje de que no se elimino el producto.
                    JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Producto no fue eliminado.", "Cancelando", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                //Se revisa que se haya eliminado del supermercado.
                if(supermercado.buscarProductoEnSupermercado(nombre) == false)
                    //Mensaje de que se elimino correctamente.
                    JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Producto eliminado correctamente.", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                else
                    //Mesansaje de que no se pudo concretar la eliminacion del producto.
                    JOptionPane.showMessageDialog(ventanaListarModificarEliminar,"Error, el producto no fue eliminado correctamente.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
            }else
            {
                //Si el usuario no tiene seleccionada ninguna columna.
                if((ventanaListarModificarEliminar.getListTable()).getSelectedRowCount() == 0)
                    JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Error, por favor seleccione solo una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
                //Si tiene seleccionada más de una columna.
                else
                    JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Error, seleccione una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
            }
            return; 
        }
        //Modificar producto.
        if(ventanaListarModificarEliminar != null && ee.getSource() == ventanaListarModificarEliminar.getBotonModificarVentanaListar()){
            DefaultTableModel model = (DefaultTableModel) (ventanaListarModificarEliminar.getListTable()).getModel();
            
            if((ventanaListarModificarEliminar.getListTable()).getSelectedRowCount() == 1){
                String categoria = (ventanaListarModificarEliminar.getListTable()).getValueAt(ventanaListarModificarEliminar.getListTable().getSelectedRow(), 2).toString().trim();
            
                String[] opciones = {"Nombre", "Categoria", "Precio", "Cantidad", "Todos"};
                String opcion = JOptionPane.showInputDialog(ventanaListarModificarEliminar, "Qué desea cambiar?", "Opciones cambiar",JOptionPane.INFORMATION_MESSAGE, null , opciones, "").toString();

                if(opcion.equalsIgnoreCase("Nombre")){
                    String nuevoNombre = JOptionPane.showInputDialog(ventanaListarModificarEliminar,"Ingrese el nuevo nombre", "Cambio de nombre", JOptionPane.INFORMATION_MESSAGE);
                    Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                    try{
                        Producto producto1 = pasillo1.buscarProducto((ventanaListarModificarEliminar.getListTable()).getValueAt(ventanaListarModificarEliminar.getListTable().getSelectedRow(), 1).toString().trim());
                        pasillo1.cambiarNombre(producto1, nuevoNombre);
                        
                        ventanaListarModificarEliminar.getListTable().setValueAt(nuevoNombre,ventanaListarModificarEliminar.getListTable().getSelectedRow(), 1);
                    }catch(NameException e){
                        JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Nombre invalido.\nIngrese nuevamente.", "Error nombre", JOptionPane.ERROR_MESSAGE);
                    }
                }else if(opcion.equalsIgnoreCase("Categoria")){
                    String nuevaCategoriaSource = JOptionPane.showInputDialog(ventanaListarModificarEliminar, "Ingrese la nueva categoria", "Cambio categoria", JOptionPane.INFORMATION_MESSAGE).trim();
                    String nuevaCategoria = (nuevaCategoriaSource.substring(0,1).toUpperCase() + nuevaCategoriaSource.substring(1)).trim();
                    
                    Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                    try{
                        if(supermercado.buscarPasillo(nuevaCategoria) != null)
                        {
                            Producto producto1 = pasillo1.buscarProducto((ventanaListarModificarEliminar.getListTable()).getValueAt(ventanaListarModificarEliminar.getListTable().getSelectedRow(), 1).toString().trim());
                            pasillo1.cambiarCategoria(producto1, nuevaCategoria);
                        }else{
                            int respuesta = JOptionPane.showConfirmDialog(ventanaListarModificarEliminar, "Pasillo ingresado no valido.\n¿Desea crear un pasillo nuevo?", "Error al ingresar categoria", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                            if(respuesta == JOptionPane.YES_OPTION){
                                Producto producto1 = pasillo1.buscarProducto((ventanaListarModificarEliminar.getListTable()).getValueAt(ventanaListarModificarEliminar.getListTable().getSelectedRow(), 1).toString().trim()); 
                                supermercado.eliminarProductoDelSupermercado(producto1.getNombre());
                                supermercado.agregarPasillo(nuevaCategoria);
                                producto1.setCategoria(nuevaCategoria);
                                supermercado.buscarPasillo(nuevaCategoria).agregarProducto(producto1);
                             }else
                                return;
                        }
                        ventanaListarModificarEliminar.getListTable().setValueAt(nuevaCategoria,ventanaListarModificarEliminar.getListTable().getSelectedRow(), 2);
                    }catch(CategoryException e){
                        JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Categoria invalida.\nIngrese nuevamente.", "Error categoria", JOptionPane.ERROR_MESSAGE);
                    }
                }else if(opcion.equalsIgnoreCase("Precio")){
                    String nuevoPrecio = JOptionPane.showInputDialog(ventanaListarModificarEliminar, "Ingrese nuevo precio", "Cambio precio", JOptionPane.INFORMATION_MESSAGE);
                    Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                    try{
                        Producto producto1 = pasillo1.buscarProducto((ventanaListarModificarEliminar.getListTable()).getValueAt(ventanaListarModificarEliminar.getListTable().getSelectedRow(), 1).toString().trim());
                        pasillo1.cambiarPrecio(producto1, nuevoPrecio);
                        
                        ventanaListarModificarEliminar.getListTable().setValueAt(nuevoPrecio,ventanaListarModificarEliminar.getListTable().getSelectedRow(), 4); 
                    }catch(NumberException e){
                        JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Precio invalido.\nIngrese nuevamente.", "Error Precio", JOptionPane.ERROR_MESSAGE);
                    }
                }else if(opcion.equalsIgnoreCase("Cantidad")){
                    String nuevaCantidad = JOptionPane.showInputDialog(ventanaListarModificarEliminar, "Ingrese nueva cantidad", "Cambio cantidad", JOptionPane.INFORMATION_MESSAGE);
                    Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                    try{
                        Producto producto1 = pasillo1.buscarProducto((ventanaListarModificarEliminar.getListTable()).getValueAt(ventanaListarModificarEliminar.getListTable().getSelectedRow(), 1).toString().trim());
                        pasillo1.cambiarCantidad(producto1, nuevaCantidad);
                        
                        ventanaListarModificarEliminar.getListTable().setValueAt(nuevaCantidad,ventanaListarModificarEliminar.getListTable().getSelectedRow(), 3);
                    }catch(NumberException e){
                        JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Cantidad invalida.\nIngrese nuevamente.", "Error Cantidad", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    String nuevoNombre = JOptionPane.showInputDialog(ventanaListarModificarEliminar,"Ingrese el nuevo nombre", "Cambio de nombre", JOptionPane.INFORMATION_MESSAGE);
                    String nuevaCategoriaSource = JOptionPane.showInputDialog(ventanaListarModificarEliminar, "Ingrese la nueva categoria", "Cambio categoria", JOptionPane.INFORMATION_MESSAGE);
                    String nuevoPrecio = JOptionPane.showInputDialog(ventanaListarModificarEliminar, "Ingrese nuevo precio", "Cambio precio", JOptionPane.INFORMATION_MESSAGE);
                    String nuevaCantidad = JOptionPane.showInputDialog(ventanaListarModificarEliminar, "Ingrese nueva cantidad", "Cambio cantidad", JOptionPane.INFORMATION_MESSAGE);

                    String nuevaCategoria = (nuevaCategoriaSource.substring(0,1).toUpperCase() + nuevaCategoriaSource.substring(1)).trim();
                    
                    Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                    try{
                        Producto producto1 = pasillo1.buscarProducto((ventanaListarModificarEliminar.getListTable()).getValueAt(ventanaListarModificarEliminar.getListTable().getSelectedRow(), 1).toString().trim());
                        pasillo1.cambiarNombre(producto1, nuevoNombre);
                        try{
                            if(supermercado.buscarPasillo(nuevaCategoria) != null)
                            {
                                pasillo1.cambiarCategoria(producto1, nuevaCategoria);
                            }else{
                                int respuesta = JOptionPane.showConfirmDialog(ventanaListarModificarEliminar, "Pasillo ingresado no valido.\n¿Desea crear un pasillo nuevo?", "Error al ingresar categoria", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                                if(respuesta == JOptionPane.YES_OPTION){
                                    supermercado.eliminarProductoDelSupermercado(producto1.getNombre());
                                    supermercado.agregarPasillo(nuevaCategoria);
                                    producto1.setCategoria(nuevaCategoria);
                                    supermercado.buscarPasillo(nuevaCategoria).agregarProducto(producto1);
                                }
                            }
                            ventanaListarModificarEliminar.getListTable().setValueAt(nuevaCategoria,ventanaListarModificarEliminar.getListTable().getSelectedRow(), 2);
                            pasillo1.cambiarPrecio(producto1, nuevoPrecio);
                            pasillo1.cambiarCantidad(producto1, nuevaCantidad);  
                        }catch(CategoryException e){
                            JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Categoria invalida.\nIngrese nuevamente.", "Error categoria", JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(NameException e){
                        JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Nombre invalido.\nIngrese nuevamente.", "Error nombre", JOptionPane.ERROR_MESSAGE);
                    }catch(NumberException e){
                        JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Precio/Cantidad invalido/a.\nIngrese nuevamente.", "Error Precio/Cantidad", JOptionPane.ERROR_MESSAGE);
                    }
                    ventanaListarModificarEliminar.getListTable().setValueAt(nuevoNombre,ventanaListarModificarEliminar.getListTable().getSelectedRow(), 1);
                    ventanaListarModificarEliminar.getListTable().setValueAt(nuevaCategoria,ventanaListarModificarEliminar.getListTable().getSelectedRow(), 2);
                    ventanaListarModificarEliminar.getListTable().setValueAt(nuevoPrecio,ventanaListarModificarEliminar.getListTable().getSelectedRow(), 4);
                    ventanaListarModificarEliminar.getListTable().setValueAt(nuevaCantidad,ventanaListarModificarEliminar.getListTable().getSelectedRow(), 3);
                }   
            }
            
            return; 
        } 
        //Buscar producto.
        if(ventanaListarModificarEliminar != null && ee.getSource() == ventanaListarModificarEliminar.getBotonBuscarVentanaListar()){
            DefaultTableModel model = (DefaultTableModel) (ventanaListarModificarEliminar.getListTable()).getModel();
            TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
            ventanaListarModificarEliminar.getListTable().setRowSorter(obj);
            if(!ventanaListarModificarEliminar.getBarraBuscarVentanaListar().getText().equals("")){
                String filtro = ventanaListarModificarEliminar.getBarraBuscarVentanaListar().getText().substring(0,1).toUpperCase() + ventanaListarModificarEliminar.getBarraBuscarVentanaListar().getText().substring(1);
                obj.setRowFilter(RowFilter.regexFilter(filtro));
            } 
            return;
        }
    }
}
