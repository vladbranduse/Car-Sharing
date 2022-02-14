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
        int companyid = DataBase.nameid(company);
        DefaultListModel<String> list = DataBase.listOfCarsGUI(companyid);
        ArrayList<String> lc = Collections.list(list.elements());
        JLabel l = new JLabel();
        l.setBounds(50, 60 + 100 * lc.size(), 300, 200);
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
            i++;
        }
        f.add(l);
        JButton back = new JButton("Back");
        back.setBounds(50, 50 + 100 * lc.size(), 250, 100);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new rentACar(customer);
            }
        });
        f.add(back);
        f.setLayout(null);
        f.setVisible(true);
    }
}

class rentACar {
    JFrame f;
    JLabel l;
    JButton back;
    rentACar(String customer) {
        f = new JFrame("Car Sharing");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        if (DataBase.checkRental(customer) != 0) {
            l = new JLabel();
            l.setBounds(50, 100, 500, 35);
            l.setText("You've already rented a car!");
            back = new JButton("Back");
            back.setBounds(50, 150, 100, 35);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.setVisible(false);
                    new CustomerAccount(customer);
                }
            });
            f.add(l);
            f.add(back);
        } else {
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
            back = new JButton("Back");
            back.setBounds(50, 50 + 100 * lc.size(), 250, 100);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.setVisible(false);
                    new CustomerAccount(customer);
                }
            });
            f.add(back);
        }
        f.setLayout(null);
        f.setVisible(true);
    }
}

class returnACar {
    JFrame f;
    JLabel l;
    JButton back;
    returnACar(String customer) {
        f = new JFrame("Car Sharing");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        if (DataBase.checkRental(customer) == 0) {
            l = new JLabel();
            l.setBounds(50, 100, 500, 35);
            l.setText("You didn't rent a car!");
            back = new JButton("Back");
            back.setBounds(50, 150, 100, 35);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.setVisible(false);
                    new CustomerAccount(customer);
                }
            });
            f.add(l);
            f.add(back);
        } else {
            l = new JLabel();
            l.setBounds(50, 150, 500, 35);
            if (DataBase.returnCar(customer)) {
                l.setText("The rented car was succesfully returned!");
            } else {
                l.setText("There was an error in returning the rented car!");
            }
            back = new JButton("Back");
            back.setBounds(50, 200, 200, 50);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.setVisible(false);
                    new CustomerAccount(customer);
                }
            });
            f.add(l);
            f.add(back);
        }
        f.setLayout(null);
        f.setVisible(true);
    }
}

class myRentedCar{
    JFrame f;
    JLabel l;
    JButton back;
    myRentedCar(String customer) {
        f = new JFrame("Car Sharing");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        l = new JLabel();
        l.setBounds(50, 150, 500, 35);
        back = new JButton("Back");
        back.setBounds(50, 200, 200, 50);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new CustomerAccount(customer);
            }
        });
        String returnedCar = DataBase.getCustomersRentedCar(customer);
        l.setText("Your rented car is " + returnedCar);
        f.add(l);
        f.add(back);
        f.setLayout(null);
        f.setVisible(true);
    }
}

public class CustomerAccount {
    JFrame f;
    JButton rentACar;
    JButton returnACar;
    JButton myRentedCar;
    JButton back;
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
        back = new JButton("Back");
        back.setBounds(50, 200, 200, 50);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new MainInterface();
            }
        });
        f.add(rentACar);
        f.add(returnACar);
        f.add(myRentedCar);
        f.add(back);
        f.setLayout(null);
        f.setVisible(true);
    }
}