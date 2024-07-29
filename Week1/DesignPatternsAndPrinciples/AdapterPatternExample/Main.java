package Week1.DesignPatternsAndPrinciples.AdapterPatternExample;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor payPalAdapter = new PayPalAdapter(new PayPal());
        payPalAdapter.processPayment("Debit Card", 100);
        
        PaymentProcessor upiAdapter = new UPIAdapter(new UPI());
        upiAdapter.processPayment("GPay", 200);
    }
}
