package Week1.DesignPatternsAndPrinciples.DependencyInjectionExample;

public class Main {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerService(customerRepository);

        Customer customer = customerService.findCustomerById(2);
        System.out.println("Customer Name: " + customer.getName());
    }
}
