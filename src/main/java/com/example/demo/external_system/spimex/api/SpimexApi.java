package com.example.demo.external_system.spimex.api;

import com.example.demo.exception.DAOException;
import com.example.demo.external_system.spimex.core.Residence;
import com.example.demo.external_system.spimex.core.TableType;
import com.example.demo.external_system.spimex.core.TradeParticipant;
import com.example.demo.util.gson.LocalDateTimeAdapter;
import com.example.demo.util.gson.ResidenceAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SpimexApi {
    private static final String spimexUri = "https://api.spimex.com/otc/lookup-tables";

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(Residence.class, new ResidenceAdapter())
            .create();

    /**
     * Получение списка участников торгов СПб-Биржи
     */
    public List<TradeParticipant> loadTradeParticipants() {
        List<TradeParticipant> list = new ArrayList<>();

        JerseyClient client = new JerseyClientBuilder().build();
        JerseyWebTarget generalTarget = client.target(spimexUri);
        JerseyWebTarget baseTarget = generalTarget.path("{table}");
        JerseyWebTarget finalTarget = baseTarget.resolveTemplate("table", TableType.TRADE_PARTICIPANT.getTableId());

        Response resp = finalTarget.request().get();
        String value = resp.readEntity(String.class);
        JsonObject response = gson.fromJson(value, JsonObject.class);

        if (response.has("result") && response.get("result") != null) {
            if (response.get("result").getAsString().equals("OK")) {
                if (response.has("records")) {
                    JsonArray records = response.getAsJsonArray("records");
                    records.forEach(jsonElement -> {
                        TradeParticipant tradeParticipant = gson.fromJson(jsonElement, TradeParticipant.class);
                        list.add(tradeParticipant);
                    });
                }
            } else {
                String messageDetail = null;
                if (response.has("errorMessage") && response.get("errorMessage") != null) {
                    messageDetail = response.get("errorMessage").getAsString();
                }
                throw new DAOException("Ошибка получения списка участников торгов от api.spimex. ", messageDetail != null ? messageDetail : "");
            }
        } else {
            throw new DAOException("Ошибка связи с api.spimex.");
        }
        return list;
    }

}
