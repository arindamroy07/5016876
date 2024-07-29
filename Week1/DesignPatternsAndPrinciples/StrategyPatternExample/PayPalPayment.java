package Week1.DesignPatternsAndPrinciples.StrategyPatternExample;

public class PayPalPayment  implements PaymentStrategy{
    private String payPalAccount;

    public PayPalPayment (String payPalAccount) {
        this.payPalAccount = payPalAccount;
    }

    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using  PayPal account " + payPalAccount);
    }
}
