package com.example.demo.external_system.spimex.dao;

import com.example.demo.core.Region;
import com.example.demo.external_system.spimex.core.Residence;
import com.example.demo.external_system.spimex.core.TradeParticipant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DictionaryDao {
    /**
     * Поиск действующих записей о юр.лицах, зарегистрированных в указанном регионе РФ
     */
    public static List<TradeParticipant> findLegalTradersByRegion(Region region) {
        //найдем список всех участников торгов
        List<TradeParticipant> list = DictionaryData.getListTradeParticipant();
        /*
        отфильтруем из них:
         1) резидентов РФ,
         2) у которых нет даты блокировки,
         3) ИНН состоит из 10 цифр (юр.лицо)
         */
        Stream<TradeParticipant> stream = list.stream()
                .filter(tradeParticipant -> {
                    return (Residence.RUSSIA_RESIDENT.equals(tradeParticipant.getResidence())
                            && tradeParticipant.getBlockDate() == null
                            && tradeParticipant.getINN().length() == 10);
                });

/*
 В случае смены региона регистрации юр.лица его ИНН не меняется,
 таким образом, чтобы получить достоверные сведения, вероятно нужно обратиться к API ФНС для получения актуальных данных,
 но для тестового задания принимаем за правило, что первые два символа ИНН указывают на регион регистрации юр.лица
*/

        if (region != null) {
            return stream
                    .filter(tradeParticipant -> {
                        if (tradeParticipant.getINN() == null) return false;
                        Integer codeFromInn = Integer.valueOf(tradeParticipant.getINN().substring(0, 2));
                        for (int regionCode : region.getCodes()) {
                           return   codeFromInn.equals(regionCode);
                        }
                        return false;
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            //если код региона не задан, вернем по всем регионам
            return stream.collect(Collectors.toCollection(ArrayList::new));

        }
    }
}
