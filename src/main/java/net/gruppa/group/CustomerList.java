package net.gruppa.group;

import net.gruppa.entity.Customer;
import java.util.List;

public class CustomerList {

    private final List<Customer> customerList;

    public CustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
