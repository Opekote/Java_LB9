package org.university;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class DemoWorkWithAPI {
     static String API_KEY = "43a0cef4dd3b4c818a5328154582ef5d";
     static String ENDPOINT = "http://api.recrm.ru/json";
    static WorkWithAPI workWithAPI = new WorkWithAPI(API_KEY,ENDPOINT);
    public static void main(String[] args) {
        System.out.println("Please enter a name of resulting file");
        String filename = new Scanner(System.in).nextLine();

        Endpoint[] endpoints = new Endpoint[]{
                new Endpoint("/countries", "", "countries", "all_countries"),
                new Endpoint("/cities", "", "cities", "ukrainian_cities"),
                new Endpoint("/districts", "&city_id=216", "districts", "districts_of_kyiv"),
                new Endpoint("/street/allstreets", "", "streets", "all_streets"),
                new Endpoint("/street/streets", "&city_id=216", "streets", "streets_of_kyiv"),
                new Endpoint("/metro/stations", "&city_id=216", "metro_stations", "metro_stations_of_kyiv"),
                new Endpoint("/routes/bycity", "&city_id=216", "routes", "routes_of_kyiv"),
                new Endpoint("/estatetypes/groups", "", "groups", "groups_of_estate_types"),
                new Endpoint("/estatetypes", "&group_id=14", "types", "estate_types_of_kyiv"),
                new Endpoint("/finish/all", "", "finishes", "real_estate_all_finishes_types"),
                new Endpoint("/dictionary/values", "&type=5", "values", "real_estate_types_of_conditioning"),
                new Endpoint("/dictionary/values", "&type=12", "values", "real_estate_types_of_water_supplying"),
                new Endpoint("/dictionary/values", "&type=14", "values", "real_estate_types_of_gas_supplying"),
                new Endpoint("/dictionary/values", "&type=10", "values", "real_estate_types_of_electricity_supplying"),
                new Endpoint("/dictionary/values", "&type=11", "values", "real_estate_types_of_canalization"),
                new Endpoint("/estate/search", "&id=city_title=Киев&min_price_meter=268&max_price_meter=440", "results", "estates_of_kyiv"),
                new Endpoint("/estate/searchlastedited", "&id=city_title=Киев&min_price_meter=268&max_price_meter=440&new=1", "results", "last_edited_estates_of_kyiv"),
                new Endpoint("/agent/all", "", "agents", "all_agents"),
                new Endpoint("/picture/EstateCoverPhoto", "&estate_id=284&width=200&height=150&crop=1&watermark=0", "pictures", "estate_cover_picture"),
                new Endpoint("/picture/EstatePhoto", "&estate_id=284&width=200&height=150&crop=1&watermark=0", "pictures", "estate_pictures"),
                new Endpoint("/picture/EstateLayout", "&estate_id=284&width=200&height=150&crop=1&watermark=0", "pictures", "layout_pictures"),
                new Endpoint("/picture/BuildingPhoto", "&building_id=284&width=200&height=150&crop=1&watermark=0", "pictures", "building_pictures"),
                new Endpoint("/contragent/getall", "", "contragents", "contragents"),

        };

        for (Endpoint endpoint : endpoints) {
            String jsonResponse = getDataWithExceptionsHandler(endpoint.path(), endpoint.queryParams());
            JSONToXLSX.convert(jsonResponse, filename, endpoint.jsonKey(), endpoint.sheetName());
        }
    }

    private static String getDataWithExceptionsHandler(String endpointPath, String queryParams) {
        try {
            String jsonResponse = workWithAPI.getData(endpointPath, queryParams);
            System.out.println(jsonResponse);
            return jsonResponse;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}