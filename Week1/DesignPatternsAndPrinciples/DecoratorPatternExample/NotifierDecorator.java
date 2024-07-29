package Week1.DesignPatternsAndPrinciples.DecoratorPatternExample;

public abstract class NotifierDecorator implements Notifier{
    private Notifier notifier;

    public NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    public void send() {
        notifier.send();
    }
}
