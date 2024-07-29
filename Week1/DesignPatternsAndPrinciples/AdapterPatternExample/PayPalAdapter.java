package Week1.DesignPatternsAndPrinciples.AdapterPatternExample;

public class PayPalAdapter implements PaymentProcessor {
    private PayPal payPal;

    public PayPalAdapter(PayPal payPal) {
        this.payPal = payPal;
    }

    public void processPayment(String paymentMethod, double amount) {
        payPal.payWithPayPal(paymentMethod, amount);
    }
}
