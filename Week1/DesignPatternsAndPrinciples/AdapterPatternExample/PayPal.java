package Week1.DesignPatternsAndPrinciples.AdapterPatternExample;

public class PayPal {
    public void payWithPayPal(String paymentMethod, double amount) {
        System.out.println("Paid $" + amount + " using PayPal with " + paymentMethod);
    }
}
