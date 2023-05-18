package org.jk.explore.java.DataAccess;

import org.jk.explore.java.Connection.ConnectionFactory;
import org.jk.explore.java.Model.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ProductDAO extends AbstractDAO<Products> {

    public ProductDAO() {

    }
    /**
     Creeaza o lista de Products caracterizata de nume
     @param name name
     @return lista de produse
     */
    public List<Products> findByName(String name){

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet resultSet=null;

        StringBuilder aux= new StringBuilder();

        aux.append("SELECT * FROM ");
        aux.append(Products.class.getSimpleName());

        try{
            conn= ConnectionFactory.getConnection();
            ps=conn.prepareStatement(aux.toString());
            ps.setString(1,name);

            return createObjects(resultSet);

        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(ps);
            ConnectionFactory.close(conn);
        }
    }
    /**
     Sterge produsul cu numele respectiv
     @param name name
     */
    public void deleteByName(String name) {

        Connection conn=null;

        PreparedStatement ps=null;

        ResultSet rs=null;

        StringBuilder aux= new StringBuilder();

        aux.append("DELETE FROM ");
        aux.append(Products.class.getSimpleName());
        aux.append(" WHERE nameP=");
        aux.append("'");
        aux.append(name);
        aux.append("'");
        System.out.println(aux.toString());

        try{
            conn=ConnectionFactory.getConnection();
            ps=conn.prepareStatement(aux.toString());
            ps.executeUpdate();
        }
        catch(SQLException e){
            LOGGER.log(Level.WARNING, Products.class.getName()+"DAO:delete "+e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(ps);
            ConnectionFactory.close(conn);
        }
    }


}