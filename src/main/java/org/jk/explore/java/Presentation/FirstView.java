package org.jk.explore.java.Presentation;

import org.jk.explore.java.DataAccess.OrdersProductsDAO;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class FirstView {

    public JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FirstView window = new FirstView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FirstView() throws SQLException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws SQLException {

        frame = new JFrame();
        frame.setBounds(100, 100, 1241, 589);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\robert\\Desktop\\warehouseservices.PNG"));
        lblNewLabel.setBounds(0, 0, 1227, 550);
        frame.getContentPane().add(lblNewLabel);

        JButton btnNewButton = new JButton("Clients");
        btnNewButton.setBackground(SystemColor.activeCaption);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ClientsView reg= null;
                try {
                    reg = new ClientsView();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //frame.dispose();
                reg.frame.setVisible(true);
                // frame.dispose();
            }
        });
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNewButton.setBounds(86, 453, 275, 56);
        frame.getContentPane().add(btnNewButton);

        JButton btnProducts = new JButton("Products");
        btnProducts.setBackground(SystemColor.activeCaption);
        btnProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ProductsView reg= null;
                try {
                    reg = new ProductsView();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //frame.dispose();
                reg.frame.setVisible(true);
                // frame.dispose();
            }
        });
        btnProducts.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnProducts.setBounds(86, 368, 275, 56);
        frame.getContentPane().add(btnProducts);

        JButton btnOrders = new JButton("Orders");
        btnOrders.setBackground(SystemColor.activeCaption);
        btnOrders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                OrdersView reg= null;
                try {
                    reg = new OrdersView();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //frame.dispose();
                reg.frame.setVisible(true);
            }
        });
        btnOrders.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnOrders.setBounds(86, 284, 275, 56);
        frame.getContentPane().add(btnOrders);

        OrdersProductsDAO ordersProductsDAO= new OrdersProductsDAO();

        ordersProductsDAO.deleteFromOrdersDetails();
    }
}
