package Week1.DesignPatternsAndPrinciples.AdapterPatternExample;

public class UPIAdapter implements PaymentProcessor {
    private UPI upi;

    public UPIAdapter(UPI upi) {
        this.upi = upi;
    }

    public void processPayment(String paymentMethod, double amount) {
        upi.payWithUPI(paymentMethod, amount);
    }
}
