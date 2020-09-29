package petStoreTests.order;

import client.StoreClient;
import dto.requests.store.Order;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static client.StoreClient.deleteOrderById;
import static data.DataSet.addingOrder;

public class AddOrderTest {
    @Description("Verify that we are adding order to the store")
    @Parameters({"Order for adding"})
    @Test(dataProvider = "testData")
    public void addOrderTest(Order order) {
        Allure.step("Start test add order to the store");
        Order response = StoreClient.postOrder(order);

        Assert.assertNotNull(response.getId());
        Assert.assertNotNull(response.getPetId());
        Assert.assertNotNull(response.getQuantity());
        Assert.assertNotNull(response.isComplete());
        Assert.assertTrue(response.getId() >= 0);
        Assert.assertTrue(response.getPetId() >= 0);
        Assert.assertTrue(response.getQuantity() >= 0);
        Assert.assertEquals(response.getShipDate(), order.getShipDate());
        Assert.assertEquals(response.getStatus(), order.getStatus());

        Allure.step("After test deleting order");
        deleteOrderById(response.getId());
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
            {addingOrder()},
            {addingOrder().setId(0)},
            {addingOrder().setId(9223372036854775807L)},
            {addingOrder().setPetId(null)},
            {addingOrder().setPetId(0L)},
            {addingOrder().setPetId(9223372036854775807L)},
            {addingOrder().setQuantity(null)},
            {addingOrder().setQuantity(0)},
            {addingOrder().setQuantity(2147483647)},
            {addingOrder().setShipDate(null)},
            {addingOrder().setStatus(null)},
            {addingOrder().setComplete(false)},
            {addingOrder().setComplete(null)},
        };
    }
}
