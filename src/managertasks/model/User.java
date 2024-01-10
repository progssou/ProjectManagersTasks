package managertasks.model;

public abstract class User {
    private String name;
    private String email;
    private String role;
    private String studentNumber; // Ajouté pour stocker le numéro d'étudiant
    private String department; // Ajouté pour stocker le département


    public User(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters et Setters pour les nouvelles propriétés
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Getters pour les propriétés existantes
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }




}
