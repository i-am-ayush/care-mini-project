package spittr;


public class PojoTest {
    private String name;
    private int id;

    public PojoTest() {
        this.name = "mike";
        this.id = 2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
