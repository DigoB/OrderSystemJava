package br.com.rodrigobraz.OrderSystemJava.entities.enums;

public enum PaymentStatus {

    PENDING(1, "Pending"),
    FINISHED(2, "Finished"),
    CANCELLED(3, "Cancelled");

    private int code;
    private String description;

    private PaymentStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (PaymentStatus x : PaymentStatus.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + code);
    }
}
