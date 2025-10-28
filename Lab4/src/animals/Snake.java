package animals;

public class Snake extends Animal {

    public Snake(String name) {
        super(name, 0);
    }

    @Override
    public String getDescription() {
        return "Snake named " + name + " with " + legs + " legs.";
    }
}
