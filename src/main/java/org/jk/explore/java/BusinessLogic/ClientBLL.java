package org.jk.explore.java.BusinessLogic;

import org.jk.explore.java.DataAccess.ClientDAO;
import org.jk.explore.java.Model.Clients;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientBLL {

    public ClientDAO clientt;

    public ClientBLL() {

        clientt= new ClientDAO();
    }
    /**
     Selecteaza toti clientii din tabela Clients

     @return O lista de Clienti
     */
    public List<Clients> selectAll() throws SQLException {

        return clientt.findAll();
    }
    /**
     Selecteaza clientul cu id ul "id"
     @param id ul furnizat
     @return un Client
     */
    public Clients selectById(int id){

        return clientt.findById(id);
    }
    /**
     Insereaza clientul
     @param  clients
     @return un Client
     */
    public Clients insertClient(Clients clients) throws SQLException, IllegalAccessException {

        return clientt.insert(clients);
    }

    /**
     Sterge clientul cu id ul "id"
     @param id ul furnizat
     */
    public void deleteClientById(int id ){

         clientt.delete(id);
    }
    /**
     Selecteaza clientul cu numele "name"
     @param name ul furnizat
     */
    public void deleteClientByName(String name){

        clientt.deleteByName(name);
    }
    /**
     Updateaza clientul
     @param  clients clients
     */
    public void updateClient(Clients clients) throws SQLException, IllegalAccessException {

        clientt.update(clients);
    }

    public ResultSet GenerateTableForClients(JTable table, ResultSet resultSet){

           return clientt.generateTable(table,resultSet);
    }

    public ResultSet findAllforTableClients() {
        return clientt.findAllforTable();
    }

}
