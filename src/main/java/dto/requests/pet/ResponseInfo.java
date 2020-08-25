package dto.requests.pet;

public class DeleteRes {

    private int code;
    private String type;
    private String message;

    public int getCode() {
        return code;
    }

    public DeleteRes setCode(int code) {
        this.code = code;
        return this;
    }

    public String getType() {
        return type;
    }

    public DeleteRes setType(String type) {
        this.type = type;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DeleteRes setMessage(String message) {
        this.message = message;
        return this;
    }
}
