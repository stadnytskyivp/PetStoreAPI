package petStoreTests.order;

import client.StoreClient;
import dto.requests.ResponseInfo;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static data.DataSet.addingOrder;
import static data.DataSet.messageUnknownResponse;

public class DeleteOrderTest {
    @Description("Verify that we are deleting order")
    @Test
    public static void deleteOrderTest() {
        Allure.step("Before test adding a order");
        StoreClient.postOrder(addingOrder());

        Allure.step("Start tst delete order from the store");
        ResponseInfo response = StoreClient.deleteOrderById(addingOrder().getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getType(), messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), String.valueOf(addingOrder().getId()));
    }
}
