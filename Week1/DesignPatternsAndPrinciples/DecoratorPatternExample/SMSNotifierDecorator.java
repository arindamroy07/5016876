package Week1.DesignPatternsAndPrinciples.DecoratorPatternExample;

public class SMSNotifierDecorator extends NotifierDecorator{
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    public void send() {
        super.send();
        System.out.println("Sending notification via SMS");
    }
}
