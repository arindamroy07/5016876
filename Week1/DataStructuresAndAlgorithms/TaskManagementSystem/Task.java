package Week1.DataStructuresAndAlgorithms.TaskManagementSystem;

// Explain the different types of linked lists (Singly Linked List, Doubly Linked List).
// Singly Linked List: A linear data structure where each node contains data and a reference (or pointer) to the next node in sequence
// Doubly: Each node contains data, a reference to the next node, and a reference to the previous node

public class Task {
    private int taskId;
    private String taskName;
    private String status;
    
    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
