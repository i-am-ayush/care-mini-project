package spittr;

import javax.validation.constraints.Size;

public class Login {
    private int id;
    @Size(min=5,max=16)
    private String name;

    public Login(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
