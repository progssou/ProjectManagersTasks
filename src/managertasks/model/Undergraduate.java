package managertasks.model;

public class Undergraduate extends Student {
    public Undergraduate(String name,String email,String role) {
        super(name,email,"Premier cycle");
    }

    @Override
    public String getRole() {
        return "Premier cycle";
    }
}
