package pl.coderslab.service;

import pl.coderslab.domain.Task;
import pl.coderslab.repository.TaskRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private TaskRepository repository;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public boolean createNewTask(String desc, String date, boolean important) throws ParseException{
        try{
            simpleDateFormat.parse(date);
            Task task = repository.create(desc,date,important);
            return task!=null;
        } catch (ParseException e) {
            throw e;
        }
    }
    public boolean updateTask(int index, String desc, String date, boolean important) throws ParseException{
        try{
            simpleDateFormat.parse(date);
            Task task = repository.update(index,desc,date,important);
            return task!=null;
        } catch (ParseException e) {
            throw e;
        }
    }

    public boolean deleteTask(int index){
        return repository.delete(index);
    }

    public List<String> readAllTasks(){
        List<Task> tasks = repository.findAll();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            list.add(String.format("%s || %s || %s || %s",
                    i,
                    task.getDescription(),
                    task.getDate(),
                    task.isImportant()?"true":"false"));
        }
        return list;
    }

    public void save(){
        repository.save();
    }
}
