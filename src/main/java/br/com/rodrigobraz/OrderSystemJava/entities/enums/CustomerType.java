package br.com.rodrigobraz.OrderSystemJava.entities.enums;

public enum CustomerType {
    NATURAL_PERSON(1, "Natural Person"),
    LEGAL_ENTITY(2, "Legal Entity");

    private int code;
    private String description;

    private CustomerType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // Varre todas as opções do Enum para garantir o código buscado para instanciar no banco de dados
    public static CustomerType toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (CustomerType x : CustomerType.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + code);
    }
}
