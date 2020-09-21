package petStoreTests.store;

import client.StoreClient;
import dto.requests.ResponseInfo;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

import static data.DataSet.addingOrder;
import static data.DataSet.messageNotFoundResponse;

public class GetOrderNegativeTest extends AbstractTest {

    @Description("Verify that we will get error message by getting nonexistent order by id")
    @Test
    public void getOrderTest() throws IOException {
        LOGGER.info("BEFORE TEST making sure that there is no order with such ID");
        StoreClient.postOrder(addingOrder());
        StoreClient.deleteOrderById(addingOrder().getId());
        LOGGER.info("BEFORE TEST order deleted");

        LOGGER.info("START TEST try to find nonexistent order in the store");
        ResponseInfo response = StoreClient.getNonexistentOrderById(addingOrder().getId());

        Assert.assertEquals(response.getCode(), messageNotFoundResponse().getCode());
        Assert.assertEquals(response.getType(), messageNotFoundResponse().getType());
        Assert.assertEquals(response.getMessage(), messageNotFoundResponse().setMessage("Order not found").getMessage());
        LOGGER.info("END TEST");
    }
}
