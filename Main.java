package carsharing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

class MainInterface {
      JFrame f;
      JLabel username;
      JTextField userfield;
      JButton login;
      JButton exit;
      JLabel success;
      MainInterface() {
          f = new JFrame("Car Sharing");
          f.setSize(400, 400);
          f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          username = new JLabel("Username: ");
          userfield = new JTextField();
          login = new JButton("Login");
          exit = new JButton("Exit");
          success = new JLabel();
          username.setBounds(50, 150, 100, 30);
          userfield.setBounds(150, 150, 150, 30);
          login.setBounds(50, 200, 100, 30);
          exit.setBounds(50, 240, 100, 30);
          success.setBounds(60, 250, 200, 60);
          login.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String usertext;
                  usertext = userfield.getText();
                  if (usertext.equals("")) {
                      success.setText("Nothing in the userbox!");
                  } else {
                      if (usertext.equals("Admin")) {
                          f.setVisible(false);
                          new Manager();
                      } else {
                          ArrayList<String> customerList = DataBase.listOfCustomers();
                          if (customerList.contains(usertext)) {
                              f.setVisible(false);
                              new CustomerAccount(usertext);
                          } else {
                              if (DataBase.createCustomer(usertext)) {
                                  f.setVisible(false);
                                  new CustomerAccount(usertext);
                              } else {
                                  success.setText("The user failed to be registered!");
                              }
                          }
                      }
                  }
              }
          });
          exit.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  System.exit(0);
              }
          });
          f.add(username);
          f.add(userfield);
          f.add(login);
          f.add(exit);
          f.add(success);
          f.setLayout(null);
          f.setVisible(true);
      }
}

public class Main {

    public static void main(String[] args) {

        if (args.length > 0) {
            DataBase.DB_URL += args[1];
        }

        DataBase.createTables();
        new MainInterface();
    }
}