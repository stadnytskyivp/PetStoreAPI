package petStoreTests.order;

import client.StoreClient;
import data.DataSet;
import dto.requests.store.Order;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static data.DataSet.addingOrder;

public class GetOrderTest {
    @Description("Verify that we are getting order by id")
    @Test
    public void getOrderTest() {
        Allure.step("Before test adding order");
        StoreClient.postOrder(addingOrder());

        Allure.step("Start test find order in the store");
        Order response = StoreClient.getOrderById(DataSet.addingOrder().getId());

        Assert.assertEquals(response.getId(), addingOrder().getId());
        Assert.assertEquals(response.getPetId(), addingOrder().getPetId());
        Assert.assertEquals(response.getQuantity(), addingOrder().getQuantity());
        Assert.assertEquals(response.getShipDate(), addingOrder().getShipDate());
        Assert.assertEquals(response.getStatus(), addingOrder().getStatus());
        Assert.assertEquals(response.isComplete(), addingOrder().isComplete());
    }
}
