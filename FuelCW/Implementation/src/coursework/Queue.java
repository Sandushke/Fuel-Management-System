package coursework;

import java.sql.*;
import java.util.ArrayList;

public abstract class Queue {
    ArrayList<Customer> customers = new ArrayList<Customer>();

    public Queue(){}

    public void addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        customers.add(customer);


    }
}
