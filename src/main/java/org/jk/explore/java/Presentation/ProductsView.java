package org.jk.explore.java.Presentation;

import org.jk.explore.java.BusinessLogic.ClientBLL;
import org.jk.explore.java.BusinessLogic.ProductBLL;
import org.jk.explore.java.DataAccess.ProductDAO;
import org.jk.explore.java.Model.Products;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ProductsView {

    public JFrame frame;
    private JTextField IdFLD;
    private JTextField NameFLD;
    private JTextField PriceFLD;
    private JTextField QuantityFLD;

    private JLabel IdLabel;
    private JLabel NameLabel;
    private JLabel PriceLabel;

    private JLabel QuantityLabel;

    private JButton AddProductBtn;
    private JButton DeleteProductBtn;
    private JButton EditProductBtn;
    private JButton btnViewProduct;
    private JTable table;
    private JScrollPane scrollPane;

    final ResultSet[] copy = new ResultSet[1];

    int ok=0;

    int allow=0;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductsView window = new ProductsView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ProductsView() throws SQLException {
        initialize();
    }

    private void initialize() throws SQLException {
        frame = new JFrame();
        frame.setBounds(100, 100, 879, 389);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

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
        btnNewButton.setBounds(746, 318, 89, 23);
        frame.getContentPane().add(btnNewButton);

        JButton btnAddProduct = new JButton("ADD");
        btnAddProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAddProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFieldsVisible(true);
            }
        });
        btnAddProduct.setBounds(727, 23, 108, 23);
        frame.getContentPane().add(btnAddProduct);

        JButton btnEditProduct = new JButton("EDIT");
        btnEditProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEditProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                setFieldsVisible(true);
                //   IdFLD.setVisible(false);
                AddProductBtn.setVisible(false);
                DeleteProductBtn.setVisible(false);
                EditProductBtn.setVisible(true);
                //IdLabel.setVisible(false);

            }
        });
        btnEditProduct.setBounds(727, 63, 108, 23);
        frame.getContentPane().add(btnEditProduct);

        JButton btnDeleteProduct = new JButton("DELETE");
        btnDeleteProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnDeleteProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFieldsVisible(false);
                EditProductBtn.setVisible(false);
                AddProductBtn.setVisible(false);
                DeleteProductBtn.setVisible(true);
                IdLabel.setVisible(true);
                IdFLD.setVisible(true);
            }
        });
        btnDeleteProduct.setBounds(727, 105, 108, 23);
        frame.getContentPane().add(btnDeleteProduct);

        IdFLD = new JTextField();
        IdFLD.setBounds(545, 24, 131, 20);
        frame.getContentPane().add(IdFLD);
        IdFLD.setColumns(10);
        NameFLD = new JTextField();
        NameFLD.setColumns(10);
        NameFLD.setBounds(545, 64, 131, 20);
        frame.getContentPane().add(NameFLD);

        PriceFLD = new JTextField();
        PriceFLD.setColumns(10);
        PriceFLD.setBounds(545, 106, 131, 20);
        frame.getContentPane().add(PriceFLD);

        QuantityFLD = new JTextField();
        QuantityFLD.setColumns(10);
        QuantityFLD.setBounds(545, 149, 131, 20);
        frame.getContentPane().add(QuantityFLD);



        IdLabel = new JLabel("ID:");
        IdLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        IdLabel.setBounds(451, 23, 84, 23);
        frame.getContentPane().add(IdLabel);

        NameLabel = new JLabel("Name:");
        NameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        NameLabel.setBounds(451, 63, 84, 23);
        frame.getContentPane().add(NameLabel);

        PriceLabel = new JLabel("Price:");
        PriceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        PriceLabel.setBounds(451, 105, 84, 23);
        frame.getContentPane().add(PriceLabel);

        QuantityLabel = new JLabel("Qauntity:");
        QuantityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        QuantityLabel.setBounds(451, 149, 84, 23);
        frame.getContentPane().add(QuantityLabel);

        AddProductBtn = new JButton("Add Product");
        AddProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ProductBLL product= new ProductBLL();
                final String id=IdFLD.getText();

                final String Name=NameFLD.getText();

                final String Price=PriceFLD.getText();

                final String Quantity=QuantityFLD.getText();
                System.out.println(Quantity);
