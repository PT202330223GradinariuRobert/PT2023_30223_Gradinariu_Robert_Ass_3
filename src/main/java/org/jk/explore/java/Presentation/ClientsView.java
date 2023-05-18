package org.jk.explore.java.Presentation;

import org.jk.explore.java.BusinessLogic.ClientBLL;
import org.jk.explore.java.DataAccess.AbstractDAO;
import org.jk.explore.java.DataAccess.ClientDAO;
import org.jk.explore.java.Model.Clients;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ClientsView {

    JFrame frame;
    private JTextField IdFLD;
    private JTextField NameFLD;
    private JTextField AddressFLD;
    private JTextField EmailFLD;
    private JTextField AgeFLD;
    private JLabel IdLabel;
    private JLabel NameLabel;
    private JLabel AddressLabel;
    private JLabel EmailLabel;
    private JLabel AgeLabel;
    private JButton AddClientBtn;
    private JButton DeleteClientBtn;
    private JButton EditClientBtn;
    private JButton btnViewClients;
    private JTable table;
    private JScrollPane scrollPane;

    final ResultSet[] copy = new ResultSet[1];

    int ok=0;

    int allow=0;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientsView window = new ClientsView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClientsView() throws SQLException {
        initialize();
    }

    private void initialize() throws SQLException {
        frame = new JFrame();
        frame.setBounds(100, 100, 939, 394);
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

        JButton btnAddClient = new JButton("ADD");
        btnAddClient.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAddClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFieldsVisible(true);
            }
        });
        btnAddClient.setBounds(727, 23, 108, 23);
        frame.getContentPane().add(btnAddClient);

        JButton btnEditClient = new JButton("EDIT");
        btnEditClient.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEditClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                setFieldsVisible(true);
                //   IdFLD.setVisible(false);
                AddClientBtn.setVisible(false);
                EditClientBtn.setVisible(true);
                //IdLabel.setVisible(false);

            }
        });
        btnEditClient.setBounds(727, 63, 108, 23);
        frame.getContentPane().add(btnEditClient);

        JButton btnDeleteClient = new JButton("DELETE");
        btnDeleteClient.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnDeleteClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFieldsVisible(false);
                EditClientBtn.setVisible(false);
                DeleteClientBtn.setVisible(true);
                IdLabel.setVisible(true);
                IdFLD.setVisible(true);
            }
        });
        btnDeleteClient.setBounds(727, 105, 108, 23);
        frame.getContentPane().add(btnDeleteClient);

        IdFLD = new JTextField();
        IdFLD.setBounds(545, 24, 131, 20);
        frame.getContentPane().add(IdFLD);
        IdFLD.setColumns(10);
        NameFLD = new JTextField();
        NameFLD.setColumns(10);
        NameFLD.setBounds(545, 64, 131, 20);
        frame.getContentPane().add(NameFLD);

        AddressFLD = new JTextField();
        AddressFLD.setColumns(10);
        AddressFLD.setBounds(545, 106, 131, 20);
        frame.getContentPane().add(AddressFLD);

        EmailFLD = new JTextField();
        EmailFLD.setColumns(10);
        EmailFLD.setBounds(545, 149, 131, 20);
        frame.getContentPane().add(EmailFLD);

        AgeFLD = new JTextField();
        AgeFLD.setColumns(10);
        AgeFLD.setBounds(545, 195, 131, 20);
        frame.getContentPane().add(AgeFLD);

        IdLabel = new JLabel("ID:");
        IdLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        IdLabel.setBounds(451, 23, 84, 23);
        frame.getContentPane().add(IdLabel);

        NameLabel = new JLabel("Name:");
        NameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        NameLabel.setBounds(451, 63, 84, 23);
        frame.getContentPane().add(NameLabel);

        AddressLabel = new JLabel("Address:");
        AddressLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        AddressLabel.setBounds(451, 105, 84, 23);
        frame.getContentPane().add(AddressLabel);

        EmailLabel = new JLabel("Email:");
        EmailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        EmailLabel.setBounds(451, 149, 84, 23);
        frame.getContentPane().add(EmailLabel);

        AgeLabel = new JLabel("Age:");
        AgeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        AgeLabel.setBounds(451, 195, 84, 23);
        frame.getContentPane().add(AgeLabel);


        AddClientBtn = new JButton("Add Client");
        AddClientBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ClientBLL client= new ClientBLL();
                final String id=IdFLD.getText();
                System.out.println(id);
                final String Name=NameFLD.getText();
                System.out.println(Name);
                final String Address=AddressFLD.getText();
                System.out.println(Address);
                final String Email=EmailFLD.getText();
                System.out.println(Email);
                final String Age=AgeFLD.getText();
                System.out.println(Age);
                Clients newClient= new Clients(Integer.parseInt(id),Name,Address,Email,Integer.parseInt(Age));
                try {
                    client.insertClient(newClient);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                IdFLD.setText("");;
                NameFLD.setText("");
                AddressFLD.setText("");
                EmailFLD.setText("");
                AgeFLD.setText("");

            }
        });
        AddClientBtn.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        AddClientBtn.setBounds(518, 235, 108, 23);
        frame.getContentPane().add(AddClientBtn);

        EditClientBtn = new JButton("Edit Client");
        EditClientBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                ClientBLL client= new ClientBLL();
                final String id=IdFLD.getText();
                System.out.println(id);
                final String Name=NameFLD.getText();
                System.out.println(Name);
                final String Address=AddressFLD.getText();
                System.out.println(Address);
                final String Email=EmailFLD.getText();
                System.out.println(Email);
                final String Age=AgeFLD.getText();
                System.out.println(Age);
                Clients newClient= new Clients(Integer.parseInt(id),Name,Address,Email,Integer.parseInt(Age));
                try {
                    client.updateClient(newClient);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                IdFLD.setText("");;
                NameFLD.setText("");
                AddressFLD.setText("");
                EmailFLD.setText("");
                AgeFLD.setText("");
            }
        });
        EditClientBtn.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        EditClientBtn.setBounds(518, 235, 108, 23);
        frame.getContentPane().add(EditClientBtn);

        DeleteClientBtn = new JButton("Delete Client");
        DeleteClientBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientBLL client= new ClientBLL();
                final String id=IdFLD.getText();
                System.out.println(id);

                client.deleteClientById(Integer.parseInt(id));
                IdFLD.setText("");
            }
        });
        DeleteClientBtn.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        DeleteClientBtn.setBounds(518, 235, 108, 23);
        frame.getContentPane().add(DeleteClientBtn);

        btnViewClients = new JButton("VIEW CLIENTS");
        btnViewClients.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnViewClients.setBounds(727, 148, 108, 23);
        frame.getContentPane().add(btnViewClients);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 416, 306);
        frame.getContentPane().add(scrollPane);
        final DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);


        setFieldsVisible(false);
        EditClientBtn.setVisible(false);
        DeleteClientBtn.setVisible(false);


        btnViewClients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ClientBLL clientBLL= new ClientBLL();
                ResultSet resultSet=clientBLL.findAllforTableClients();

                clientBLL.GenerateTableForClients(table,resultSet);
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
    /**
    Seteaza atributele Field urile pe false astfel incat sa devina invizibile
    @param visible
     */
    private void setFieldsVisible(boolean visible) {
        IdFLD.setVisible(visible);
        NameFLD.setVisible(visible);
        AddressFLD.setVisible(visible);
        EmailFLD.setVisible(visible);
        AgeFLD.setVisible(visible);
        IdLabel.setVisible(visible);
        NameLabel.setVisible(visible);
        AddressLabel.setVisible(visible);
        EmailLabel.setVisible(visible);
        AgeLabel.setVisible(visible);
        AddClientBtn.setVisible(visible);
    }
}
