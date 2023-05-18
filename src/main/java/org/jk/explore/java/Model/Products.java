package org.jk.explore.java.Model;

public class    Products {

    private int id;

    private String nameP;

    private int priceP;

    private int quantityP;

    public Products() {
    }

    public Products(int id, String nameP, int priceP, int quantityP) {
        this.id = id;
        this.nameP = nameP;
        this.priceP = priceP;
        this.quantityP = quantityP;
    }



    public int getId() {
        return id;
    }

    public void setId(int idProduct) {
        this.id = idProduct;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public int getPriceP() {
        return priceP;
    }

    public void setPriceP(int priceP) {
        this.priceP = priceP;
    }

    public int getQuantityP() {
        return quantityP;
    }

    public void setQuantityP(int quantityP) {
        this.quantityP = quantityP;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + id +
                ", nameP='" + nameP + '\'' +
                ", priceP=" + priceP +
                ", quantityP=" + quantityP +
                '}';
    }
}
