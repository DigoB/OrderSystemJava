package br.com.rodrigobraz.OrderSystemJava.entities.enums;

public enum Roles {
    ADMIN(1, "ROLE_ADMIN"),
    CUSTOMER(2, "ROLE_CUSTOMER");

    private int code;
    private String description;

    private Roles(int code, String description) {
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
    public static Roles toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Roles x : Roles.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + code);
    }
}
