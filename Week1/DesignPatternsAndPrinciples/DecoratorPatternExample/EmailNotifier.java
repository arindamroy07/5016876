package Week1.DesignPatternsAndPrinciples.DecoratorPatternExample;

public class EmailNotifier implements Notifier {
    public void send() {
        System.out.println("Sending notification via email");
    }
}
