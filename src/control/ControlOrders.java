package control; 

import baseDatos.Orders;
import baseDatos.conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import objetoNegocio.Order;

public class ControlOrders {
    private String servidor = "DESKTOP-3H3CLH5"; //Nombre del servidor 
    private String puerto = "1433"; //IP
    private String user = "sa"; //usuario loggin SQL Server
    private String password = "itson"; //Contraseña
    private String baseDatos = "Northwind"; //Nombre de la base de datos
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    List nombresColumnasTablaOrders = new ArrayList<String>();
 
    public ControlOrders() {
        //Llenar la lista con las columnas de la tabla
        nombresColumnasTablaOrders.add("OrderID");
        nombresColumnasTablaOrders.add("CustomerID");
        nombresColumnasTablaOrders.add("EmployeeID");
        nombresColumnasTablaOrders.add("OrderDate");
        nombresColumnasTablaOrders.add("RequiredDate");
        nombresColumnasTablaOrders.add("ShippedDate");
        nombresColumnasTablaOrders.add("ShipVia");
        nombresColumnasTablaOrders.add("Freight");
        nombresColumnasTablaOrders.add("ShipName");
        nombresColumnasTablaOrders.add("ShipAddress");
        nombresColumnasTablaOrders.add("ShipCity");
        nombresColumnasTablaOrders.add("ShipRegion");
        nombresColumnasTablaOrders.add("ShipPostalCode");
        nombresColumnasTablaOrders.add("ShipCountry");
        
    } 
    public void llenaTablaOrders(JFrame frame,DefaultTableModel model){
        try { 
            Connection conn = conexion.getConexion(user, password, driver, servidor, baseDatos, puerto);
            Orders orders = new Orders(conn);
            try{
                orders.tablaOrders(model);
                
            } catch(SQLException e){
                JOptionPane.showMessageDialog(frame, e.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
    }
     
 
}
