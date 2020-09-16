package store;

import client.StoreClient;
import dto.requests.store.Order;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.DataSet.addingOrder;


public class AddOrderTest extends AbstractStoreTest {

    @Description("Verify that we are adding order to the store")
    @Parameters({"Order for adding"})
    @Test(dataProvider = "testData")
    public void addOrderTest(Order order) throws IOException {
        LOGGER.info("START TEST add order to the store ");

        Order response = StoreClient.postOrder(order);

        Assert.assertEquals(response.getId(), order.getId());
        Assert.assertEquals(response.getPetId(), order.getPetId());
        Assert.assertEquals(response.getQuantity(), order.getQuantity());
        Assert.assertEquals(response.getShipDate(), order.getShipDate());
        Assert.assertEquals(response.getStatus(), order.getStatus());
        Assert.assertEquals(response.isComplete(), order.isComplete());

        LOGGER.info("END TEST");

//        LOGGER.info("AFTER TEST DELETING ORDER");
//
//        deleteOrderById(response.getId());
//
//        LOGGER.info("AFTER TEST ORDER DELETED");
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
            {addingOrder()},
        };
    }
}
