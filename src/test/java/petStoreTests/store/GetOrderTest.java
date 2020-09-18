package petStoreTests.store;

import client.StoreClient;
import data.DataSet;
import dto.requests.store.Order;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

import static data.DataSet.addingOrder;

public class GetOrderTest extends AbstractTest {

    @Description("Verify that we are getting order by id")
    @Test
    public void getOrderTest() throws IOException {

        LOGGER.info("BEFORE TEST adding a order");
        StoreClient.postOrder(addingOrder());
        LOGGER.info("BEFORE TEST order added");

        LOGGER.info("START TEST find order in the store");
        Order response = StoreClient.getOrderById(DataSet.addingOrder().getId());

        Assert.assertEquals(response.getId(), addingOrder().getId());
        Assert.assertEquals(response.getPetId(), addingOrder().getPetId());
        Assert.assertEquals(response.getQuantity(), addingOrder().getQuantity());
        Assert.assertEquals(response.getShipDate(), addingOrder().getShipDate());
        Assert.assertEquals(response.getStatus(), addingOrder().getStatus());
        Assert.assertEquals(response.isComplete(), addingOrder().isComplete());
        LOGGER.info("END TEST");
    }
}