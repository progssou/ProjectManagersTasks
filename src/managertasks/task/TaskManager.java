package managertasks.task;

import managertasks.model.*;
import managertasks.projets.*;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public interface TaskManager extends  Observer{
    void addTask(Task task);

    void removeTask(Task task);

    void removeProject(Project project);

    Project findProjectByName(String projectName);

    List<Project> getProjectsForStudent(Student student);


    void update(Task task);

    void displayTasks();

    void displayTasksForStudent(Student student);

    List<Task> getTasks();

    void addProject(Project project);

    void addTaskToProject(Task task, Project project);

    void displayAllStudents();

    void displayAllProjects();


    @Override
    void update(Observable o, Object arg);
}
