package store;

import client.PetClient;
import client.StoreClient;
import dto.requests.ResponseInfo;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.DataSet.*;

public class DeleteOrderTest extends AbstractStoreTest{

    @BeforeMethod
    public void addOrder() throws IOException {
        LOGGER.info("BEFORE TEST adding a order");

        StoreClient.postOrder(addingOrder());

        LOGGER.info("BEFORE TEST order added");
    }

    @Description("Verify that we are deleting order")
    @Test
    public static void deleteOrderTest() throws IOException {
        LOGGER.info("START TEST delete order from the store");

        ResponseInfo response = StoreClient.deleteOrderById(addingOrder().getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getType(), messageDelResponse().getType());
        Assert.assertEquals(response.getMessage(), String.valueOf(addingOrder().getId()));

        LOGGER.info("END TEST");
    }
}
