package carsharing;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class DataBase {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static String DB_URL = "jdbc:h2:./src/carsharing/db";

    static void createTables() {

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String sql = "CREATE TABLE IF NOT EXISTS COMPANY (" +
                            "ID INT PRIMARY KEY AUTO_INCREMENT," +
                            "NAME VARCHAR NOT NULL UNIQUE" +
                            ");" +
                            "CREATE TABLE IF NOT EXISTS CAR (" +
                            "ID INT PRIMARY KEY AUTO_INCREMENT," +
                            "NAME VARCHAR NOT NULL UNIQUE," +
                            "COMPANY_ID INT NOT NULL," +
                            "CONSTRAINT fk_companyId FOREIGN KEY (COMPANY_ID)" +
                            "REFERENCES COMPANY(ID)" +
                            ");" +
                            "CREATE TABLE IF NOT EXISTS CUSTOMER (" +
                            "ID INT PRIMARY KEY AUTO_INCREMENT," +
                            "NAME VARCHAR NOT NULL UNIQUE," +
                            "RENTED_CAR_ID INT DEFAULT NULL," +
                            "CONSTRAINT fk_rentId FOREIGN KEY (RENTED_CAR_ID)" +
                            "REFERENCES CAR(ID)" +
                            ");";

                    statement.executeUpdate(sql);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static boolean createCompany(String companyName) {

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String sql = "INSERT INTO COMPANY(NAME) VALUES('" + companyName + "');";
                    statement.executeUpdate(sql);
                    System.out.println();
                }
            }
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static DefaultListModel listOfCompaniesGUI() {

        DefaultListModel dm = new DefaultListModel();

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String countQuery = "SELECT COUNT(*) FROM COMPANY";
                    var res = statement.executeQuery(countQuery);
                    int count = -1;

                    while (res.next()) {
                        count = res.getInt(1);
                    }

                    if (count > 0) {

                        String listQuery = "SELECT * FROM COMPANY";
                        res = statement.executeQuery(listQuery);

                        while (res.next()) {
                            String name = res.getString(2);
                            dm.addElement(name);
                        }
                    }
                }
            }
            return dm;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<String> listOfCompanies() {

        ArrayList<String> companyList = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String countQuery = "SELECT COUNT(*) FROM COMPANY";
                    var res = statement.executeQuery(countQuery);
                    int count = -1;

                    while (res.next()) {
                        count = res.getInt(1);
                    }

                    if (count > 0) {

                        String listQuery = "SELECT * FROM COMPANY";
                        res = statement.executeQuery(listQuery);

                        while (res.next()) {
                            String companyName = res.getString(2);
                            companyList.add(companyName);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    static int nameid(String companyName) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String query = "SELECT ID FROM COMPANY WHERE NAME LIKE '%" + companyName + "%' ;";
                    var res = statement.executeQuery(query);
                    while (res.next()) {
                          int nid = res.getInt("ID");
                          return nid;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    static boolean createCar(String name, int companyId) {

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {
                    String sql = "INSERT INTO CAR(NAME, COMPANY_ID) VALUES('" + name + "', " + companyId + ");";
                    statement.executeUpdate(sql);
                }
            }
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static DefaultListModel listOfCarsGUI(int companyId) {

        DefaultListModel dm = new DefaultListModel();

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String countQuery = "SELECT COUNT(*) FROM CAR";
                    var res = statement.executeQuery(countQuery);
                    int count = -1;

                    while (res.next()) {
                        count = res.getInt(1);
                    }

                    if (count > 0) {
                        String listQuery = "SELECT * " +
                                "FROM CAR " +
                                "WHERE COMPANY_ID = " + companyId + ";";
                        res = statement.executeQuery(listQuery);

                        while (res.next()) {
                            String name = res.getString(2);
                            dm.addElement(name);
                        }
                    }
                }
            }
            return dm;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<String> listOfCars(int companyId) {

        ArrayList<String> carList = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String countQuery = "SELECT COUNT(*) FROM CAR";
                    var res = statement.executeQuery(countQuery);
                    int count = -1;

                    while (res.next()) {
                        count = res.getInt(1);
                    }

                    if (count > 0) {
                        String listQuery = "SELECT * " +
                                "FROM CAR " +
                                "WHERE COMPANY_ID = " + companyId + ";";
                        res = statement.executeQuery(listQuery);

                        while (res.next()) {
                            carList.add(res.getString(2));
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    static boolean createCustomer(String name) {

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String sql = "INSERT INTO CUSTOMER(NAME) VALUES('" + name + "');";
                    statement.executeUpdate(sql);
                }
            }
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static DefaultListModel listOfCustomersGUI() {

        DefaultListModel dm = new DefaultListModel();
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String countQuery = "SELECT COUNT(*) FROM CUSTOMER;";
                    var res = statement.executeQuery(countQuery);
                    int count = -1;

                    while (res.next()) {
                        count = res.getInt(1);
                    }

                    if (count > 0) {

                        String listQuery = "SELECT * FROM CUSTOMER";
                        res = statement.executeQuery(listQuery);

                        while (res.next()) {

                            String name = res.getString(2);
                            dm.addElement(name);
                        }
                    }
                }
            }
            return dm;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<String> listOfCustomers() {

        ArrayList<String> customers = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String countQuery = "SELECT COUNT(*) FROM CUSTOMER;";
                    var res = statement.executeQuery(countQuery);
                    int count = -1;

                    while (res.next()) {
                        count = res.getInt(1);
                    }

                    if (count > 0) {

                        String listQuery = "SELECT * FROM CUSTOMER";
                        res = statement.executeQuery(listQuery);

                        while (res.next()) {

                            String name = res.getString(2);
                            customers.add(name);
                        }
                    }
                }
            }
            return customers;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    static int customerid(String customerName) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String query = "SELECT ID FROM CUSTOMER WHERE NAME LIKE '%" + customerName + "%' ;";
                    var res = statement.executeQuery(query);
                    while (res.next()) {
                        int nid = res.getInt("ID");
                        return nid;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    static int nameid2(String carName) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String query = "SELECT ID FROM CAR WHERE NAME LIKE '%" + carName + "%' ;";
                    var res = statement.executeQuery(query);
                    while (res.next()) {
                        int nid = res.getInt("ID");
                        return nid;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    static DefaultListModel getRentedCarId() {

        DefaultListModel dm = new DefaultListModel();

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String sql = "SELECT RENTED_CAR_ID FROM CUSTOMER";
                    var res = statement.executeQuery(sql);
                    while (res.next()) {
                            int id = res.getInt("RENTED_CAR_ID");
                            dm.addElement(id);
                    }
                }
            }
            return dm;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static boolean rentACar(int carId, int customerId) {

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    String sql = "UPDATE CUSTOMER SET RENTED_CAR_ID = " + carId + " WHERE ID = " + customerId;
                    statement.executeUpdate(sql);
                }
            }
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    static String getCustomersRentedCar(String customerName) {

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    int customerId = customerid(customerName);
                    String query = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID=" + customerId + ";";
                    ResultSet res = statement.executeQuery(query);

                    String carName = "NULL";
                    String companyName = "NULL";
                    int companyId = 0;
                    int rentedCarId = 0;

                    while (res.next()) {

                        if (res.getObject(1) == null) {
                            return null;
                        } else {
                            rentedCarId = res.getInt(1);
                        }
                    }

                    query = "SELECT * FROM CAR WHERE ID=" + rentedCarId;
                    ResultSet car = statement.executeQuery(query);

                    while (car.next()) {

                        carName = car.getString("NAME");
                        companyId = car.getInt("COMPANY_ID");
                    }

                    query = "SELECT NAME FROM COMPANY WHERE ID=" + companyId;
                    ResultSet company = statement.executeQuery(query);

                    while (company.next()) {
                        companyName = company.getString("NAME");
                    }

                    return carName;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    static boolean returnCar(String cstr) {

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {

                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {

                    int customerId = customerid(cstr);
                    String sql = "SELECT * FROM CUSTOMER WHERE ID=" + customerId + ";";
                    ResultSet customer = statement.executeQuery(sql);

                    while (customer.next()) {

                        if (customer.getObject(3) == null) {
                            return false;
                        }
                    }

                    sql = "UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE ID = " + customerId;
                    statement.executeUpdate(sql);
                }
            }
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}