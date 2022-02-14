package carsharing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Company {


    public static void companyMenu(String companyName) {
        int companyid = DataBase.nameid(companyName);
        JFrame f;
        f = new JFrame("Car Sharing");
        f.setLayout(null);
        f.setSize(400, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(new JLabel());
        JComboBox carlist = new JComboBox<String>();
        JButton b1 = new JButton("Car list");
        b1.setBounds(50, 50, 300, 90);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  f.setVisible(false);
                  JFrame f2 = new JFrame("Car Sharing");
                  f2.setSize(400, 400);
                  f2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                  DefaultListModel<String> list = DataBase.listOfCarsGUI(companyid);
                  JList<String> l = new JList<>(list);
                  l.setBounds(50, 100, 150, 150);
                  f2.add(l);
                  JButton back = new JButton("Back");
                  back.setBounds(50, 300, 100, 90);
                  back.addActionListener(new ActionListener() {
                      @Override
                      public void actionPerformed(ActionEvent e) {
                          f2.setVisible(false);
                          f.setVisible(true);
                      }
                  });
                  f2.add(back);
                  f2.setLayout(null);
                  f2.setVisible(true);
            }
        });
        f.add(b1);
        JButton b2 = new JButton("Create a car");
        b2.setBounds(50, 150, 300, 90);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f1 = new JFrame("Car Sharing");
                f1.setSize(400, 400);
                f1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                JLabel l = new JLabel("Enter the name of the car here: ");
                l.setBounds(50, 50, 250, 30);
                JTextField t = new JTextField();
                t.setBounds(50, 100, 200, 30);
                JButton b = new JButton("Create");
                b.setBounds(50, 150, 95, 30);
                JButton back = new JButton("Back");
                back.setBounds(50, 200, 95, 30);
                JLabel l1 = new JLabel();
                l1.setBounds(50, 250, 200, 30);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String text = t.getText();
                        ArrayList<String> clist = DataBase.listOfCars(companyid);
                        ArrayList<String> alist = DataBase.listOfAllCars();
                        if (clist.contains(text) || alist.contains(text)) {
                            l1.setText("The car is already registered!");
                        } else {
                            if (DataBase.createCar(text, companyid)) {
                                l1.setText("The car was succesfully created!");
                            } else {
                                l1.setText("There was an error in creating the car!");
                            }
                        }
                    }
                });
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f1.setVisible(false);
                        f.setVisible(true);
                    }
                });
                f1.add(l);
                f1.add(t);
                f1.add(b);
                f1.add(back);
                f1.add(l1);
                f1.setLayout(null);
                f1.setVisible(true);
            }
        });
        f.add(b2);
        JButton back = new JButton("Back");
        back.setBounds(50, 250, 300, 90);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new ListOfCompanies();
            }
        });
        f.add(back);
        f.setLayout(null);
        f.setVisible(true);
    }
}