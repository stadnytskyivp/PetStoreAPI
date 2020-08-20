package data;

public class Resources {

    public static String getPetById(String petId) {
        String res = "/v2/pet/" + petId;
        return res;
    }

    public static String postPet() {
        String res = "/v2/pet/";
        return res;
    }


}