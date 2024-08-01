public class Main {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        // Adding tasks
        taskList.addTask(new Task(1, "Task 1", "Pending"));
        taskList.addTask(new Task(2, "Task 2", "Completed"));
        taskList.addTask(new Task(3, "Task 3", "In Progress"));

        // Traversing tasks
        System.out.println("All tasks:");
        taskList.traverseTasks();

        // Searching for a task
        System.out.println("\nSearching for task with ID 2:");
        Task task = taskList.searchTask(2);
        if (task != null) {
            System.out.println("Task found: " + task);
        } else {
            System.out.println("Task not found");
        }

        // Deleting a task
        System.out.println("\nDeleting task with ID 2:");
        boolean isDeleted = taskList.deleteTask(2);
        if (isDeleted) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }

        // Traversing tasks again to verify deletion
        System.out.println("\nAll tasks after deletion:");
        taskList.traverseTasks();
    }
}
