package org.jk.explore.java.Model;

public class Clients {

    private int id;

    private String name;

    private String address;

    private String email;

    private int age;

    public Clients() {

    }
    public Clients(int idClient, String name, String address, String email, int age) {
        this.id = idClient;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int idClient) {
        this.id = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
