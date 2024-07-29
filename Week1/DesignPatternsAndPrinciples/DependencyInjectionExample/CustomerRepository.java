package Week1.DesignPatternsAndPrinciples.DependencyInjectionExample;

public interface CustomerRepository {
    Customer findCustomerById(int id);
}
