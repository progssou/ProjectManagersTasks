package managertasks.model;

public class Student extends User {
    public Student(String name,String email,String role) {
        super(name,email,role);
    }

    @Override
    public String getRole() {
        return null; // You might want to provide an implementation in subclasses
    }
}
