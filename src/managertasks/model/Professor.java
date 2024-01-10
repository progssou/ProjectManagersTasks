package managertasks.model;

import java.util.Observable;
import java.util.Observer;

public class Professor  extends User implements Observer {

    public Professor(String name, String email, String professeur) {
        super(name, email, "Professeur");
    }
    @Override
    public String getRole() {
        return "Professeur";
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
