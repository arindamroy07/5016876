package Week1.DesignPatternsAndPrinciples.MVCPatternExample;

public class Main {
    public static void main(String[] args) {
        Student student = new Student(1, "Void", 'A');
        StudentView studentView = new StudentView();
        StudentController studentController = new StudentController(student, studentView);
        studentController.displayStudentDetails();
        System.out.println();

        System.out.println("After updating..");
        studentController.updateStudentDetails(7, "Ady", 'O');
        studentController.displayStudentDetails();
    }
}
