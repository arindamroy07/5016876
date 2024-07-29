package Week1.DesignPatternsAndPrinciples.FactoryMethodPatternExample;

public interface WordDocument extends Document {
    default void display() {
        System.out.println("Displaying Word document...");
    }
}
