package dto.requests;

public enum EStatus {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String status;

    EStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
