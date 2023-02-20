package com.example.demo.external_system.spimex.core;

/**
 * Перечень типов данных, получаемых от https://api.spimex.com/otc/lookup-tables
 */
public enum TableType {
    TRADE_PARTICIPANT(1, "Участники торгов"),
    PRODUCT(2, "Товары"),
    CURRENCY(3, "Валюта"),
    SUPPLY_ADDRESS(4, "Адреса поставки"),
    SUPPLY_TERMS(5, "Условия поставки");

    private final Integer tableId;
    private final String tableName;

    public Integer getTableId() {
        return tableId;
    }

    TableType(Integer tableId, String tableName) {
        this.tableId = tableId;
        this.tableName = tableName;
    }
}
