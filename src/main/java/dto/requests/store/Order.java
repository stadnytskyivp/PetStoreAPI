package dto.requests.store;

public class Order {
    private long id;
    private Long petId;
    private Integer quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    public long getId() {
        return id;
    }

    public Order setId(long id) {
        this.id = id;
        return this;
    }

    public Long getPetId() {
        return petId;
    }

    public Order setPetId(Long petId) {
        this.petId = petId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Order setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getShipDate() {
        return shipDate;
    }

    public Order setShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public Boolean isComplete() {
        return complete;
    }

    public Order setComplete(Boolean complete) {
        this.complete = complete;
        return this;
    }
}
