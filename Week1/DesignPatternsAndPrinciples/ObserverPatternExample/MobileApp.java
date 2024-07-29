package Week1.DesignPatternsAndPrinciples.ObserverPatternExample;

public class MobileApp implements Observer{
    public void update() {
        System.out.println("Mobile app received stock price update.");
    }
}
