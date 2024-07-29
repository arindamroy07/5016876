package Week1.DataStructuresAndAlgorithms.EmployeeManagementSystem;

public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);

        ems.addEmployee(new Employee(1, "Jane", "Manager", 5000));
        ems.addEmployee(new Employee(2, "Joe", "Developer", 4500));
        ems.addEmployee(new Employee(3, "Bob", "Tester", 3000));
        ems.addEmployee(new Employee(4, "May", "Designer", 4000));
        ems.addEmployee(new Employee(5, "Hal", "OPs", 3800));

        System.out.println("All Employees:");
        ems.traverseEmployees();
        System.out.println();

        Employee employee = ems.searchEmployee(3);
        if (employee != null) {
            System.out.println("Found Employee:");
            System.out.println(employee);
        } else {
            System.out.println("Employee not found.");
        }
        System.out.println();

        System.out.println("All Employees after deletion:");
        ems.deleteEmployee(4);
        ems.traverseEmployees();
    }
}

// Analyze the time complexity of each operation (add, search, traverse, delete).
// Add: O(1)
// Search: O(n)
// Traverse: O(n)
// Delete: O(n)

// Discuss the limitations of arrays and when to use them.
// Limitations:
// Fixed Size: Arrays have a fixed size, which means you need to know the maximum number of elements in advance.
// Inefficient Deletion: Deleting an element requires shifting elements, which can be inefficient.
// Inefficient Insertion: Inserting an element in the middle requires shifting elements.
// When to use:
// When you need fast access to elements using an index.
// When the number of elements is known and fixed.
// When memory efficiency and cache performance are important.
