package org.jk.explore.java.BusinessLogic;

import org.jk.explore.java.DataAccess.ClientDAO;
import org.jk.explore.java.DataAccess.OrderDAO;
import org.jk.explore.java.DataAccess.OrdersProductsDAO;
import org.jk.explore.java.DataAccess.ProductDAO;
import org.jk.explore.java.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBLL {


    private ClientDAO clientDao;
    private ProductDAO productDao;

    private OrdersProductsDAO ordersProductsDAO;
    private OrderDAO orderDao;

    public OrderBLL() throws SQLException {

        clientDao=new ClientDAO();
        productDao=new ProductDAO();
        ordersProductsDAO= new OrdersProductsDAO();
        orderDao =new OrderDAO();
    }

    /**
     Selecteaza datele din tabela OrdersDetails

     @return ResultSet
     */
    public ResultSet selectFromOrdersAndDetails(){

        return ordersProductsDAO.SelectFromOrdersAndDetails();
    }
    /**
     Selecteaza toate comenzile

     @return orderDao
     */
    public List<Orders> selectAll() throws SQLException {

        return orderDao.findAll();
    }

    /**
    Insereaza o comanda
     @param order order
     @return Orders
     */
    public Orders insertOrder(Orders order) throws SQLException, IllegalAccessException {


         return orderDao.insert(order);
    }


    /**
     Adauga la comanda deja creata( formata din id unic si id ul clientului) produsul si atributele sale
     Facem update in tabela Products daca produsul a fost adaugat cu success
     Facem update la Bill ul comenzii respective, inserand produsul adaugat
     @param order order, int idProduct, int quantity
     @return un Client
     */
    public String addAtOrder(Orders order, int idProduct, int quantity) throws SQLException, IllegalAccessException {

        Products product;
        product = productDao.findById(idProduct);
        Clients client;

        if (product.getQuantityP() >= quantity){

            OrdersProducts obj =new OrdersProducts(order.getId(),idProduct,quantity);
            /// adaugam produsul la comanda in tabela OrdersProducts

            ordersProductsDAO.insert(obj);

            int actualQuantity=product.getQuantityP();
            product.setQuantityP(actualQuantity-quantity); ///actualizam cantitatea
            System.out.println(actualQuantity-quantity);
             productDao.update(product); /// facem update pe produs
            product=productDao.findById(idProduct);  ///cautam respectivul produs
            client=clientDao.findById(order.getClientId());
           List<OrdersProducts> listWithProducts = ordersProductsDAO.findAll(); /// reprezinta lista cu elementele din tabela OrdersProducts

           List<OrdersProducts> filteredList= new ArrayList<OrdersProducts>();

              for(OrdersProducts ordProd: listWithProducts){
               if(ordProd.getOrderId()==order.getId()){
                   filteredList.add(ordProd); /// adaug doar lista formata din produsele de la comanda cu id ul X
               }
               }

            Bill.generateBill(order,client,filteredList); /// adaugam produsul pe factura
            return "S a adaugat cu succes produsul cu id-ul "+idProduct+" la comanda "+order.getId();
        }
        return  null;

    }

    public ResultSet GenerateTableForOrders(JTable table, ResultSet resultSet){

        return orderDao.generateTable(table,resultSet);
    }

    public ResultSet findAllforTableOrders() {
        return orderDao.findAllforTable();
    }
}
