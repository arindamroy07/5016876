package Week1.DesignPatternsAndPrinciples.StrategyPatternExample;

public class Main {
    public static void main(String[] args) {
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234-5678");
        PaymentStrategy paypalPayment = new PayPalPayment("adx@gmail.com"); 

        PaymentContext paymentContext = new PaymentContext(creditCardPayment);
        paymentContext.pay(100);

        paymentContext = new PaymentContext(paypalPayment);
        paymentContext.pay(200);
    }
}
