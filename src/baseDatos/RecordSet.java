/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class RecordSet {

  // Conexión con la base de datos */
  private Connection conn;
  // Sentencia SQL que se le envia al manejador de la base de datos para
  // consultar, insertar, actualizar, borrar.
  private Statement stmt;
  // Respuesta del manejador de la base de datos a la sentencia SQL.
  private ResultSet rs;
  //Sentencia que se le envía al manejador de la base de datos para ejecutar un SP
  private CallableStatement cst;

  /**
   * Establece la conexión con la base de datos.
     * @param conn conexion a la base de datos
   */
  public RecordSet(Connection conn) {
       this.conn = conn;
  }

  /**
   * Este método permite consultar una tabla de una base de datos mySQL.
   * @param sql Cadena con la sentencia con la consulta.
   * @throws SQLException Si no puede crear el Statement o ejecutar la consulta
   */
  public void executeSelect(String sql) throws SQLException {
    // Crea una sentencia para hacer la consulta
    stmt = conn.createStatement();

    // Ejecuta la consulta. El método executeQuery() regresa una tabla
    // que genera como resultado de la consulta
    rs = stmt.executeQuery(sql);
 }

 /**
  * Este método obtiene el siguiente renglón de la tabla generada por la
  * consulta hecha por el método executeSelect().
  * @return Si hay renglones, el siguiente renglón, null en caso contrario.
  * @throws SQLException Si no puede obtener el siguiente renglon
  * de la tabla.
  */
 public ResultSet readNext() throws SQLException {
    // Si hay otro renglon en la tabla, regrésalo.
    if(rs.next()) return rs;
    // Si no, cierra la sentencia, la tabla y regresa null.
    else
    {
      // Cierra la sentencia y la tabla y regresa null.
      rs.close();
      stmt.close();
      return null;
    }
  }

  /**
   * Este método permite modificar, insertar y borrar renglones de una tabla
   * de una base de datos mySQL.
   * @param sql Cadena con la sentencia para modificar, insertar o borrar.
   * @throws SQLException Si no puede crear el Statement o ejecutar la
   * actualización
   */
  public void executeUpdate(String sql) throws SQLException {
    // Crea una sentencia para hacer la modificación.
    stmt = conn.createStatement();

    // Ejecuta la modificación.
    int i = stmt.executeUpdate(sql);

    // Cierra la sentencia y la tabla.
    stmt.close();
  }
  
  /**
   * Este método permite hacer llamadas al sp_insertarProducto
   * @param sql Cadena con la llamada sentencia para ejecutar el SP
   * @param ProductName Nombre del producto
   * @param SupplierID ID del proveedor
   * @param CategoryID ID categoría
   * @param QuantityPerUnit Cantidad por unidad
   * @param UnitPrice Precio unitario
   * @param UnitsInStock Unidades en stock
   * @param UnitsOnOrder Unidades por orden
   * @param ReorderLevel Nivel de reorden
   * @param Discontinued es descontinuado
   * @throws SQLException Si no puede crear el Statement o ejecutar el SP
   */
  public void sp_insertarProducto (String sql, String ProductName, int SupplierID, int CategoryID,
            String QuantityPerUnit, double UnitPrice, int UnitsInStock, int UnitsOnOrder, int ReorderLevel,
            boolean Discontinued) throws SQLException {
      //Crea una sentencia para llamar el SP
      cst = conn.prepareCall(sql);
      //Agregar parámetros
      cst.setString(1,ProductName);
      cst.setInt(2, SupplierID);
      cst.setInt(3, CategoryID);
      cst.setString(4, QuantityPerUnit);
      cst.setDouble(5, UnitPrice);
      cst.setInt(6, UnitsInStock);
      cst.setInt(7, UnitsOnOrder);
      cst.setInt(8, ReorderLevel);
      cst.setBoolean(9, Discontinued);
      //Ejecuta el SP
      cst.execute();
      cst.close();
  }
  
  /**
   * Este método permite hacer llamadas al sp_actualizarProducto
   * @param sql Cadena con la llamada sentencia para ejecutar el SP
   * @param ProductID ID del producto a actualizar
   * @param ProductName nombre del producto a modificar
   * @param SupplierID proveedor del producto a modificar
   * @param CategoryID categoría del producto a modificar
   * @param QuantityPerUnit cantidades por unidad a modificar
   * @param UnitPrice precio unitario a modificar
   * @param UnitsInStock unidades en stock a modificar
   * @param UnitsOnOrder unidades en orden a modificar
   * @param ReorderLevel nivel de reordenamiento a modificar
   * @param Discontinued es discontinuo a modificar
   * @throws SQLException Si no puede crear el Statement o ejecutar el SP
   */
  public void sp_actualizarProducto(String sql, int ProductID, String ProductName, int SupplierID, int CategoryID,
          String QuantityPerUnit, double UnitPrice, int UnitsInStock, int UnitsOnOrder, int ReorderLevel,
          boolean Discontinued) throws SQLException{
      //Crea una sentencia para llamar el SP
      cst = conn.prepareCall(sql);
      //Agregar parámetros
      cst.setInt(1, ProductID);
      cst.setString(2, ProductName);
      cst.setInt(3, SupplierID);
      cst.setInt(4, CategoryID);
      cst.setString(5, QuantityPerUnit);
      cst.setDouble(6, UnitPrice);
      cst.setInt(7, UnitsInStock);
      cst.setInt(8, UnitsOnOrder);
      cst.setInt(9, ReorderLevel);
      cst.setBoolean(10, Discontinued);
      //Ejecutar el SP
      cst.execute();
      cst.close();
  }
  
  /**
   * Método que llena un defaulttablemodel con los datos de un resultset
   * @param sql sentencia para llenar la tabla
   * @param model talbla a llenar
   * @throws SQLException si no se puede ejecutar la sentencia
   */
  public void llenarTabla(String sql,DefaultTableModel model)throws SQLException{
      cst = conn.prepareCall(sql);
      ResultSet rs = cst.executeQuery();
      
      while(rs.next()){
          String datos [] = new String[14];
          for (int i = 0; i < 14; i++) { 
                datos[i]=rs.getString(i+1);
          }
          model.addRow(datos);
      }
      cst.close();
      conn.close();
  }
  
  /**
   * Llena un arreglo con los nombres de proveedores
   * @param sql sentencia para obtener los proveedores
   * @param proveedores arreglo a llenar
   * @throws SQLException si no se puede ejecutar la sentencia
   */
  public void obtenerEmpleados(String sql, int empleados[]) throws SQLException{
      cst = conn.prepareCall(sql);
      ResultSet rs = cst.executeQuery();
      int contador = 0;
      while(rs.next()){
          empleados[contador]=rs.getInt("EmployeeID");
          contador++;
      }
  }
  
  /**
   * Llena un arreglo con los id de proveedores
   * @param sql sentencia para obtener los proveedores
   * @param proveedores arreglo a llenar
   * @throws SQLException si no se puede ejecutar la sentencia
   */
  public void obtenerEmpleados(String sql, String empleados[]) throws SQLException{
      cst = conn.prepareCall(sql);
      ResultSet rs = cst.executeQuery();
      int contador = 0;
      while(rs.next()){
          empleados[contador]=rs.getString("EmployeeID");
          contador++;
      }
  }
  
  /**
   * Método que llena un arreglo con los nombres de las categorías
   * @param sql sentencia sql para obtener las categorías
   * @param categorias arreglo a llenar
   * @throws SQLException Si no se puede ejecutar la sentencia
   */
  public void obtenerCategorias (String sql, String categorias[])throws SQLException{
      cst = conn.prepareCall(sql);
      ResultSet rs = cst.executeQuery();
      int contador = 0;
      while(rs.next()){
          categorias [contador] = rs.getString("CategoryName");
          contador++;
      }
  }
  
  /**
   * Método que llena un arreglo con los id de las categorías
   * @param sql sentencia sql para obtener las categorías
   * @param categorias arreglo a llenar
   * @throws SQLException Si no se puede ejecutar la sentencia
   */
  public void obtenerCategorias (String sql, int categorias[])throws SQLException{
      cst = conn.prepareCall(sql);
      ResultSet rs = cst.executeQuery();
      int contador = 0;
      while(rs.next()){
          categorias [contador] = rs.getInt("CategoryID");
          contador++;
      }
  }
  
  /**
   * Método que obtiene el nombre de un proveedor a partir de su id
   * @param sql sentencia a ejecutar
   * @param id proveedor a buscar
   * @param proveedor nombre del proveedor
   * @throws SQLException si no se puede ejecutar la sentencia
   */
  public void obtenerProveedorNombre (String sql, int id, String proveedor) throws SQLException{
      cst = conn.prepareCall(sql);
      ResultSet rs = cst.executeQuery();
      while(rs.next()){
          proveedor = rs.getString("CompanyName");
      }
  }
  
  /**
   * Método que obtiene el id de un proveedor a partir de su nombre
   * @param sql sentencia a ejecutar
   * @param id id del proveedor
   * @param proveedor proveedor a buscar
   * @throws SQLException si no se puede ejecutar la sentencia
   */
  public void obtenerProveedorID (String sql, int id, String proveedor) throws SQLException{
      cst = conn.prepareCall(sql);
      ResultSet rs = cst.executeQuery();
      while(rs.next()){
          id = rs.getInt("SupplierID");
      }
  }
  
  /**
   * Método que obtiene el nombre de una categoría a partir de su id
   * @param sql sentencia a ejectuar
   * @param id categoría a buscar
   * @param categoria nombre de la categoría
   * @throws SQLException si no se puede ejecutar la sentencia
   */
  public void obtenerCategoriaNombre(String sql, int id, String categoria) throws SQLException{
      cst = conn.prepareCall(sql);
      ResultSet rs = cst.executeQuery();
      while(rs.next()){
          categoria = rs.getString("CategoryName");
      }
  }
  
  /**
   * Método que obtiene el id de una categoría a partir de su nombre
   * @param sql sentencia a ejecutar
   * @param id id de la categoría
   * @param categoria categoría a buscar
   * @throws SQLException si no se puede ejecutar la sentencia
   */
  public void obtenerCategoriaID(String sql, int id, String categoria)throws SQLException{
      cst = conn.prepareCall(sql);
      ResultSet rs = cst.executeQuery();
      while (rs.next()){
          id = rs.getInt("CategoryID");
      }
  }
}