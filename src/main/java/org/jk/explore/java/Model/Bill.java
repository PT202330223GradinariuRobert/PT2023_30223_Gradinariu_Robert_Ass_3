package org.jk.explore.java.Model;


import org.jk.explore.java.DataAccess.ProductDAO;
import org.jk.explore.java.Model.Clients;
import org.jk.explore.java.Model.Orders;
import org.jk.explore.java.Model.OrdersProducts;
import org.jk.explore.java.Model.Products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.List;

public class Bill {

    private static boolean detailsDisplayed = false;
    /**
     Scrie intr un fisier .TXT factura generata de produsele adaugate de catre clientul respectiv
     @param  order order, clients client, lista deOrdersProducts listProds
     */
    public static void generateBill( Orders order, Clients client, List<OrdersProducts> listProds) {
        File file = new File("Order " + order.getId() + ".txt");


        int TotalSum=0;
        try {
            FileWriter writer = new FileWriter(file, false);
            writer.write("ORDER BILL NO. " + order.getId() + "\n");
            writer.write("---------------------------------\n");
            writer.write("Client:\n");
            writer.write("\tName: " + client.getName() + "\n");
            writer.write("\tAddress: " + client.getAddress() + "\n");
            writer.write("\tEmail: " + client.getEmail() + "\n");
            writer.write("---------------------------------\n");
            writer.write("Products:\n");
            writer.write("---------------------------------\n");

            for (OrdersProducts orderedProduct : listProds) {
                ProductDAO daoProd = new ProductDAO();
                Products prod = daoProd.findById(orderedProduct.getProductId());
                int quantity = orderedProduct.getQuantity();

                writer.write("\tProduct: " + prod.getNameP() + "\n");
                writer.write("\t\tQuantity: " + quantity + "\n");
                writer.write("\t\tPrice: " + prod.getPriceP() + "\n");
                writer.write("Units x $: " + quantity + " x " + prod.getPriceP() + " $ = " + (quantity * prod.getPriceP()) + " $"+"\n");
                TotalSum+=quantity * prod.getPriceP();
            }
                writer.write("\n\t\tTotal: "+TotalSum+" $"+"\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}