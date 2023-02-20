package com.example.demo.api;

import com.example.demo.core.Region;
import com.example.demo.exception.RestException;
import com.example.demo.external_system.spimex.core.Residence;
import com.example.demo.external_system.spimex.core.TradeParticipant;
import com.example.demo.external_system.spimex.dao.DictionaryDao;
import com.example.demo.util.gson.LocalDateTimeAdapter;
import com.example.demo.util.gson.ResidenceAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("/traders")
public class TradersApi {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(Residence.class, new ResidenceAdapter())
            .create();

    //например http://localhost:8080/demo7_war_exploded/api/traders/44
    @GET
    @Path("/{region_code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRatePlanInfo_GET(
            @PathParam("region_code") Integer regionCode
    ) {
        JsonObject json = new JsonObject();
        try {
            List<TradeParticipant> list = null;
            Region region = null;

            if (regionCode != null && regionCode > 0) {
                region = Region.valOf(regionCode);
                if (region == null) {
                    throw new RestException(Response.Status.BAD_REQUEST, "Неверно задан код региона.");
                }
            }
            //Найдем список всех участников торгов
            list = DictionaryDao.findLegalTradersByRegion(region);
            JsonArray arr = new JsonArray();
            if (list != null && list.size() > 0) {
                list.forEach(item -> arr.add(gson.toJsonTree(item, TradeParticipant.class)));
                json.addProperty("size", list.size());
            }
            json.addProperty("region", region != null ? region.getNameFull() : "Все регионы");
            json.add("records", arr);

            return Response.status(Response.Status.OK).entity(json.toString()).build();
        } catch (RestException e) {
            try {
                json.addProperty("error", e.getMessage());
            } catch (Exception ex) {
            }
            return Response.status(e.getErrorCode()).entity(json.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                json.addProperty("error", "При получении данных произошла ошибка. " + e.getMessage());
            } catch (Exception ex) {
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json.toString()).build();
        }
    }
}