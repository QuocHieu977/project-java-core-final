package statics;

public enum TypeStatus {
    PENDING("Chờ xác nhận"),
    APPROVED("Đã xác nhận"),
    PREPARING_ORDER("Đang chuẩn bị hàng"),
    DELIVERING("Đang giao"),
    RECEIVED("Đã nhận"),
    CANCELED("Đã huỷ"),
    REQUEST_CANCEL("Đã yêu cầu huỷ");

    public final String value;

    TypeStatus(String value) {
        this.value = value;
    }
}
