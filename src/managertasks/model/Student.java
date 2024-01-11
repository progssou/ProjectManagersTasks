package managertasks.model;

import managertasks.task.Task;

import java.util.List;

public class Student extends User {

    private List<Task> task;
    public Student(String name,String email,String role) {
        super(name,email,role);
    }

    @Override
    public String getRole() {
        return null; // You might want to provide an implementation in subclasses
    }

    public Object getTask() {
        return task;
    }
}
