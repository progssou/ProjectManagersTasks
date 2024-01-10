package managertasks.task;

import managertasks.model.Student;
import managertasks.projets.Project;

import java.util.*;

public class TaskManagerImpl extends Observable implements TaskManager    {
    private List<Task> tasks;
    private List<Project> projects;
    private List<Observable> observers;

    private static TaskManagerImpl instance;

    private TaskManagerImpl() {
        this.tasks = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public static TaskManagerImpl getInstance() {
        if (instance == null) {
            instance = new TaskManagerImpl();
        }
        return instance;
    }

    @Override
    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
            notifyObservers();
        }
    }


    public void displayAllProjects() {
        System.out.println("Liste de tous les projets :");
        for (Project project : projects) {
            System.out.println("- " + project.getProjectName());
        }
    }




    @Override
    public void removeTask(Task task) {
        if (task != null) {
            tasks.remove(task);

            for (Project project : projects) {
                project.getTasks().remove(task);
            }

            System.out.println("Tâche supprimée avec succès.");
            notifyObservers();
        }
    }

    @Override
    public void removeProject(Project project) {
        if (project.getTasks().isEmpty()) {
            projects.remove(project);
            System.out.println("Projet supprimé avec succès.");
        } else {
            System.out.println("Impossible de supprimer le projet. Il contient toujours des tâches.");
        }
    }

    @Override
    public Project findProjectByName(String projectName) {
        for (Project project : projects) {
            if (project.getProjectName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }

    @Override
    public List<Project> getProjectsForStudent(Student student) {
        List<Project> studentProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getTasks().stream().anyMatch(task -> task.getAssignedStudents().contains(student))) {
                studentProjects.add(project);
            }
        }
        return studentProjects;
    }

    @Override
    public void update(Task task) {
        System.out.println("Tâche modifiée : " + task.getTaskName());
    }

    @Override
    public void displayTasks() {
        System.out.println("Tâches :");
        for (Task task : tasks) {
            System.out.println("- " + task.getTaskName() +
                    " (Assignée à : " + task.getAssignedStudents() +
                    ", Statut : " + task.isCompleted() +
                    ", Date de début : " + task.getStartDate() +
                    ", Date limite : " + task.getDeadline() + ")");
        }
    }

    @Override
    public void displayTasksForStudent(Student student) {
        if (student != null) {
            System.out.println(student.getRole() + " " + student.getName() + " - Tâches assignées :");
            for (Task task : tasks) {
                if (task.getAssignedStudents().contains(student)) {
                    System.out.println("- " + task.getTaskName() +
                            " (Statut : " + task.isCompleted() +
                            ", Date de début : " + task.getStartDate() +
                            ", Date limite : " + task.getDeadline() + ")");
                }
            }
        } else {
            System.out.println("Affichage de tous les projets :");
            for (Project project : projects) {
                System.out.println("- " + project.getProjectName());
            }
        }
    }

    @Override
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    @Override
    public void addProject(Project project) {
        if (project != null) {
            projects.add(project);
            System.out.println("Projet ajouté avec succès.");
        }
    }

    @Override
    public void addTaskToProject(Task task, Project project) {
        if (projects.contains(project)) {
            project.getTasks().add(task);
            System.out.println("Tâche ajoutée au projet avec succès.");
        } else {
            System.out.println("Projet non trouvé. Tâche non ajoutée au projet.");
        }
    }

    @Override
    public void displayAllStudents() {
        System.out.println("Liste de tous les étudiants :");
        for (Task task : tasks) {
            for (Student student : task.getAssignedStudents()) {
                System.out.println("- " + student.getName());
            }
        }
    }



    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }

    @Override
    public void update(Observable o, Object arg) {

    }


}
