package petStoreTests.order;

import client.StoreClient;
import dto.requests.ResponseInfo;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

import static data.DataSet.*;

public class DeleteOrderTest extends AbstractTest {
    @Description("Verify that we are deleting order")
    @Test
    public static void deleteOrderTest() throws IOException {
        LOGGER.info("BEFORE TEST adding a order");
        StoreClient.postOrder(addingOrder());
        LOGGER.info("BEFORE TEST order added");

        LOGGER.info("START TEST delete order from the store");
        ResponseInfo response = StoreClient.deleteOrderById(addingOrder().getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getType(), messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), String.valueOf(addingOrder().getId()));
        LOGGER.info("END TEST");
    }
}
