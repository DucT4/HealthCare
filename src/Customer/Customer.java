/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import Order.Order;
import Pet.Pet;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Customer implements Comparable<Customer> {

    private long id;
    private String name;
    private ArrayList<Pet> petList;
    private ArrayList<Order> orderList;

    public Customer() {
        this.id = System.currentTimeMillis();
        this.name = "Khach vang lai";
        this.orderList = new ArrayList<>();
        this.petList = new ArrayList<>();
    }

    public Customer(long id, String name, ArrayList<Pet> petList, ArrayList<Order> orderList) {
        this.id = id;
        this.name = name;
        this.petList = petList;
        this.orderList = orderList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Pet> getPetList() {
        return petList;
    }

    public void setPetList(ArrayList<Pet> petList) {
        this.petList = petList;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public void displayPetList() {
        for (Pet pet : this.petList) {
            System.out.println(pet);
        }
    }

    public boolean addPet(Pet pet) {
        return this.petList.add(pet);
    }

    public Pet findPetById(int id) {
        Pet result = null;
        for (Pet pet : this.petList) {
            if (pet.getId() == id) {
                result = pet;
            }
        }
        return result;
    }

    public void displayOrderList() {
        for (Order o : this.orderList) {
            o.exportOutput();
        }
    }

    public boolean addOrder(Order o) {
        return this.orderList.add(o);
    }

    public void exportInfoCustomer() {
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        displayOrderList();
        displayPetList();
    }

    public void output() {
        System.out.println("cusid:" + id);
        System.out.println("cusname:" + name);

    }

    @Override
    public int compareTo(Customer o) {
        if (this.id < o.getId()) {
            return 1;
        } else if (this.id > o.getId()) {
            return -1;
        }
        return 0;
    }
    
    
    
}
