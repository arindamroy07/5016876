package Week1.DesignPatternsAndPrinciples.FactoryMethodPatternExample;

public interface PdfDocument extends Document {
    default void display() {
        System.out.println("Displaying PDF document...");
    }
}
