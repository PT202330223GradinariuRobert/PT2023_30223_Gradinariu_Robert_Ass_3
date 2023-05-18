package org.jk.explore.java.Presentation;

import org.jk.explore.java.BusinessLogic.ClientBLL;
import org.jk.explore.java.BusinessLogic.OrderBLL;
import org.jk.explore.java.BusinessLogic.OrdersProductsBLL;
import org.jk.explore.java.BusinessLogic.ProductBLL;
import org.jk.explore.java.DataAccess.OrderDAO;
import org.jk.explore.java.Model.Clients;
import org.jk.explore.java.Model.Orders;
import org.jk.explore.java.Model.Products;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrdersView {

    JFrame frame;

    private JTable table;
    private JScrollPane scrollPane;

    private JTextArea textAreaForBill;

    private JScrollPane scrollPaneBill;

    private JButton btnAddtoorder;
    private JLabel lblNewLabel;
    private JButton btnViewOrders;

    private JButton btnAddOrder;

    private JLabel OrderLabel;

    private JLabel IdOrderForCreate;

    private JLabel ClientLabel;

    private JTextField textField_1;

    private JLabel ProductLabel;

    private JLabel lblNewLabel_2;
    private JLabel QuantityLabel;

    private JTextField OrderField;

    private JTextField ClientField;
    private JTextField ProductField;
    private JTextField QuantityField;

    int ok=0;
    JComboBox comboBoxClient;

    JComboBox comboBoxProd;

    JComboBox comboBoxOrder;

    JComboBox comboBox_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrdersView window = new OrdersView();
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
    public OrdersView() throws SQLException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws SQLException {
        frame = new JFrame();
        frame.setBounds(100, 100, 1179, 598);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        OrderLabel = new JLabel("Order Id:");
        OrderLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        OrderLabel.setBounds(455, 40, 65, 23);
        frame.getContentPane().add(OrderLabel);

        ClientLabel = new JLabel("Client Id:");
        ClientLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ClientLabel.setBounds(601, 29, 65, 44);
        frame.getContentPane().add(ClientLabel);

        ProductLabel = new JLabel("Product Id:");
        ProductLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ProductLabel.setBounds(750, 36, 71, 31);
        frame.getContentPane().add(ProductLabel);

        QuantityLabel = new JLabel("Quantity:");
        QuantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        QuantityLabel.setBounds(915, 33, 65, 37);
        frame.getContentPane().add(QuantityLabel);

        IdOrderForCreate = new JLabel("Order Id:");
        IdOrderForCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        IdOrderForCreate.setBounds(455, 180, 65, 23);
        frame.getContentPane().add(IdOrderForCreate);


        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(525, 183, 54, 20);
        frame.getContentPane().add(textField_1);

        lblNewLabel_2 = new JLabel("Client Id:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(601, 180, 65, 23);
        frame.getContentPane().add(lblNewLabel_2);

        final JComboBox comboBoxOrder = new JComboBox();
        comboBoxOrder.setBounds(525, 42, 54, 22);
        frame.getContentPane().add(comboBoxOrder);

        comboBoxClient = new JComboBox();
        comboBoxClient.setBounds(676, 42, 54, 22);
        frame.getContentPane().add(comboBoxClient);

        comboBoxProd = new JComboBox();
        comboBoxProd.setBounds(836, 42, 54, 22);
        frame.getContentPane().add(comboBoxProd);

        comboBox_2 = new JComboBox();
        comboBox_2.setBounds(676, 182, 54, 22);
        frame.getContentPane().add(comboBox_2);

        ClientBLL cblll= new ClientBLL();
        List<Clients> clientss= cblll.selectAll();
        List<String> idClientss= new ArrayList<String>();

        for(Clients ii:clientss){
            idClientss.add(Integer.toString(ii.getId()));
        }

        String[] IDclientss= new String[idClientss.size()];
        idClientss.toArray(IDclientss);

        comboBox_2.setModel(new DefaultComboBoxModel(IDclientss));

        QuantityField = new JTextField();
        QuantityField.setBounds(998, 43, 96, 20);
        frame.getContentPane().add(QuantityField);
        QuantityField.setColumns(10);



        ClientBLL cbll= new ClientBLL();
        List<Clients> clients= cbll.selectAll();
        List<String> idClients= new ArrayList<String>();

        for(Clients i:clients){
            idClients.add(Integer.toString(i.getId()));
        }

        String[] IDclients= new String[idClients.size()];
        idClients.toArray(IDclients);

        comboBoxClient.setModel(new DefaultComboBoxModel(IDclients));

        ProductBLL productBLL= new ProductBLL();

        List<Products> products=productBLL.selectAll();
        List<String> idProducts1= new ArrayList<String>();

        for(Products p:products){
            idProducts1.add(Integer.toString(p.getId()));
        }

        String[] idProducts= new String[idProducts1.size()];

        idProducts1.toArray(idProducts);

        comboBoxProd.setModel(new DefaultComboBoxModel(idProducts));


        final OrderBLL orderBLL= new OrderBLL();

        List<Orders> orders=orderBLL.selectAll();
        List<String> idOrders1= new ArrayList<String>();

        for(Orders o: orders){
            idOrders1.add(Integer.toString(o.getId()));
        }

        String[] idOrders= new String[idOrders1.size()];

        idOrders1.toArray(idOrders);

        comboBoxOrder.setModel(new DefaultComboBoxModel(idOrders));

        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                FirstView view = null;
                try {
                    view = new FirstView();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                view.frame.setVisible(true);
            }
        });
        btnNewButton.setBounds(1044, 509, 89, 23);
        frame.getContentPane().add(btnNewButton);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 416, 306);
        frame.getContentPane().add(scrollPane);
        final DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);


