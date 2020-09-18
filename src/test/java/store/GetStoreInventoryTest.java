package store;

import client.StoreClient;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetStoreInventoryTest extends AbstractStoreTest {

    @Description("Verify that we are getting all store inventory")
    @Test
    public void getStoreInventoryTest() throws IOException {
        LOGGER.info("START TEST getting all info about store inventory");

        Response response = StoreClient.getStoreInventory();

        Assert.assertEquals(response.asString(), "some data");

        LOGGER.info("END TEST");
    }
}
