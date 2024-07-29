package Week1.DesignPatternsAndPrinciples.DependencyInjectionExample;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository{
    private Map<Integer, Customer> customers;

    public CustomerRepositoryImpl() {
        customers = new HashMap<>();
        customers.put(1, new Customer(1, "Adi"));
        customers.put(2, new Customer(2, "May"));
        customers.put(3, new Customer(3, "Bob"));
    }

    public Customer findCustomerById(int id) {
        return customers.get(id);
    }
}
