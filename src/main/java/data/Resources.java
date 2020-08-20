package data;

public class Resources {

    public static String getPetById(String petId) {
        String res = "/v2/pet/" + petId;
        return res;
    }

}