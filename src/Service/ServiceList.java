package Service;

import Service.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class ServiceList extends ArrayList<Service> {

    public void displayAll() {
        //1.
        /*
        for (int i = 0; i < size(); i++) {
            Service s = get(i);
            System.out.println(s);
        }
         */

        //2. Use pointer
        for (Service s : this) {
            System.out.println(s);
        }

        //3. Iterator
        /*
        Iterator<Service> it = iterator();
        while (it.hasNext()) {
            Service s = it.next();
            System.out.println(s);
        }
         */
    }

    public ArrayList<Service> searchByName(String name) {
        ArrayList<Service> resultServices = new ServiceList();

        for (Service s : this) {
            if (s.getName().contains(name)) {
                resultServices.add(s);
            }
        }

        return resultServices;
    }

    public Service findServiceById(int id) {
        Service resultServices = null;
        //

        for (Service s : this) {

            if (s.getId() == id) {
                resultServices = s;
                break;
            }
        }

        return resultServices;
    }

    public boolean updateService(int id) {
        Service resultService = findServiceById(id);

        if (resultService != null) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter new service's name: ");
            resultService.setName(sc.nextLine());
            System.out.print("Enter new service's price: ");
            resultService.setPrice(sc.nextInt());
            return true;
        } else {
            System.out.println("Service not found!");
            return false;
        }
    }

    public boolean deleteService(int id) {
        Service resultService = findServiceById(id);

        if (resultService != null) {
            remove(resultService);
            return true;
        } else {
            System.out.println("Service not found !");
            return false;
        }

    }

    public void sortByPrice() {
        Collections.sort(this);
    }

    public void sortByName() {
        Collections.sort(this, Service.tmp);
    }



}
