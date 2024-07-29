package Week1.DesignPatternsAndPrinciples.ProxyPatternExample;

public class Main {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.jpg");
        Image image3 = new ProxyImage("image3.jpg");

        image1.display();
        image2.display();
        System.out.println();

        // for cached image
        System.out.println("After caching..");
        image1.display();
        image2.display();

        image3.display();
    }
}
