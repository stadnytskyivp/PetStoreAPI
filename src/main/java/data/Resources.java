package data;

public class Resources {

    public static String getPetById(long petId) {
        return "/v2/pet/" + petId;
    }

    public static String postPet() {
        return "/v2/pet/";
    }

    public static String getPetByStatus(String petStatus) {
        return "/v2/pet/findByStatus?status=" + petStatus;
    }

}