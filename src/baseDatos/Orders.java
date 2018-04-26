/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Orders extends RecordSet{ 
    private String nomTabla = "Orders";
    public Orders(Connection conn) {
        super(conn);
    }
    /**
     * 	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [nchar](5) NULL,
	[EmployeeID] [int] NULL,
	[OrderDate] [datetime] NULL,
	[RequiredDate] [datetime] NULL,
	[ShippedDate] [datetime] NULL,
	[ShipVia] [int] NULL,
	[Freight] [money] NULL,
	[ShipName] [nvarchar](40) NULL,
	[ShipAddress] [nvarchar](60) NULL,
	[ShipCity] [nvarchar](15) NULL,
	[ShipRegion] [nvarchar](15) NULL,
	[ShipPostalCode] [nvarchar](10) NULL,
	[ShipCountry] [nvarchar](15) NULL,
     * @param model
     * @throws SQLException 
     */
 
    public void tablaOrders(DefaultTableModel model) throws SQLException{
        String sql = "SELECT o.OrderID, c.CompanyName, e.FirstName+' '+e.LastName,o.OrderDate, o.RequiredDate, o.ShippedDate, s.CompanyName,"
                + "o.Freight, o.ShipName, o.ShipAddress, o.ShipCity, o.ShipRegion, o.ShipPostalCode, o.ShipCountry "
                + "FROM Orders O INNER JOIN Employees E ON e.EmployeeID = o.EmployeeID "
                + "INNER JOIN Shippers S ON s.ShipperID = o.ShipVia "
                + "INNER JOIN Customers C ON c.CustomerID = o.CustomerID";
        llenarTabla(sql, model);
    } 
}