/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import Order.Order;
import Order.OrderDetail;
import Service.Service;
import Service.ServiceList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import javafx.print.Collation;

/**
 *
 * @author Asus
 */
public class CustomerSet extends TreeSet<Customer> {

    public void disPlayAll() {
        for (Customer c : this) {
            System.out.println("--------------------");
            c.output();
            System.out.println("--------------------");
        }

    }

    public Customer searchCustbyId(long findId) {
        for (Customer c : this) {
            if (c.getId() == findId) {
                return c;
            }
        }
        return null;
    }

    public Customer getMostOrder() {
        int max = this.first().getOrderList().size();
        Customer reruls = null;
        for (Customer customer : this) {
            if (customer.getOrderList().size() > max) {
                max = customer.getOrderList().size();
                reruls = customer;
            }
        }
        return reruls;
    }

    public ArrayList<Order> getAllOrderByYear(String year) {
        ArrayList<Order> result = new ArrayList<>();
        for (Customer customer : this) {
            for (Order order : customer.getOrderList()) {
                String orderYear = order.getOrderDate().toString().split("\\s+")[5];
                // le duc    tu  -> le duc tu split("\\s+"," ")
                if (orderYear.equals(year)) {
                    result.add(order);
                }
            }
        }
        return result;

    }

    public int totalRevenueByQuarter(int quarter) {

        int total = 0;
        for (Customer customer : this) {
            for (Order order : customer.getOrderList()) {
                String orderMonth = order.getOrderDate().toString().split("\\s+")[1];

                if (quarter == 1) {
                    if (orderMonth.equals("Jan") || orderMonth.equals("Feb") || orderMonth.equals("Mar")) {
                        for (OrderDetail orderDetail : order.getDetailList()) {
                            total += orderDetail.getService().getPrice();
                        }
                    }

                } else if (quarter == 2) {
                    if (orderMonth.equals("Apr") || orderMonth.equals("May") || orderMonth.equals("Jun")) {
                        for (OrderDetail orderDetail : order.getDetailList()) {
                            total += orderDetail.getService().getPrice();
                        }
                    }
                } else if (quarter == 3) {
                    if (orderMonth.equals("Jul") || orderMonth.equals("Aug") || orderMonth.equals("Sep")) {
                        for (OrderDetail orderDetail : order.getDetailList()) {
                            total += orderDetail.getService().getPrice();
                        }
                    }
                } else if (quarter == 4) {
                    if (orderMonth.equals("Oct") || orderMonth.equals("Nov") || orderMonth.equals("Dec")) {
                        for (OrderDetail orderDetail : order.getDetailList()) {
                            total += orderDetail.getService().getPrice();
                        }
                    }
                }

            }

        }

        return total;
    }

    public ArrayList<Service> getAllService() {
        ArrayList<Service> listServiceUsed = new ArrayList<>();
        for (Customer customer : this) {
            for (Order order : customer.getOrderList()) {
                for (OrderDetail orderDetal : order.getDetailList()) {
                    Service service = orderDetal.getService();
                    listServiceUsed.add(service);
                }
            }
        }
        return listServiceUsed;
    }

    public HashMap<Integer, Integer> countsUsedService(ServiceList listOfService) {
        HashMap<Integer, Integer> result = new HashMap<>();

//        for (Service service : listOfService) {
//            result.put(service.getId(), 0);
//        }
        ArrayList<Service> serviceAll = this.getAllService();
        if (serviceAll.isEmpty()) {

            for (Service service : listOfService) {
                result.put(service.getId(), 0);
            }
        } else {
            for (Service service : serviceAll) {
                result.put(service.getId(), result.getOrDefault(service.getId(), 0) + 1);
            }

        }
        return result;
    }

    public ArrayList<Service> findUsedMostService(ServiceList listOfService) {
        ArrayList<Service> result = new ArrayList<>();
        HashMap<Integer, Integer> findMost = this.countsUsedService(listOfService);
        int max = findMost.get(listOfService.get(0).getId());
        for (int id : findMost.keySet()) {
            int count = findMost.get(id);
            if (count >= max) {
                max = count;
            }

        }

        for (int id : findMost.keySet()) {
            if (findMost.get(id) == max && max != 0) {
                for (Service service : listOfService) {
                    if (id == service.getId()) {
                        result.add(service);
                    }
                }
            }
        }

        return result;

    }

    public ArrayList<Service> findUnUsedMostService(ServiceList listOfService) {
        ArrayList<Service> result = new ArrayList<>();
        HashMap<Integer, Integer> findMost = this.countsUsedService(listOfService);
        for (Service service : listOfService) {
            if (!findMost.containsKey(service.getId()) || findMost.get(service.getId()) == 0) {
                result.add(service);
            }

        }


        return result;

    }

}
