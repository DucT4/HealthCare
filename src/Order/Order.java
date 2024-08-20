/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class Order {
    private long id;
    private Date orderDate;
    private ArrayList<OrderDetail> detailList;
    
    public Order () {
        id = System.currentTimeMillis();
        orderDate = new Date();
        detailList = new ArrayList<>();
    }

    public Order(long id, Date orderDate, ArrayList<OrderDetail> detailList) {
        this.id = id;
        this.orderDate = orderDate;
        this.detailList = detailList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<OrderDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(ArrayList<OrderDetail> detailList) {
        this.detailList = detailList;
    }
    
    public void exportOutput () {
        System.out.println("Order ID: " + this.id);
        System.out.println("Order date: " + this.orderDate);
        System.out.println("List detailed orders: ");
        for (OrderDetail orderDetail : this.detailList) {
            System.out.println(orderDetail);
        }
    }
    
    public boolean createDetail (OrderDetail o) {
        return this.detailList.add(o);
    }
}
