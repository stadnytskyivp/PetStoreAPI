package dto.requests.pet;

public class PetCategory {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public PetCategory setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PetCategory setName(String name) {
        this.name = name;
        return this;
    }
}
