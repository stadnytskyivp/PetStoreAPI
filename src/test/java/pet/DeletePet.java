package pet;

import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DeletePet {

    private final String EXPECTED_TYPE = "unknown";
    private final String EXPECTED_MESSAGE = "669118";

    public static final Logger LOGGER = LoggerFactory.getLogger(DeletePet.class);

    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST delete pet from the store");

//        Assert.assertEquals(pet.getBody("type"), EXPECTED_TYPE);
//        Assert.assertEquals(pet.getStatus(), EXPECTED_MESSAGE);

        LOGGER.info("END TEST");
    }

}
