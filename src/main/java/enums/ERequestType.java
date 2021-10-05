package enums;

public enum ERequestType {
    GET("get"),
    DELETE("delete"),
    POST("post"),
    PUT("put");

    private final String requestType;

    ERequestType(String requestType) {this.requestType = requestType;}

    public String getRequestType() {return requestType;}
}
