package Week1.DesignPatternsAndPrinciples.FactoryMethodPatternExample;

public interface ExcelDocument extends Document {
    default void display() {
        System.out.println("Displaying Excel document...");
    }
}
