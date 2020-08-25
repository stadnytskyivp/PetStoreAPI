package dto.requests.pet;

public class ResponseInfo {

    private int code;
    private String type;
    private String message;

    public int getCode() {
        return code;
    }

    public ResponseInfo setCode(int code) {
        this.code = code;
        return this;
    }

    public String getType() {
        return type;
    }

    public ResponseInfo setType(String type) {
        this.type = type;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseInfo setMessage(String message) {
        this.message = message;
        return this;
    }
}
