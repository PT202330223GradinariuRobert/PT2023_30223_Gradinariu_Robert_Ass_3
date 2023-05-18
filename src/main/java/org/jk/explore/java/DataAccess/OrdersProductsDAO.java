package org.jk.explore.java.DataAccess;


import org.jk.explore.java.Connection.ConnectionFactory;
import org.jk.explore.java.Model.OrdersProducts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class OrdersProductsDAO extends AbstractDAO<OrdersProducts> {

    public OrdersProductsDAO() throws SQLException {

    }
    /**
     Creeaza un resultSet  de obiecte OrdersProducts si face JOIN pe tabela Orders si OrdersProducts

     @return resultSet
     */
    public ResultSet SelectFromOrdersAndDetails() {

        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        StringBuilder aux = new StringBuilder();

        aux.append("SELECT OrdersProducts.orderId,Orders.clientId,OrdersProducts.productId,OrdersProducts.quantity from Orders join OrdersProducts on Orders.id=OrdersProducts.orderId;");
        System.out.println(aux.toString());

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(aux.toString());
            resultSet = statement.executeQuery();

            return resultSet;
        } catch (SQLException E) {
            LOGGER.log(Level.WARNING, "OrdersProductsDAO:SelectFromOrdersAndDetails " + E.getMessage());
        } finally {
           // ConnectionFactory.close(resultSet);
          //  ConnectionFactory.close(statement);
          //  ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     Sterge( goleste ) tabela OrdersProducts
     */
    public void deleteFromOrdersDetails() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            String query = "DELETE FROM OrdersProducts";
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            System.out.println("Deleted rows: " + statement.getUpdateCount());
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error deleting from OrdersProducts: " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    }


