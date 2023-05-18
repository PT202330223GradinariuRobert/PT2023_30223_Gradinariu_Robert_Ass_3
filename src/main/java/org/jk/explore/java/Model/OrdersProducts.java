package org.jk.explore.java.Model;

public class OrdersProducts {


   private  int orderId;

    private int productId;

    private int quantity;


    public OrdersProducts() {
    }

    public OrdersProducts( int orderId, int productId, int quantity) {

        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }




    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrdersProducts{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
