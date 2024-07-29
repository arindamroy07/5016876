package Week1.DesignPatternsAndPrinciples.FactoryMethodPatternExample;

public class ExcelDocumentFactory extends DocumentFactory{
    public Document createDocument() {
        return new ExcelDocument() {};
    }
}
