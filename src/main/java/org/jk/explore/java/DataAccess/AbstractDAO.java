package org.jk.explore.java.DataAccess;

import org.jk.explore.java.Connection.ConnectionFactory;
import org.jk.explore.java.Model.OrdersProducts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;


public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type =(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    /**
     Creeaza Query ul respectiv " SELECT * FROM type where TXT=?"
     @param TXT
     @return STRING
     */
    private String createSelectQuery(String TXT) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + TXT + " =?");
        return sb.toString();
    }



    /**
     Cauta un obiect de tipul T cu id ul respectiv
     @param id id
     @return T
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            List<T> objs=createObjects(resultSet);
            if(!objs.isEmpty())
            return objs.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     Creeaza o lista de obiecte T din tabela respectiva
     @param resultSet resultSet
     @return lista de obiecte t
     */
    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     Creeaza o lista cu toate  obiectele de tipul T din tabela respectiva
     @return lista de obiecte t
     */
    public List<T> findAll()  {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        StringBuilder aux= new StringBuilder();

        aux.append("SELECT ");
        aux.append(" * ");
        aux.append(" FROM ");
        aux.append(type.getSimpleName());
        System.out.println(aux.toString());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(aux.toString());
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        }
        catch(SQLException E){
            LOGGER.log(Level.WARNING,type.getName()+"DAO:findAll "+E.getMessage());
        }
        finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }
    /**
     Creeaza o lista de obiecte T din tabela respectiva

     @return resultset resultset
     */

    public ResultSet findAllforTable() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        StringBuilder aux = new StringBuilder();

        aux.append("SELECT ");
        aux.append(" * ");
        aux.append(" FROM ");
        aux.append(type.getSimpleName());
        System.out.println(aux.toString());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(aux.toString());
            resultSet = statement.executeQuery();

            return resultSet;
        } catch (SQLException E) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + E.getMessage());
        } finally {
          //  ConnectionFactory.close(resultSet);
          //  ConnectionFactory.close(statement);
          //  ConnectionFactory.close(connection);

            // Nu închideți aici resursele (resultSet, statement, connection)
        }

        return null;
    }
    /**
     Creeaza un Query pt inserare in tabel
     @param t t
     @return string
     */
    public String  insertQuery(Object t) throws IllegalAccessException {

        StringBuilder aux= new StringBuilder();
        aux.append("INSERT INTO ");
        aux.append(type.getSimpleName());
        aux.append("(");
        for(Field field:t.getClass().getDeclaredFields()){
            field.setAccessible(true);
            aux.append(field.getName()+",");
        }
        int start=aux.length()-1;
        int finall=aux.length();
        aux.delete(aux.length()-1,aux.length());
        aux.append(")");
        aux.append(" VALUES (");

        for(Field field:t.getClass().getDeclaredFields()){
            field.setAccessible(true);

            if(field.getType()== String.class){   /// facem diferenta intre string si numar
                aux.append("'"+field.get(t)+"'"+",");
            }
            else
            {
                aux.append(field.get(t)+",");
            }
        }
        aux.delete(aux.length()-1,aux.length());
        aux.append(")");

        return aux.toString();
    }

    /**
     Insereaza in tabel obiectul T
     @param t t
     @return T
     */

    public T insert(T t) throws IllegalAccessException, SQLException {
        if (t instanceof OrdersProducts) {
            Connection conn = null;
            PreparedStatement ps = null;
            String query = insertQuery(t);
            System.out.println(query);
            try {
                conn = ConnectionFactory.getConnection();
                ps = conn.prepareStatement(query);
                ps.executeUpdate(query);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
            } finally {
                ConnectionFactory.close(ps);
                ConnectionFactory.close(conn);
            }
            return t;
        } else {
            // Existing code for inserting other objects

            List<Integer> duplicates = new ArrayList<>();

            duplicates = findAllIds();

            Field idField;
            int ok = 0;
            try {
                idField = t.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                int id = (int) idField.get(t);
                if (duplicates.contains(id))
                    ok = 1;

                if (ok == 1) {
                    throw new IllegalArgumentException("ID ul pe care incerci sa-l adaugi exista deja!");
                }
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Obiectul nu conține câmpul 'id'.");
            }
            if (ok == 0) {
                Connection conn = null;
                PreparedStatement ps = null;
                System.out.println(insertQuery(t));
                String query = insertQuery(t);

                try {
                    conn = ConnectionFactory.getConnection();
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate(query);
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
                } finally {
                    ConnectionFactory.close(ps);
                    ConnectionFactory.close(conn);
                }
            }
            return t;
        }
    }
    /**
     Creeaza o lista de obiecte Integer din tabela respectiva cu toate id urile

     @return lista de Integer
     */
    public List<Integer> findAllIds() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Integer> idList = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT id FROM ");
        queryBuilder.append(type.getSimpleName());

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(queryBuilder.toString());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                idList.add(id);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAllIds " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return idList;
    }
    /**
     Sterge un obiect dintr o tabela
     */
     public void delete(int id) {

        Connection conn=null;

        PreparedStatement ps=null;

        ResultSet rs=null;

        StringBuilder aux= new StringBuilder();

        aux.append("DELETE FROM ");
        aux.append(type.getSimpleName());
        aux.append(" WHERE id="+id);

         System.out.println(aux.toString());
        try{
            conn=ConnectionFactory.getConnection();
            ps=conn.prepareStatement(aux.toString());
            ps.executeUpdate();
        }
        catch(SQLException e){
            LOGGER.log(Level.WARNING,type.getName()+"DAO:delete "+e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(ps);
            ConnectionFactory.close(conn);
        }
     }
    /**
     Creeaza un Query pentru update la tabela pe obiectul respectiv
     @param t t
     @return string
     */
    public String updateQuery(Object t) throws IllegalAccessException {
        StringBuilder aux = new StringBuilder();

        StringBuilder idString = new StringBuilder();
        aux.append("UPDATE ");
        aux.append(type.getSimpleName());
        aux.append(" SET ");

        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                aux.append(field.getName()).append("=");
                if (field.getType() == String.class) {
                    aux.append("'").append(field.get(t)).append("',");
                } else {
                    aux.append(field.get(t)).append(",");
                }
            } else {
                idString.append(field.get(t));  /// salvam id ul
            }
        }
        aux.delete(aux.length() - 1, aux.length());
        aux.append(" WHERE id=").append(idString);

        return aux.toString();
    }
    /**
     Face update la tabela pe respectivul obiect t
     @param t t
     @return T
     */
    public T update(T t) throws IllegalAccessException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = updateQuery(t);
        System.out.println(query);
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(query);
            ps.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(ps);
            ConnectionFactory.close(conn);
        }
        return t;
    }
    /**
     Primeste o lista de obiecte de tip ResultSet si genereaza headerele tabelului prin metoda reflexion si apoi le populeaza
     @param resultSet resultset, jtable table
     */
    public  ResultSet generateTable(JTable table, ResultSet resultSet) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (tableModel.getColumnCount() == 0) {
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    tableModel.addColumn(metaData.getColumnName(columnIndex));
                }
            }

            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

}
