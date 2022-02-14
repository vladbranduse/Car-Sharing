package carsharing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

class Rental{
    JFrame f;
    Rental(String company, String customer) {
        f = new JFrame("Car Sharing");
        f.setSize(800, 800);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel l = new JLabel();
        l.setBounds(80, 800, 300, 200);
        int companyid = DataBase.nameid(company);
        DefaultListModel<String> list = DataBase.listOfCarsGUI(companyid);
        ArrayList<String> lc = Collections.list(list.elements());
        int i = 0;
        while (i < lc.size()) {
            int finalI = i;
            JButton b = new JButton(lc.get(i));
            b.setBounds(50, 50 + 100 * i, 250, 100);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                      DefaultListModel<Integer> list2 = DataBase.getRentedCarId();
                      ArrayList<Integer> lc2 = Collections.list(list2.elements());
                      int carid = DataBase.nameid2(lc.get(finalI));
                      int customerid = DataBase.customerid(customer);
                      if (lc2.contains(carid)) {
                          l.setText("The car is already rented!");
                      } else {
                          if (DataBase.rentACar(carid, customerid)) {
                              l.setText("The car was successfully rented!");
                          } else {
                              l.setText("There was an error in renting the car!");
                          }
                      }
                }
            });
            f.add(b);
            f.add(l);
            i++;
        }
        f.setLayout(null);
        f.setVisible(true);
    }
}

class rentACar {
    JFrame f;
    rentACar(String customer) {
        f = new JFrame("Car Sharing");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DefaultListModel<String> ls = DataBase.listOfCompaniesGUI();
        ArrayList<String> lc = Collections.list(ls.elements());
        int i = 0;
        while (i < lc.size()) {
            int finalI = i;
            JButton b = new JButton(lc.get(i));
            b.setBounds(50, 50 + 100 * i, 250, 100);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     f.setVisible(false);
                     new Rental(lc.get(finalI), customer);
                }
            });
            f.add(b);
            i++;
        }
        f.setLayout(null);
        f.setVisible(true);
    }
}

class returnACar {
    JFrame f;
    JLabel l;
    returnACar(String customer) {
        f = new JFrame("Car Sharing");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        l = new JLabel();
        l.setBounds(50, 150, 500, 35);
        if (DataBase.returnCar(customer)) {
            l.setText("The rented car was succesfully returned!");
        } else {
            l.setText("There was an error in returning the rented car!");
        }
        f.add(l);
        f.setLayout(null);
        f.setVisible(true);
    }
}

class myRentedCar{
    JFrame f;
    JLabel l;
    myRentedCar(String customer) {
        f = new JFrame("Car Sharing");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        l = new JLabel();
        l.setBounds(50, 150, 500, 35);
        String returnedCar = DataBase.getCustomersRentedCar(customer);
        l.setText("My rented car is " + returnedCar);
        f.add(l);
        f.setLayout(null);
        f.setVisible(true);
    }
}

public class CustomerAccount {
    JFrame f;
    JButton rentACar;
    JButton returnACar;
    JButton myRentedCar;
    int id;
    CustomerAccount(String customer) {
        f = new JFrame("Car Sharing");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rentACar = new JButton("Rent a car");
        rentACar.setBounds(50, 50, 200, 50);
        rentACar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 f.setVisible(false);
                 new rentACar(customer);
                 id = DataBase.customerid(customer);
            }
        });
        returnACar = new JButton("Return a car");
        returnACar.setBounds(50, 100, 200, 50);
        returnACar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new returnACar(customer);
            }
        });
        myRentedCar = new JButton("My rented car");
        myRentedCar.setBounds(50, 150, 200, 50);
        myRentedCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 f.setVisible(false);
                 new myRentedCar(customer);
            }
        });
        f.add(rentACar);
        f.add(returnACar);
        f.add(myRentedCar);
        f.setLayout(null);
        f.setVisible(true);
    }
}