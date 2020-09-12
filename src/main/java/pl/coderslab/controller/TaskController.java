package pl.coderslab.controller;

import pl.coderslab.service.TaskService;

import java.text.ParseException;
import java.util.Scanner;

public class TaskController {
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    public void displayList(){
        System.out.println("TASKS:");
       for(String line: service.readAllTasks()){
           System.out.println(line);
       }

        System.out.println(" ================ ");
    }


    public void createTask(Scanner scanner){
        String desc = getInput(scanner,"Give me description:");
        String date = getInput(scanner,"Give me some due date (yyyy-mm-dd):");
        String isImportant = getInput(scanner,"Is important (t/f):");
        if(isImportant.equals("t") || isImportant.equals("f")){
            try {
                if(service.createNewTask(desc,date,isImportant.equals("t"))){
                    System.out.println("Task created");
                    System.out.println(" ================ ");
                }else{
                    System.out.println("Ooops :(");
                    System.out.println(" ================ ");
                }
            } catch (ParseException e) {
                System.out.println("Date has invalid format");
                System.out.println(" ================ ");
            }
        }else{
            System.out.println("Bad data input");
            System.out.println(" ================ ");
        }
    }
    public void updateTask(Scanner scanner){
        int id = getIntInput(scanner,"Give me id:");
        String desc = getInput(scanner,"Give me description:");
        String date = getInput(scanner,"Give me some due date (yyyy-mm-dd):");
        String isImportant = getInput(scanner,"Is important (t/f):");
        if(isImportant.equals("t") || isImportant.equals("f")){
            try {
                if(service.updateTask(id, desc,date,isImportant.equals("t"))){
                    System.out.println("Task updated");
                    System.out.println(" ================ ");
                }else{
                    System.out.println("Ooops :(");
                    System.out.println(" ================ ");
                }
            } catch (ParseException e) {
                System.out.println("Date has invalid format");
                System.out.println(" ================ ");
            }
        }else{
            System.out.println("Bad data input");
            System.out.println(" ================ ");
        }
    }
    public void removeTask(Scanner scanner){
        int id = getIntInput(scanner,"Give me id:");
        if(service.deleteTask(id)){
            System.out.println("Task removed");
            System.out.println(" ================ ");
        }else{
            System.out.println("Ooops :(");
            System.out.println(" ================ ");
        }
    }

    private String  getInput(Scanner scanner, String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
    private int  getIntInput(Scanner scanner, String prompt){
        System.out.println(prompt);
        while (!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.println("invalid input type");
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    public void save() {
        service.save();
    }
}
