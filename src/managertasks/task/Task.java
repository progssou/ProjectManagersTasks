package managertasks.task;

import managertasks.model.Student;
import managertasks.projets.Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Task {
    private Student student;
    private String taskName;
    private TaskStates taskStatus;
    private boolean isCompleted;
    private List<Student> assignedStudents;
    private Date startDate;
    private Date deadline;
    private String note;
    private List<String> comments;
    private List<String> checklists;
    private List<String> labels;
    private Project project;
    private String comment;
    private String description; // Nouvelle propriété ajoutée

    private List<TaskManager> observers = new ArrayList<>();

    public Task(String taskName, List<Student> assignedStudents, Date startDate, Date deadline, String description) {
        this.taskName = taskName;
        this.taskStatus = TaskStates.IN_PROGRESS;
        this.isCompleted = false;
        this.assignedStudents = assignedStudents;
        this.startDate = startDate;
        this.deadline = deadline;
        this.comments = new ArrayList<>();
        this.checklists = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.description = description; // Initialisation de la nouvelle propriété
    }

    public Task(String taskName, List<Student> assignedStudents, Date startDate, Date deadline,Project project) {
        this.taskName = taskName;
        this.taskStatus = TaskStates.IN_PROGRESS;
        this.isCompleted = false;
        this.assignedStudents = assignedStudents;
        this.startDate = startDate;
        this.deadline = deadline;
        this.comments = new ArrayList<>();
        this.checklists = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.project = project;

    }

    public void addObserver(TaskManager observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskManager observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (TaskManager observer : observers) {
            observer.update(this);
        }
    }



    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Student> getAssignedStudents() {
        return assignedStudents;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskStatus(TaskStates taskStatus) {
        this.taskStatus = taskStatus;
        if (taskStatus == TaskStates.COMPLETED) {
            isCompleted = true;
        }
    }

    public abstract void performTask();


    // Getters et Setters pour la nouvelle propriété
    public Date getStartDate() {
        return startDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<String> getComments() {
        return comments;
    }

    public List<String> getChecklists() {
        return checklists;
    }

    public List<String> getLabels() {
        return labels;
    }

    public Task setTaskName(String taskName) {
        this.taskName = taskName;
        return this;
    }

    public Task setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

    public Task setAssignedStudents(List<Student> assignedStudents) {
        this.assignedStudents = assignedStudents;
        return this;
    }

    public Task setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Task setDeadline(Date deadline) {
        this.deadline = deadline;
        return this;
    }

    public Task setComments(List<String> comments) {
        this.comments = comments;
        return this;
    }

    public Task setChecklists(List<String> checklists) {
        this.checklists = checklists;
        return this;
    }

    public Task setLabels(List<String> labels) {
        this.labels = labels;
        return this;
    }

    public void addComments(String comment) {
        this.comment = comment;
    }

    public void addChecklistItem(String checklistItem) {
        this.checklists.add(checklistItem);
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }
}