Products p= new Products(Integer.parseInt(id),Name,Integer.parseInt(Price),Integer.parseInt(Quantity));
                try {
                    product.insertProduct(p);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        AddProductBtn.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        AddProductBtn.setBounds(518, 235, 108, 23);
        frame.getContentPane().add(AddProductBtn);

        EditProductBtn = new JButton("Edit Prod");
        EditProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ProductBLL product= new ProductBLL();
                final String id=IdFLD.getText();

                final String Name=NameFLD.getText();

                final String Price=PriceFLD.getText();

                final String Quantity=QuantityFLD.getText();
                System.out.println(Quantity);
                Products p= new Products(Integer.parseInt(id),Name,Integer.parseInt(Price),Integer.parseInt(Quantity));
                try {
                    product.updateProduct(p);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                IdFLD.setText("");

                NameFLD.setText("");

                PriceFLD.setText("");

                QuantityFLD.setText("");
            }
        });
        EditProductBtn.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        EditProductBtn.setBounds(518, 235, 108, 23);
        frame.getContentPane().add(EditProductBtn);

        DeleteProductBtn = new JButton("Delete Product");
        DeleteProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ProductBLL product= new ProductBLL();
                final String id=IdFLD.getText();

                    product.deleteProductById(Integer.parseInt(id));

                IdFLD.setText("");
            }
        });
        DeleteProductBtn.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        DeleteProductBtn.setBounds(518, 235, 108, 23);
        frame.getContentPane().add(DeleteProductBtn);

        btnViewProduct = new JButton("VIEW PROD");
        btnViewProduct.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnViewProduct.setBounds(727, 148, 108, 23);
        frame.getContentPane().add(btnViewProduct);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 416, 306);
        frame.getContentPane().add(scrollPane);
        final DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);

        // Ascundeți câmpurile de text și etichetele inițial
        setFieldsVisible(false);
        EditProductBtn.setVisible(false);
        DeleteProductBtn.setVisible(false);


        btnViewProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {




                ProductBLL productBLL= new ProductBLL();
                ResultSet resultSet=productBLL.findAllforTableProducts();

                productBLL.GenerateTableForProducts(table,resultSet);
//                // Obțineți referința către modelul tabelului
//                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//                tableModel.setRowCount(0); // Ștergeți toate rândurile existente din tabel
//
//                ProductDAO productDAO = new ProductDAO();
//                try {
//                    ResultSet resultSet = productDAO.findAllforTable();
//                    ResultSetMetaData metaData = resultSet.getMetaData();
//                    int columnCount = metaData.getColumnCount();
//
//                    // Adăugați numele coloanelor în modelul tabelului dacă nu există deja
//                    if (tableModel.getColumnCount() == 0) {
//                        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
//                            tableModel.addColumn(metaData.getColumnName(columnIndex));
//                        }
//                    }
//
//                    // Parcurgeți rezultatele interogării și adăugați fiecare rând în tabel
//                    while (resultSet.next()) {
//                        Object[] rowData = new Object[columnCount];
//                        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
//                            rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
//                        }
//                        tableModel.addRow(rowData);
//                    }
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
            }
        });
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
    private void setFieldsVisible(boolean visible) {
        IdFLD.setVisible(visible);
        NameFLD.setVisible(visible);
       PriceFLD.setVisible(visible);
        QuantityFLD.setVisible(visible);
        IdLabel.setVisible(visible);
        NameLabel.setVisible(visible);
       PriceLabel.setVisible(visible);
        QuantityLabel.setVisible(visible);
        AddProductBtn.setVisible(visible);
        EditProductBtn.setVisible(visible);
        DeleteProductBtn.setVisible(visible);
    }
}
