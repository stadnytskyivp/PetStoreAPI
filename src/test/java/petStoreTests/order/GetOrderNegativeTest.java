package petStoreTests.order;

import client.StoreClient;
import dto.requests.ResponseInfo;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static data.DataSet.addingOrder;
import static data.DataSet.messageNotFoundResponse;

public class GetOrderNegativeTest {
    @Description("Verify that we will get error message by getting nonexistent order by id")
    @Test
    public void getOrderTest() {
        Allure.step("Before test making sure that there is no order with such ID");
        StoreClient.postOrder(addingOrder());
        StoreClient.deleteOrderById(addingOrder().getId());

        Allure.step("Start test try to find non existing order in the store");
        ResponseInfo response = StoreClient.getNonexistentOrderById(addingOrder().getId());

        Assert.assertEquals(response.getCode(), messageNotFoundResponse().getCode());
        Assert.assertEquals(response.getType(), messageNotFoundResponse().getType());
        Assert.assertEquals(response.getMessage(), messageNotFoundResponse().setMessage("Order not found").getMessage());
    }
}
