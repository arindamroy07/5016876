package Week1.DesignPatternsAndPrinciples.StrategyPatternExample;

public class CreditCardPayment implements PaymentStrategy{
    private String cardNumber;

    public CreditCardPayment (String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using credit card " + cardNumber);
    }
}
