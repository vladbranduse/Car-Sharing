package carsharing;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        if (args.length > 0) {
            DataBase.DB_URL += args[1];
        }

        DataBase.createTables();
        Scanner scanner = new Scanner(System.in);
        Manager mg = new Manager();
        String username = "";
        String adminUsername = "Admin";
        System.out.println("Login");
        System.out.print("Username: ");
        username = scanner.nextLine();
        if (username.equals(adminUsername)) {
            mg.openManager();
        } else {
            ArrayList<CustomerAccount> listOfCustomers = DataBase.listOfCustomers();
            int notFound = 1;
            String cstName = "NULL";
            int cstId = 0;
            int cstRentedCarId = 0;
            for (CustomerAccount lc: listOfCustomers) {
                if (lc.name.equals(username)) {
                    notFound = 0;
                    break;
                }
            }
            if (notFound == 1) {
                DataBase.createCustomer(username);
            }
            for (CustomerAccount lc: listOfCustomers) {
                if (lc.name.equals(username)) {
                        cstId = lc.id;
                        cstName = lc.name;
                        cstRentedCarId = lc.rentedCarId;
                        break;
                    }
            }
            CustomerAccount currentCustomer = new CustomerAccount(cstId, cstName, cstRentedCarId);
            currentCustomer.customerAccountInteraction();
        }
    }
}