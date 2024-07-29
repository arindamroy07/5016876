package Week1.DesignPatternsAndPrinciples.ObserverPatternExample;

public class Main {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.register(mobileApp);
        stockMarket.register(webApp);
        stockMarket.notifyObservers();
        System.out.println();
        
        stockMarket.deregister(webApp);
        stockMarket.notifyObservers();
    }
}
