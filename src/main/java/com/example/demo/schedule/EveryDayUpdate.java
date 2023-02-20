package com.example.demo.schedule;

import com.example.demo.external_system.spimex.dao.DictionaryData;

public class EveryDayUpdate implements Runnable {
    @Override
    public void run() {
        DictionaryData.updateTradeParticipantsInfo();
    }
}
