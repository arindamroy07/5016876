package Week1.DesignPatternsAndPrinciples.ObserverPatternExample;

public class WebApp implements Observer{
    public void update() {
        System.out.println("Web app received stock price update.");
    }
}
