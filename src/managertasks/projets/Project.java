package managertasks.projets;

import managertasks.task.*;

import java.util.*;

public class Project {
    private String projectName;
    private List<Task> tasks;
    private String description;

    public Project(String projectName) {
        this.projectName = projectName;
        this.tasks = new ArrayList<>();
    }

    // Getters et Setters pour les nouvelles propriétés
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters pour les propriétés existantes
    public List<Task> getTasks() {
        return tasks;
    }

    public String getProjectName() {
        return projectName;
    }

    public Project setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

}


