package Week1.DesignPatternsAndPrinciples.MVCPatternExample;

public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void updateStudentDetails(int id, String name, char grade) {
        model.setId(id);
        model.setName(name);
        model.setGrade(grade);
    }

    public void displayStudentDetails() {
        view.displayStudentDetails(model);
    }
}
