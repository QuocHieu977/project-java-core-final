package statics;

public enum TypeRole {
    ADMIN("Quản trị viên"),
    CUSTOMER("Khách hàng");

    public String value;

    TypeRole(String value) {
        this.value = value;
    }
}
