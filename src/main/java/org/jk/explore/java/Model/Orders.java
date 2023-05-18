package org.jk.explore.java.Model;

public class Orders {

    private int id;

    private int clientId;

    public Orders(){

    }
    public Orders(int id, int clientId) {
        this.id = id;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int idOrder) {
        this.id = idOrder;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }



    @Override
    public String toString() {
        return "Orders{" +
                "idOrder=" + id +
                ", clientId=" + clientId
                +
                '}';
    }
}
