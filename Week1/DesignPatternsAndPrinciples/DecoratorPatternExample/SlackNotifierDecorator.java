package Week1.DesignPatternsAndPrinciples.DecoratorPatternExample;

public class SlackNotifierDecorator extends NotifierDecorator{
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    public void send() {
        super.send();
        System.out.println("Sending notification via Slack");
    }
}
