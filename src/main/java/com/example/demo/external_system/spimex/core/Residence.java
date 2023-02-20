package com.example.demo.external_system.spimex.core;

/**
 * Перечень типов данных, получаемых от https://api.spimex.com/otc/lookup-tables
 */
public enum Residence {
    NOT_DEFINED(0, "Не определен"),
    RUSSIA_RESIDENT(1, "Резидент РФ"),
    NOT_RUSSIA_RESIDENT(2, "Нерезидент РФ");

    private final Integer id;
    private final String residenceName;

    public Integer getId() {
        return id;
    }

    public String getResidenceName() {
        return residenceName;
    }

    Residence(Integer tableId, String tableName) {
        this.id = tableId;
        this.residenceName = tableName;
    }

    public static Residence valOf(String residenceName) {
        if (residenceName != null && !residenceName.trim().isEmpty()) {
            for (Residence residence : Residence.values()) {
                if (residence.getResidenceName().equals(residenceName)) {
                    return residence;
                }
            }
        }
        return NOT_DEFINED;
    }
}
