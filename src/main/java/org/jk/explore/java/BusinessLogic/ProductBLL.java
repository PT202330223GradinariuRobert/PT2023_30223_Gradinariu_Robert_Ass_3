package org.jk.explore.java.BusinessLogic;

import org.jk.explore.java.DataAccess.ProductDAO;
import org.jk.explore.java.Model.Products;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductBLL  {


    public ProductDAO productt;

    public ProductBLL(){

        productt= new ProductDAO();
    }
    /**
     Selecteaza toate produsele din tabela
     @return Products reprezentand produsele din tabel
     */
    public List<Products> selectAll() throws SQLException{

        return productt.findAll();
    }
    /**
     Selecteaza produsul cu id ul respectiv
     @param id
     @return Products
     */
    public Products selectById(int id){

        return productt.findById(id);
    }
    /**
     Insereaza produsul respectiv
     @param products
     @return Products
     */
    public Products insertProduct(Products products) throws SQLException, IllegalAccessException {

        return productt.insert(products);
    }
    /**
     Sterge produsul respectiv
     @param  id id
     */
    public void deleteProductById( int id){

        productt.delete(id);
    }
    /**
     Sterge produsul respectiv dupa nume
     @param name name
     */
    public void deleteProductByName( String name){

        productt.deleteByName(name);
    }
    /**
     Updateaza datele despre produs
     @param products
     */
    public void updateProduct(Products products) throws SQLException,IllegalAccessException{
        productt.update(products);
    }

    public ResultSet GenerateTableForProducts(JTable table, ResultSet resultSet){

        return productt.generateTable(table,resultSet);
    }

    public ResultSet findAllforTableProducts() {
        return productt.findAllforTable();
    }




}
