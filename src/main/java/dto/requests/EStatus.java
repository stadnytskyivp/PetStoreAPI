package dto.requests;

public enum EStatus {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String status;

    EStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.valueOf(status);
    }
}
