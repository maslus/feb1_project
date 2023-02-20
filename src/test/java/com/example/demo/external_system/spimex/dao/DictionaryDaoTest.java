package com.example.demo.external_system.spimex.dao;

import com.example.demo.core.Region;
import com.example.demo.external_system.spimex.core.TradeParticipant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DictionaryDaoTest {
    @Test
    public void findLegalTradersByRegion() {
        try {
            //поиск организаций без указания региона, должны получить большой список
            List<TradeParticipant> allTraders = DictionaryDao.findLegalTradersByRegion(null);
            Assertions.assertNotNull(allTraders);
            Assertions.assertTrue(allTraders.size() > 0);

            //поиск организаций в Москве, список должен быть меньше
            List<TradeParticipant> mskTraders = DictionaryDao.findLegalTradersByRegion(Region.valOf(77));
            Assertions.assertNotNull(mskTraders);
            Assertions.assertTrue(mskTraders.size() > 0);
            Assertions.assertTrue(allTraders.size() > mskTraders.size());

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

}
