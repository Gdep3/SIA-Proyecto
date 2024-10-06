package controlador;

import clases.*;
import excepciones.*;
import java.awt.event.ActionEvent;
import ventanas.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Isidora Osorio
 */
public class Controlador implements ActionListener{
    private Supermercado supermercado;
    private Cliente cliente;
    private Empleado empleado;
    
    private VentanaPrincipal menuMain;
    private VentanaEmpleado menuEmpleado;
    private VentanaCliente menuCliente;
    private VentanaCarrito menuCarrito;
    private VentanaListar_Modificar_Eliminar ventanaListarModificarEliminar;
    private VentanaUsuarioCliente ventanaUsuarioCliente;
    private VentanaAgregar menuAgregar;
    private UsuarioEmpleado ventanaUsuarioEmpleado;
    
    public void iniciar(){
        supermercado = new Supermercado();
        
        cliente = new Cliente("Jose", "21274476-3");
        empleado = new Empleado("Jose", "21274476-3", 12313423);
        
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
        

        String className = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        try {
            UIManager.setLookAndFeel(className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            menuCliente.getBotonAñadirCarritoMenuCliente().addActionListener(this);
            menuCliente.getBotonCarrito().addActionListener(this);
            menuCliente.getBotonUsuarioCliente().addActionListener(this);
            
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
            menuEmpleado.getBotonUsuarioEmpleado().addActionListener(this);
            
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
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonCarrito()){
            menuCarrito = new VentanaCarrito(cliente.comprasAString());
            
            menuCarrito.getBotonComprarCarrito().addActionListener(this);
            menuCarrito.getBotonVovlerCarrito().addActionListener(this);
            menuCarrito.getBotonEliminarCarrito().addActionListener(this);
            
            menuCarrito.getTotalTexto().setText(cliente.totalComprasPrecio());
            
            menuCarrito.setAlwaysOnTop(true);
            menuCarrito.setTitle("Carrito");
            menuCarrito.setSize(500, 400);
            menuCarrito.setResizable(false);
            menuCarrito.setLocationRelativeTo(null);
            menuCarrito.setVisible(true);
            return;
        }
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonUsuarioCliente()){
            String[] datos = cliente.datosAString().split(";");

            if(datos.length == 3)
                ventanaUsuarioCliente = new VentanaUsuarioCliente(datos[2]);
            else
                ventanaUsuarioCliente = new VentanaUsuarioCliente("");
            
            ventanaUsuarioCliente.getBotonVovlerHistorial().addActionListener(this);
            ventanaUsuarioCliente.getTitulo().setText("Bienvenido" + " " + datos[0]);
            ventanaUsuarioCliente.getTextoRutCliente().setText(" Rut cliente: " + datos[1].trim());
            ventanaUsuarioCliente.getTextoEmailCliente().setText(" Email cliente: " + "a@gmail.com");
            
            ventanaUsuarioCliente.setAlwaysOnTop(true);
            ventanaUsuarioCliente.setTitle("Cliente");
            ventanaUsuarioCliente.setSize(500, 400);
            ventanaUsuarioCliente.setResizable(false);
            ventanaUsuarioCliente.setLocationRelativeTo(null);
            ventanaUsuarioCliente.setVisible(true);

            return;
        }
        if(ventanaUsuarioCliente != null && ee.getSource() == ventanaUsuarioCliente.getBotonVovlerHistorial()){
            ventanaUsuarioCliente.dispose();
            return;
        }
        //Accines menu carrito.
        //Añadir a carrito.
        if(menuCliente != null && ee.getSource() == menuCliente.getBotonAñadirCarritoMenuCliente()){
            DefaultTableModel model = (DefaultTableModel) menuCliente.getListaCliente().getModel();
            
            switch (menuCliente.getListaCliente().getSelectedRowCount()) {
                case 1:
                    String cantidad = JOptionPane.showInputDialog(menuCliente, "Ingrese la cantidad de " + menuCliente.getListaCliente().getValueAt(menuCliente.getListaCliente().getSelectedRow(), 0).toString().trim(), "Cantidad a comprar", JOptionPane.INFORMATION_MESSAGE);
                    if(cantidad == null || cantidad.equals("")){
                        JOptionPane.showMessageDialog(menuCliente, "Por favor ingrese una cantidad", "Producto no añadido a carrito", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    } 
                    Producto producto = supermercado.obtenerProductoEnSupermercado(menuCliente.getListaCliente().getValueAt(menuCliente.getListaCliente().getSelectedRow(), 0).toString().trim());
                    if(producto.getCantidad() >= Integer.parseInt(cantidad)){
                        cliente.guardarCompras(menuCliente.getListaCliente().getValueAt(menuCliente.getListaCliente().getSelectedRow(), 0).toString().trim(), Double.parseDouble(menuCliente.getListaCliente().getValueAt(menuCliente.getListaCliente().getSelectedRow(), 1).toString().trim()), Integer.parseInt(cantidad));
                        
                        model.setValueAt(producto.getCantidad() - Integer.parseInt(cantidad), menuCliente.getListaCliente().convertRowIndexToModel(menuCliente.getListaCliente().getSelectedRow()), 2);
                        
                        try{
                            producto.setCantidad(producto.getCantidad() - Integer.parseInt(cantidad));
                        }catch(NumberException e){
                            e.printStackTrace();
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(menuCliente, "Cantidad invalida,\ncantidad ingresada sobrepasa el stock del supermercado." , "Error cantida invalida", JOptionPane.ERROR_MESSAGE);
                        return;
                    }   break;
                case 0:
                    JOptionPane.showMessageDialog(menuCliente, "Por favor seleccione un producto", "Ningún producto seleccionado", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(menuCliente, "Por favor seleccione solo un producto", "Ningún producto seleccionado", JOptionPane.ERROR_MESSAGE);
                    break;
            }
            return;
        }
        //Comprar en carrito
        if(menuCarrito != null && ee.getSource() ==  menuCarrito.getBotonComprarCarrito()){   
            if(cliente.getTotalComprasCantidad() != 0){
                
                supermercado.setVentas(cliente.getTotalComprasCantidad());
                supermercado.setStockTotal(cliente.getTotalComprasCantidad() * -1);
                cliente.añadirAHistorial();
                cliente.vaciarCarrito();
                menuCarrito.dispose();
            }
            return;
        }
        //Eliminar del carrito.
        if(menuCarrito != null && ee.getSource() == menuCarrito.getBotonEliminarCarrito()){
            DefaultTableModel model = (DefaultTableModel) (menuCarrito.getListaCompras()).getModel();

            if((menuCarrito.getListaCompras()).getSelectedRowCount() == 1){
                String nombre = (menuCarrito.getListaCompras()).getValueAt(menuCarrito.getListaCompras().getSelectedRow(), 0).toString().trim();
                int respuesta = JOptionPane.showConfirmDialog(menuCarrito, "Esta seguro   de que quiere eliminar este producto?", "Eliminando producto del carrito", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if(respuesta == JOptionPane.YES_OPTION){  
                    int cantidad = Integer.parseInt((menuCarrito.getListaCompras()).getValueAt(menuCarrito.getListaCompras().getSelectedRow(), 2).toString().trim());
                    
                    cliente.eliminarCompra(nombre);
                    menuCarrito.getTotalTexto().setText(cliente.totalComprasPrecio());
                    
                    try{
                        Producto producto = supermercado.obtenerProductoEnSupermercado(nombre);
                        producto.setCantidad(producto.getCantidad() + cantidad);
                        
                        DefaultTableModel modelCliente = (DefaultTableModel) menuCliente.getListaCliente().getModel();
                        for(int i = 0; i < modelCliente.getRowCount(); i++){
                            if(modelCliente.getValueAt(i, 0).equals(nombre)){
                                modelCliente.setValueAt(producto.getCantidad(), i, 2);
                                break;
                            }
                        }
                        
                    }catch(NumberException e){
                        e.printStackTrace();
                    }
                    model.removeRow(menuCarrito.getListaCompras().convertRowIndexToModel((menuCarrito.getListaCompras().getSelectedRow())));
                    
                }else{
                    JOptionPane.showMessageDialog(menuCarrito, "Producto no fue eliminado.", "Cancelando", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if(cliente.buscarCompra(nombre) == false)
                    JOptionPane.showMessageDialog(menuCarrito, "Producto eliminado correctamente del carrito.", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(menuCarrito,"Error, el producto no fue eliminado correctamente del carrito.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
            }else
            {
                if((menuCarrito.getListaCompras()).getSelectedRowCount() == 0)
                    JOptionPane.showMessageDialog(menuCarrito, "Error, por favor seleccione solo una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(menuCarrito, "Error, seleccione una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Boton volver a menu cliente.
        if(menuCarrito != null && ee.getSource() == menuCarrito.getBotonVovlerCarrito()){
            menuCarrito.dispose();
            return;
        } 
        //Acciones menu empleado.
        //Ir menu usuario empleado
        if(menuEmpleado != null && ee.getSource() == menuEmpleado.getBotonUsuarioEmpleado()){
            ArrayList pasillosCopia = supermercado.obtenerPasillos();
            for(int i = 0; i < pasillosCopia.size(); i++){
                ArrayList productos = ((Pasillo)pasillosCopia.get(i)).obtenerProductos();
                for(int k = 0; k < productos.size(); k++){
                    Producto producto = (Producto)productos.get(k);
                    if(producto.getCantidad() == 0)
                        empleado.agregarProductosSinStock(producto.getCodigo() +","+ producto.getNombre()+","+ producto.getCategoria()+","+ producto.getPrecio()+","+ producto.getCantidad() + "\n");
                }
            }
            
            String[] datos = empleado.datosAString().split(";");

            if(datos.length == 4)
                ventanaUsuarioEmpleado = new UsuarioEmpleado(datos[3]);
            else
                ventanaUsuarioEmpleado = new UsuarioEmpleado("");

            ventanaUsuarioEmpleado.getTituloUsuarioEmpleado().setText("Bienvenido" + " " + datos[0]);
            ventanaUsuarioEmpleado.getTextoRutEmpleado().setText(" Rut empleado: " + datos[1].trim());
            ventanaUsuarioEmpleado.getTextoCodigoEmpleado().setText(" Codigo empleado: " + datos[2]);
            
            ventanaUsuarioEmpleado.getBotonAgregarStock().addActionListener(this);
            ventanaUsuarioEmpleado.getBotonVolverUsuarioEmpleado().addActionListener(this);
            
            ventanaUsuarioEmpleado.setAlwaysOnTop(true);
            ventanaUsuarioEmpleado.setTitle("Empleado");
            ventanaUsuarioEmpleado.setSize(500, 400);
            ventanaUsuarioEmpleado.setResizable(false);
            ventanaUsuarioEmpleado.setLocationRelativeTo(null);
            ventanaUsuarioEmpleado.setVisible(true);
            return;
        }
        if(ventanaUsuarioEmpleado != null && ee.getSource() == ventanaUsuarioEmpleado.getBotonVolverUsuarioEmpleado()){
            ventanaUsuarioEmpleado.dispose();
            return;
        }
        if(ventanaUsuarioEmpleado != null && ee.getSource() == ventanaUsuarioEmpleado.getBotonAgregarStock()){
            if((ventanaUsuarioEmpleado.getProductosSinStock()).getSelectedRowCount() == 1){
                String categoria = (ventanaUsuarioEmpleado.getProductosSinStock()).getValueAt(ventanaUsuarioEmpleado.getProductosSinStock().getSelectedRow(), 2).toString().trim();
                String nuevaCantidad = JOptionPane.showInputDialog(ventanaUsuarioEmpleado, "Ingrese nueva cantidad", "Cambio cantidad", JOptionPane.INFORMATION_MESSAGE);
                Pasillo pasillo1 = supermercado.buscarPasillo(categoria);
                try{
                    Producto producto1 = pasillo1.buscarProducto((ventanaUsuarioEmpleado.getProductosSinStock()).getValueAt(ventanaUsuarioEmpleado.getProductosSinStock().getSelectedRow(), 1).toString().trim());
                    pasillo1.cambiarCantidad(producto1, nuevaCantidad);
                        
                    ventanaUsuarioEmpleado.getProductosSinStock().setValueAt(nuevaCantidad,ventanaUsuarioEmpleado.getProductosSinStock().getSelectedRow(), 4);
                }catch(NumberException e){
                    JOptionPane.showMessageDialog(ventanaUsuarioEmpleado, "Cantidad invalida.\nIngrese nuevamente.", "Error Cantidad", JOptionPane.ERROR_MESSAGE);
                }
            }else
            {
                //Si el usuario no tiene seleccionada ninguna columna.
                if((ventanaUsuarioEmpleado.getProductosSinStock()).getSelectedRowCount() == 0)
                    JOptionPane.showMessageDialog(ventanaUsuarioEmpleado, "Error, por favor seleccione solo una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
                //Si tiene seleccionada más de una columna.
                else
                    JOptionPane.showMessageDialog(ventanaUsuarioEmpleado, "Error, seleccione una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
            }
            empleado.vaciarProductosSinStock();
            return;
        }
        
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
            JOptionPane.showMessageDialog(menuEmpleado, "Creación del reporte completada.", "Reporte completado", JOptionPane.INFORMATION_MESSAGE);
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
            }else{
                //Si el usuario no tiene seleccionada ninguna columna.
                if((ventanaListarModificarEliminar.getListTable()).getSelectedRowCount() == 0)
                    JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Error, por favor seleccione solo una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
                //Si tiene seleccionada más de una columna.
                else
                    JOptionPane.showMessageDialog(ventanaListarModificarEliminar, "Error, seleccione una columna.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
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