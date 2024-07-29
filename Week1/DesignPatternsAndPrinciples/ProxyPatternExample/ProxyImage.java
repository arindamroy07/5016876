package Week1.DesignPatternsAndPrinciples.ProxyPatternExample;

public class ProxyImage implements Image{
    private String url;
    private RealImage realImage;

    public ProxyImage(String url) {
        this.url = url;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(url);
        }
        realImage.display();
    }
}
