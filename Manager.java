package carsharing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

class ListOfCompanies {
    JFrame f;
    ListOfCompanies() {
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
                    Company.companyMenu(lc.get(finalI));
                }
            });
            f.add(b);
            i++;
        }
        f.setLayout(null);
        f.setVisible(true);
    }
}

class createACompany {
      createACompany() {
          JFrame f = new JFrame("Car Sharing");
          f.setSize(400, 400);
          f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          JLabel l = new JLabel("Enter the name of the company here: ");
          l.setBounds(50, 50, 250, 30);
          JTextField t = new JTextField();
          t.setBounds(50, 100, 200, 30);
          JButton b = new JButton("Create");
          b.setBounds(50, 150, 95, 30);
          JLabel l1 = new JLabel();
          l1.setBounds(50, 200, 100, 30);
          b.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String text = t.getText();
                  ArrayList<String> clist = DataBase.listOfCompanies();
                  if (clist.contains(text)) {
                      l1.setText("The company is already registered!");
                  } else {
                      if (DataBase.createCompany(text)) {
                          l1.setText("The company was succesfully created!");
                      } else {
                          l1.setText("There was an error in creating the company!");
                      }
                  }
              }
          });
          f.add(l);
          f.add(t);
          f.add(b);
          f.add(l1);
          f.setLayout(null);
          f.setVisible(true);
      }
}


class ListOfCustomers {
     ListOfCustomers() {
         JFrame f = new JFrame("Car Sharing");
         f.setSize(400, 400);
         f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         DefaultListModel<String> list = DataBase.listOfCustomersGUI();
         JList<String> l = new JList<>(list);
         l.setBounds(50, 100, 150, 150);
         f.add(l);
         f.setLayout(null);
         f.setVisible(true);
     }
}

public class Manager {
    JFrame f;
    JButton companyList;
    JButton createACompany;
    JButton customerList;
    Manager() {
        f = new JFrame("Car Sharing");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        companyList = new JButton("Company list");
        createACompany = new JButton("Create a company");
        customerList = new JButton("Customer list");
        companyList.setBounds(50, 50, 200, 50);
        createACompany.setBounds(50, 100, 200, 50);
        customerList.setBounds(50, 150, 200, 50);
        companyList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new ListOfCompanies();
            }
        });
        createACompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new createACompany();
            }
        });
        customerList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.setVisible(false);
               new ListOfCustomers();
            }
        });
        f.add(companyList);
        f.add(createACompany);
        f.add(customerList);
        f.setLayout(null);
        f.setVisible(true);
    }
}