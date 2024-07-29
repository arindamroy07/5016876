package Week1.DesignPatternsAndPrinciples.AdapterPatternExample;

public interface PaymentProcessor {
    public void processPayment(String paymentMethod, double amount);
}
