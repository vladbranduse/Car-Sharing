package carsharing;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Scanner;


public class Customer {

    void printCustomers(ArrayList<CustomerAccount> customers) {

        System.out.println("Customer list:");

        int id = 1;

        for (CustomerAccount customer : customers) {
            System.out.println(id + ". " + customer.name);
            id++;
        }
    }


}

