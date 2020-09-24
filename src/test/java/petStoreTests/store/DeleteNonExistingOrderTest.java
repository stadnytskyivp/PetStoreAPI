package petStoreTests.store;

import client.StoreClient;
import data.DataSet;
import dto.requests.ResponseInfo;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

public class DeleteNonExistingOrderTest extends AbstractTest {
    @Description("Verify that we will get error message when trying to delete nonexistent order by id")
    @Test
    public void getOrderTest() throws IOException {
        LOGGER.info("BEFORE TEST making sure that there is no order with such ID");
        StoreClient.postOrder(DataSet.addingOrder());
        StoreClient.deleteOrderById(DataSet.addingOrder().getId());
        LOGGER.info("BEFORE TEST order deleted");

        LOGGER.info("START TEST try to delete nonexistent order in the store");
        ResponseInfo response = StoreClient.deleteNonExistingOrderById(DataSet.addingOrder().getId());

        Assert.assertEquals(response.getCode(), DataSet.messageUnknownResponse().setCode(404).getCode());
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), DataSet.messageUnknownResponse().setMessage("Order Not Found").getMessage());
        LOGGER.info("END TEST");
    }
}
