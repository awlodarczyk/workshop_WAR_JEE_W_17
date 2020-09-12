package pl.coderslab;

import pl.coderslab.controller.TaskController;
import pl.coderslab.repository.TaskRepository;
import pl.coderslab.service.TaskService;

import java.util.Scanner;

public class App {

    private static TaskController controller;
    public static void main(String[] args) {
        controller = new TaskController(new TaskService(new TaskRepository("tasks.csv")));
        Scanner scanner = new Scanner(System.in);

        System.out.println("### Hello in Task Manager app ###");
        while(true) {
            System.out.println("Please select what do You want?");
            System.out.println("- list");
            System.out.println("- add");
            System.out.println("- remove");
            System.out.println("- edit");
            System.out.println("- exit");
            String input = scanner.nextLine();
            if (input.equals("list")){
                controller.displayList();
            }else if (input.equals("add")){
                controller.createTask(scanner);
            }else if (input.equals("remove")){
                controller.removeTask(scanner);
            }else if (input.equals("edit")){
                controller.updateTask(scanner);
            }else if (input.equals("exit")){
                controller.save();
                break;
            }else {
                System.out.println("invalid input");
            }
        }
    }
}
