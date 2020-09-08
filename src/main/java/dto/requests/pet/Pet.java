package dto.requests.pet;

import java.util.List;

public class Pet {

    private long id;
    private PetCategory category;
    private String name;
    private List<String> photoUrls;
    private List<PetCategory> tags;
    private String status;

    public Long getId() {
        return id;
    }

    public Pet setId(long id) {
        this.id = id;
        return this;
    }

    public PetCategory getCategory() {
        return category;
    }

    public Pet setCategory(PetCategory category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public Pet setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public List<PetCategory> getTags() {
        return tags;
    }

    public Pet setTags(List<PetCategory> tags) {
        this.tags = tags;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Pet setStatus(String status) {
        this.status = status;
        return this;
    }
}
