package petStoreTests.order;

import client.StoreClient;
import data.DataSet;
import dto.requests.ResponseInfo;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteNonExistingOrderTest {
    @Description("Verify that we will get error message when trying to delete nonexistent order by id")
    @Test
    public void getOrderTest() {
        Allure.step("Before test making sure that there is no order with such ID");
        StoreClient.postOrder(DataSet.addingOrder());
        StoreClient.deleteOrderById(DataSet.addingOrder().getId());

        Allure.step("Start test try to delete nonexistent order from the store");
        ResponseInfo response = StoreClient.deleteNonExistingOrderById(DataSet.addingOrder().getId());

        Assert.assertEquals(response.getCode(), DataSet.messageUnknownResponse().setCode(404).getCode());
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), DataSet.messageUnknownResponse().setMessage("Order Not Found").getMessage());
    }
}
