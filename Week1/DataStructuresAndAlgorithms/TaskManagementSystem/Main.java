package Week1.DataStructuresAndAlgorithms.TaskManagementSystem;

public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        linkedList.addTask(new Task(1, "Task 1", "In Progress"));
        linkedList.addTask(new Task(2, "Task 2", "Completed"));
        linkedList.addTask(new Task(3, "Task 3", "Not Started"));

        System.out.println("All Tasks:");
        linkedList.traverseTasks();
        System.out.println();

        Task task = linkedList.searchTask(3);
        if (task != null) {
            System.out.println("Found Task:");
            System.out.println(task);
        } else {
            System.out.println("Task not found");
        }
        System.out.println();

        System.out.println("After deleting..");
        linkedList.deleteTask(2);
        linkedList.traverseTasks();
    }
}

// Analyze the time complexity of each operation.
// Add: O(n)
// Search: O(n)
// Traverse: O(n)
// Delete: O(n)

// Discuss the advantages of linked lists over arrays for dynamic data.
// Dynamic Size: Linked lists can grow and shrink dynamically, unlike arrays which have a fixed size
// Efficient Insertion/Deletion: Inserting or deleting elements in a linked list is more efficient
