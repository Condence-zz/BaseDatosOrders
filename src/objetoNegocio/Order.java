package objetoNegocio; 

import java.util.Calendar; 
public class Order {
    public int OrderID,EmployeeID,ShipVia;
    public Calendar OrderDate,RequiredDate,ShippedDate;
    public String CustomerID,ShipName,ShipAddress,ShipCity,ShipRegion,ShipPostalCode,ShipCountry;
    public float Freight;

    public Order() {
    }
    
    public Order(int OrderID, int EmployeeID, int ShipVia, Calendar OrderDate, Calendar RequiredDate, Calendar ShippedDate, String CustomerID, String ShipName, String ShipAddress, String ShipCity, String ShipRegion, String ShipPostalCode, String ShipCountry, float Freight) {
        this.OrderID = OrderID;
        this.EmployeeID = EmployeeID;
        this.ShipVia = ShipVia;
        this.OrderDate = OrderDate;
        this.RequiredDate = RequiredDate;
        this.ShippedDate = ShippedDate;
        this.CustomerID = CustomerID;
        this.ShipName = ShipName;
        this.ShipAddress = ShipAddress;
        this.ShipCity = ShipCity;
        this.ShipRegion = ShipRegion;
        this.ShipPostalCode = ShipPostalCode;
        this.ShipCountry = ShipCountry;
        this.Freight = Freight;
    } 
    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public int getShipVia() {
        return ShipVia;
    }

    public void setShipVia(int ShipVia) {
        this.ShipVia = ShipVia;
    }

    public Calendar getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Calendar OrderDate) {
        this.OrderDate = OrderDate;
    }

    public Calendar getRequiredDate() {
        return RequiredDate;
    }

    public void setRequiredDate(Calendar RequiredDate) {
        this.RequiredDate = RequiredDate;
    }

    public Calendar getShippedDate() {
        return ShippedDate;
    }

    public void setShippedDate(Calendar ShippedDate) {
        this.ShippedDate = ShippedDate;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getShipName() {
        return ShipName;
    }

    public void setShipName(String ShipName) {
        this.ShipName = ShipName;
    }

    public String getShipAddress() {
        return ShipAddress;
    }

    public void setShipAddress(String ShipAddress) {
        this.ShipAddress = ShipAddress;
    }

    public String getShipCity() {
        return ShipCity;
    }

    public void setShipCity(String ShipCity) {
        this.ShipCity = ShipCity;
    }

    public String getShipRegion() {
        return ShipRegion;
    }

    public void setShipRegion(String ShipRegion) {
        this.ShipRegion = ShipRegion;
    }

    public String getShipPostalCode() {
        return ShipPostalCode;
    }

    public void setShipPostalCode(String ShipPostalCode) {
        this.ShipPostalCode = ShipPostalCode;
    }

    public String getShipCountry() {
        return ShipCountry;
    }

    public void setShipCountry(String ShipCountry) {
        this.ShipCountry = ShipCountry;
    }

    public float getFreight() {
        return Freight;
    }

    public void setFreight(float Freight) {
        this.Freight = Freight;
    }
    
}
