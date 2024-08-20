
import Customer.Customer;
import Customer.CustomerSet;
import Order.Order;
import Order.OrderDetail;
import Pet.Pet;
import Service.ServiceList;
import Service.Service;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        ServiceList listOfServices = new ServiceList();
        listOfServices.add(new Service(100, 500, "Massage"));
        listOfServices.add(new Service(101, 300, "Tia mong"));
        listOfServices.add(new Service(102, 700, "Tam cho thu cung"));
        listOfServices.add(new Service(103, 200, "Tia long"));
        CustomerSet custSet = new CustomerSet();
        do {
            System.out.println("1. Manage services");
            System.out.println("2. Manage customer and orders");
            System.out.println("3. Report");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    int choice2;
                    do {
                        System.out.println("1. Add service");
                        System.out.println("2. Display service");
                        System.out.println("3. Update service");
                        System.out.println("4. Delete service");
                        System.out.println("5. Search service");
                        System.out.print("Enter choice: ");
                        choice2 = sc.nextInt();

                        switch (choice2) {
                            case 1:
                                int id,
                                 price;
                                String name = null;
                                System.out.print("Enter ID: ");
                                id = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter name: ");
                                name = sc.nextLine();
                                System.out.print("Enter price: ");
                                price = sc.nextInt();
                                Service s = new Service(id, price, name);

                                System.out.println(listOfServices.add(s) ? "Added successfully!!!" : "Failed add!!!");
                                break;
                            case 2:
                                System.out.println("1. Sort by price");
                                System.out.println("2. Sort by name");
                                System.out.print("Enter 1 or 2: ");
                                int sortChoice = sc.nextInt();
                                if (sortChoice == 1) {
                                    listOfServices.sortByPrice();
                                } else if (sortChoice == 2) {
                                    listOfServices.sortByName();
                                }
                                listOfServices.displayAll();
                                break;
                            case 3:
                                int updateId;
                                System.out.print("Enter ID to update: ");
                                updateId = sc.nextInt();
                                boolean resultUpdate = listOfServices.updateService(updateId);
                                if (resultUpdate == true) {
                                    System.out.println("Updated!!!");
                                } else {
                                    System.out.println("Failed!!!");
                                }
                                break;
                            case 4:
                                int deleteServiceId;
                                System.out.print("Enter ID to delete: ");
                                deleteServiceId = sc.nextInt();
                                boolean resultDelete = listOfServices.deleteService(deleteServiceId);

                                if (resultDelete) {
                                    System.out.println("Deleted successfully!");
                                } else {
                                    System.out.println("Deleted failed!1");
                                }
                                break;
                            case 5:
                                System.out.println("1. Search by name");
                                System.out.println("2. Search by id");
                                System.out.print("Enter 1 or 2: ");
                                int choice3 = sc.nextInt();

                                switch (choice3) {
                                    case 1:
                                        sc.nextLine();
                                        String searchName = null;
                                        System.out.println("Enter name to finds: ");
                                        searchName = sc.nextLine();

                                        ArrayList<Service> resultSearchByName = listOfServices.searchByName(searchName);

                                        if (resultSearchByName.isEmpty()) {
                                            System.out.println("Not found!!!");
                                        } else {
                                            System.out.println("List of services that you search: ");
                                            ((ServiceList) resultSearchByName).displayAll();
                                        }
                                        break;
                                    case 2:
                                        int findId;
                                        System.out.print("Enter ID:");
                                        findId = sc.nextInt();

                                        Service resultFindById = listOfServices.findServiceById(findId);
                                        if (resultFindById == null) {
                                            System.out.println("Not found");
                                        } else {
                                            System.out.println("The service you found: ");
                                            System.out.println(resultFindById);
                                        }
                                        break;
                                }
                                break;
                        }
                    } while (choice2 <= 4);
                    break;
                case 2: //Manage customer and order
                    int choiceCusAndOrd;

                    do {
                        System.out.println("1. Create new customer");
                        System.out.println("2. Display customers");
                        System.out.println("3. Search customer by id");
                        System.out.print("Enter choice: ");
                        choiceCusAndOrd = sc.nextInt();
                        switch (choiceCusAndOrd) {
                            case 1:
                                Customer customer = new Customer();
                                String continueAdd = "y";
                                System.out.println("Them thong tin pet cho khach hang:");
                                do {
                                    System.out.print("Enter pet ID: ");
                                    int petId = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter pet name: ");
                                    String petName = sc.nextLine();

                                    Pet pet = new Pet(petId, petName);
                                    System.out.println(customer.addPet(pet) ? "Added successfully!" : "Added failed!");

                                    System.out.print("Do you want to add others pet? (y/n): ");
                                    continueAdd = sc.nextLine();
                                } while (continueAdd.equalsIgnoreCase("y"));

                                System.out.println("Quy trinh tao don hang:");
                                String answer = "y";
                                do {
                                    System.out.println("Tao order: ");
                                    Order order = new Order();
                                    continueAdd = "y";

                                    do {
                                        System.out.println("Choose your pet within your pet list: ");
                                        customer.displayPetList();

                                        System.out.print("Enter pet id to order: ");
                                        int petIdOrder = sc.nextInt();
                                        Pet pet = customer.findPetById(petIdOrder);
                                        System.out.println("List of services of system: ");
                                        listOfServices.displayAll();
                                        System.out.print("Enter service ID to order: ");
                                        int idService = sc.nextInt();
                                        sc.nextLine();
                                        Service service_order = listOfServices.findServiceById(idService);

                                        if (pet != null && service_order != null) {
                                            OrderDetail detail = new OrderDetail(pet, service_order);
                                            order.createDetail(detail);
                                            System.out.println("Added detail successfully!");
                                        } else {
                                            System.out.println("Service or Pet not found!");
                                        }

                                        System.out.print("Add others orders: ");
                                        continueAdd = sc.nextLine();
                                    } while (continueAdd.equalsIgnoreCase("y"));
                                    System.out.println(customer.addOrder(order) ? "Add order successfully!" : "Failed add!");
                                    custSet.add(customer);
                                    System.out.print("Add others: ");
                                    answer = sc.nextLine();
                                } while (answer.equalsIgnoreCase("y"));

                                customer.exportInfoCustomer();
                                break;
                            case 2:
                                System.out.println("Create customer of system:");
                                custSet.disPlayAll();

                                break;
                            case 3:
                                long id_search;
                                System.out.print("Enter id:");
                                id_search = sc.nextLong();
                                System.out.println(custSet.searchCustbyId(id_search));
                                break;
                        }
                    } while (choiceCusAndOrd <= 3);
                    break;
                case 3:
                    int choiceRe;
                    do {
                        System.out.println("1. hien thi so kh cua he thong");
                        System.out.println("2. hien thi kh co nhieu don hang nhat");
                        System.out.println("3. hien thi don hang khanh hang da dat trong nam");
                        System.out.println("4. service dc dat(su dung) nhieu nhat");
                        System.out.println("5. service chua co ai su dung");
                        System.out.println("6. hien thi doanh thu cua he thong thong theo tung quy");
                        System.out.print("Enter choice:");
                        choiceRe = sc.nextInt();
                        switch (choiceRe) {
                            case 1:
                                custSet.disPlayAll();
                                System.out.println("so luong khach hang he thong: " + custSet.size());

                                break;

                            case 2:

                                Customer custWithMostOrder = custSet.getMostOrder();
                                System.out.println("khach hang co don hang nhieu nhat:");
                                custWithMostOrder.exportInfoCustomer();
                                break;
                            case 3:
                                System.out.println("3. hien thi don hang khanh hang da dat trong nam");
                                System.out.print("Nhap nam can in order: ");
                                sc.nextLine();
                                String year = sc.nextLine();
                                ArrayList<Order> ordersByYear = custSet.getAllOrderByYear(year);
                                if (!ordersByYear.isEmpty()) {
                                    for (Order order : ordersByYear) {
                                        order.exportOutput();
                                    }
                                } else {
                                    System.out.println("e khach!!!!");
                                }

                                break;
                            case 4:
                                System.out.println("4. service dc dat(su dung) nhieu nhat");
                                ArrayList<Service> mostUsedService = custSet.findUsedMostService(listOfServices);
                                for (Service service : mostUsedService) {
                                    System.out.println(service);
                                }
                                break;
                            case 5:
                                System.out.println("5. service chua co ai su dung");
                                ArrayList<Service> mostUnUsedService = custSet.findUnUsedMostService(listOfServices);
                                if (mostUnUsedService.isEmpty()) {
                                    System.out.println("Khong co dich vu nao chua qua su dung");
                                } else {
                                    for (Service service : mostUnUsedService) {
                                        System.out.println(service);
                                    }
                                }
                                break;
                            case 6:
                                System.out.println("6. hien thi doanh thu cua he thong thong theo tung quy");
                                int nhap_quy;
                                System.out.print("Nhap quy can tinh doanh thu:");
                                nhap_quy = sc.nextInt();
                                System.out.println("Doanh thu quy " + nhap_quy + ":" + custSet.totalRevenueByQuarter(nhap_quy));

                                break;
                        }

                    } while (choiceRe <= 6);

                    break;
            }
        } while (choice <= 3);
    }
}
