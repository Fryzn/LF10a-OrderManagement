package net.gruppa.main;

import net.gruppa.entity.Address;
import net.gruppa.entity.Customer;
import net.gruppa.group.CustomerList;
import java.util.ArrayList;
import java.util.List;

public class CustomerHandler {

    private final CustomerList savedCustomer;
    private final List<Customer> customerList;

    public CustomerHandler() {
        customerList = new ArrayList<>();
        this.savedCustomer = new CustomerList(customerList);
        customerList.add(new Customer(0, "Hase GmbH", "Frau Hase", "hase@info.de", new Address("Hasen Weg", 10, 13131, "Berlin")));
        customerList.add(new Customer(1, "Zalando GmbH", "Frau Zalandori", "zalando@info.de", new Address("Neuköllner Stra\u00dfe", 4, 13514, "Berlin")));
        customerList.add(new Customer(2, "Begis GmbH", "Herr Begis", "begis@info.de", new Address("Am Borsigturm", 44, 13507, "Berlin")));
        customerList.add(new Customer(3, "Oszimt GmbH", "Herr Hafezi", "oszimt@info.de", new Address("Haarlemer Stra\u00dfe", 23, 12359, "Berlin")));
    }

    public CustomerList getSavedCustomer() {
        return savedCustomer;
    }

    public void createCustomer(Customer customer) { customerList.add(customer); }

    public void deleteCustomer(Customer customer) { customerList.remove(customer); }
}
