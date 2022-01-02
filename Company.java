package carsharing;

import java.util.ArrayList;
import java.util.Scanner;

public class Company {

    int id;
    String name;

    public Company(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public Company(Company company) {

        this.id = company.id;
        this.name = company.name;
    }

    void companyCarFleetInteraction() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            printMenu();
            int selection = Integer.parseInt(scanner.nextLine());
            System.out.println();

            switch (selection) {

                case 1:

                    ArrayList<Car> carList = DataBase.listOfCars(this.id);

                    if (carList.size() == 0) {
                        System.out.println("The car list is empty!");
                        System.out.println();
                    } else {
                        printCars(carList);
                        System.out.println();
                    }
                    break;

                case 2:

                    System.out.println("Enter the car name:");
                    String carName = scanner.nextLine();
                    DataBase.createCar(carName, this.id);
                    break;

                case 0:
                    return;
            }
        }
    }

    void printMenu() {
        System.out.println(name + " company");
        System.out.println(
                "1. Car list\n" +
                        "2. Create a car\n" +
                        "0. Back");
    }

    static void printCars(ArrayList<Car> carList) {

        int i = 1;
        for (Car car : carList) {
            System.out.println(i + ". " + car.name);
            i++;
        }
    }
}