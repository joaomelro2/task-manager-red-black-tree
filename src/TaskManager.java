import java.util.LinkedList;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n1. Add task");
            System.out.println("2. List tasks by date");
            System.out.println("3. Print tree");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter the date (dd/mm/yyyy): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter the task: ");
                    String task = scanner.nextLine();
                    tree.addTask(date, task);
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    System.out.print("Enter the date (dd/mm/yyyy): ");
                    date = scanner.nextLine();
                    LinkedList<String> tasks = tree.getTasks(date);
                    if (tasks != null) {
                        System.out.println("Tasks for date " + date + ":");
                        for (String t : tasks) {
                            System.out.println("- " + t);
                        }
                    } else {
                        System.out.println("No tasks found for this date.");
                    }
                    break;
                case 3:
                    tree.printTree();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (option != 4);

        scanner.close();
    }
}
