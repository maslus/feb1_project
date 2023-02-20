package com.example.demo.external_system.spimex.dao;

import com.example.demo.external_system.spimex.api.SpimexApi;
import com.example.demo.external_system.spimex.core.TradeParticipant;

import java.util.List;

public class DictionaryData {
    private static List<TradeParticipant> LIST_TRADE_PARTICIPANT = null;

    /**
     * Справочник. Список участников торгов
     */
    public static List<TradeParticipant> getListTradeParticipant() {
        if (LIST_TRADE_PARTICIPANT == null) {
            updateTradeParticipantsInfo();
        }
        return LIST_TRADE_PARTICIPANT;
    }

    /**
     * Метод запускается при старте приложения и работает по расписанию, раз в сутки, чтобы каждый день была актуальная информация
     */
    public static void updateTradeParticipantsInfo() {
        LIST_TRADE_PARTICIPANT = new SpimexApi().loadTradeParticipants();
        System.out.println("Список участников торгов обновлен.");
    }
}
