package Week1.DesignPatternsAndPrinciples.ProxyPatternExample;

public class RealImage implements Image{
    private String url;

    public RealImage(String url) {
        this.url = url;
        loadFromServer();
    }

    private void loadFromServer() {
        System.out.println("Loading image from server: " + url);
    }

    public void display() {
        System.out.println("Displaying image: " + url);
    }
}
