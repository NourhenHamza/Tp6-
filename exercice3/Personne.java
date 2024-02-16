import java.io.Serializable;

public class Personne implements Serializable {
    private static final long serialVersionUID = 1L;
    private int age;
    private String nom;

    public Personne(int age, String nom) {
        this.age = age;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "age=" + age +
                ", nom='" + nom + '\'' +
                '}';
    }
}
