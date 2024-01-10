package managertasks.model;

public class Graduate extends Student {
    public Graduate(String name,String email,String role) {
        super(name,email,"Diplômé");
    }

    @Override
    public String getRole() {
        return "Diplômé";
    }
}
