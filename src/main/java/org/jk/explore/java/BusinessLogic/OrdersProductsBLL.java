package org.jk.explore.java.BusinessLogic;

import org.jk.explore.java.Model.OrdersProducts;
import org.jk.explore.java.DataAccess.OrdersProductsDAO;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrdersProductsBLL {

    public OrdersProductsDAO ordProd;


    public OrdersProductsBLL() throws SQLException {

        ordProd= new OrdersProductsDAO();
    }

    /**
     Selecteaza datele despre comanda din tabela OrdersProducts

     @return ordProd
     */
    public List<OrdersProducts> selectAll() throws SQLException {

        return ordProd.findAll();
    }
    /**
     Selecteaza datele despre comanda cu id ul id
     @param id
     @return OrdersProducts
     */
    public OrdersProducts selectById(int id){

        return ordProd.findById(id);
    }
    /**
     insereaza datele despre comanda
     @param ordPROD
     @return OrdersProducts
     */
    public OrdersProducts insertOrderProducts(OrdersProducts ordPROD) throws SQLException, IllegalAccessException {

        return ordProd.insert(ordPROD);
    }

    public ResultSet GenerateTableForOrdersProducts(JTable table, ResultSet resultSet){

        return ordProd.generateTable(table,resultSet);
    }

    public ResultSet findAllforTableOrdersProducts() {
        return ordProd.findAllforTable();
    }


}
