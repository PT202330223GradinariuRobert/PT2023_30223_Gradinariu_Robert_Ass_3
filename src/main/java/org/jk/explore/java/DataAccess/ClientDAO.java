package org.jk.explore.java.DataAccess;

import org.jk.explore.java.Model.Clients;
import org.jk.explore.java.Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ClientDAO extends AbstractDAO<Clients> {

    public ClientDAO(){

    }
    /**
    Creeaza o lista de Clienti caracterizata de nume
     @param name name
     @return lista de Clients
     */
    public List<Clients> findByName(String name){

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet resultSet=null;

        StringBuilder aux= new StringBuilder();

        aux.append("SELECT * FROM ");
        aux.append(Clients.class.getSimpleName());

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
    Sterge clientul cu numele respectiv
     @param name name
     */
    public void deleteByName(String name) {

        Connection conn=null;

        PreparedStatement ps=null;

        ResultSet rs=null;

        StringBuilder aux= new StringBuilder();

        aux.append("DELETE FROM ");
        aux.append(Clients.class.getSimpleName());
        aux.append(" WHERE name=");
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
            LOGGER.log(Level.WARNING, Clients.class.getName()+"DAO:delete "+e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(ps);
            ConnectionFactory.close(conn);
        }
    }
}