//        textAreaForBill = new JTextArea();
//        textAreaForBill.setBounds(10, 10, 416, 306);
//        scrollPaneBill = new JScrollPane(textAreaForBill);
//        scrollPaneBill.setBounds(525, 256, 455, 225);
//        frame.getContentPane().add(scrollPaneBill);

        btnAddtoorder = new JButton("Add To Order");
        btnAddtoorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String idOrder = getSelectedComboRol(comboBoxOrder);
                String idClient = getSelectedComboRol(comboBoxClient);
                String idProd = getSelectedComboRol(comboBoxProd);
                String quantity = QuantityField.getText();
                String mesaj;
                OrderBLL order;
                try {
                    order = new OrderBLL();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                Orders first = new Orders(Integer.parseInt(idOrder), Integer.parseInt(idClient));

                Orders ex= null;
                OrderDAO second= new OrderDAO();

                ex=second.findById(Integer.parseInt(idClient));

                if(ex==null) {
                    try {
                        order.insertOrder(first);
                    } catch (SQLException | IllegalAccessException exx) {
                        throw new RuntimeException(exx);
                    }
                }

                try {
                      mesaj=order.addAtOrder(first,Integer.parseInt(idProd),Integer.parseInt(quantity));
                } catch (SQLException | IllegalAccessException exx) {
                    throw new RuntimeException(exx);
                }

                if(mesaj!=null){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Produsul cu id-ul "+Integer.parseInt(idProd)+" a fost adaugat cu succes la comanda!");
                }
                else {
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Produsul cu id-ul "+Integer.parseInt(idProd)+" nu se poate adauga pe comanda! Stoc insuficient!");

                }

            }
        });
        btnAddtoorder.setBounds(978, 92, 116, 23);
        frame.getContentPane().add(btnAddtoorder);

        btnAddOrder = new JButton("Create Order");
        btnAddOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String Id=textField_1.getText();
                String Client=getSelectedComboRol(comboBox_2);

                try {
                    OrderBLL order= new OrderBLL();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Orders toAdd=new Orders(Integer.parseInt(Id),Integer.parseInt(Client));

                Orders ex= null;
                OrderDAO second= new OrderDAO();

                ex=second.findById(Integer.parseInt(Id));

                    try {
                        orderBLL.insertOrder(toAdd);
                        System.out.println("s a inserat");
                    } catch (SQLException | IllegalAccessException exx) {
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Comanda cu id-ul " + Integer.parseInt(Id) + " exista deja!");

                    }

                if(ex!=null) {
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Comanda cu id-ul " + Integer.parseInt(Id) + " exista deja!");
                }
            }
        });
        btnAddOrder.setBounds(978, 182, 116, 23);
        frame.getContentPane().add(btnAddOrder);


        btnViewOrders = new JButton("View Orders");
        btnViewOrders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                OrderBLL order;

                try {
                    order = new OrderBLL();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                OrdersProductsBLL ordprod;
                try {
                    ordprod = new OrdersProductsBLL();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                ResultSet resultSet = ordprod.findAllforTableOrdersProducts();

               ordprod.GenerateTableForOrdersProducts(table, resultSet);

            }
        });
        btnViewOrders.setBounds(157, 341, 116, 23);
        frame.getContentPane().add(btnViewOrders);

//        JButton btnImport;
//        btnImport = new JButton("Import");
//        btnImport.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Apelați metoda pentru importul fișierului .txt în tabelul Java
//
//                importTxtFile(textAreaForBill);
//            }
//        });
//        btnImport.setBounds(10, 341, 116, 23);
//        frame.getContentPane().add(btnImport);




    }
//    private void importTxtFile(JTextArea textArea) {
//        JFileChooser fileChooser = new JFileChooser();
//        int returnValue = fileChooser.showOpenDialog(null);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            try {
//                File selectedFile = fileChooser.getSelectedFile();
//                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
//                StringBuilder sb = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line);
//                    sb.append(System.lineSeparator());
//                }
//                textArea.setText(sb.toString());
//                reader.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
    public String getSelectedComboRol(JComboBox comboBox){
        if(comboBox.getSelectedItem().toString() == null)
            return "0";
        return comboBox.getSelectedItem().toString();
    }

    private boolean rowExists(DefaultTableModel model, Object[] rowData) {
        for (int row = 0; row < model.getRowCount(); row++) {
            boolean rowMatched = true;
            for (int column = 0; column < model.getColumnCount(); column++) {
                if (!rowData[column].equals(model.getValueAt(row, column))) {
                    rowMatched = false;
                    break;
                }
            }
            if (rowMatched) {
                return true;
            }
        }
        return false;
    }
}
