package com.example.pavlo_zarudnii.json_view_widget;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

class JSONParser {

    static String getPrice(String s) throws JSONException {
        String price;
        JSONArray arr = new JSONArray(s);
        JSONObject obj0 = arr.getJSONObject(0);
        JSONObject obj1 = arr.getJSONObject(1);
        JSONObject obj2 = arr.getJSONObject(2);
        price = "0";

        if (obj0.getString("ccy").equals("USD")) {
            price = parseExc(obj0.getString("buy")).concat("-").concat(parseExc(obj0.getString("sale")));
        }

        if (obj1.getString("ccy").equals("USD")) {
            price =  parseExc(obj1.getString("buy")).concat("-").concat(parseExc(obj1.getString("sale")));
        }

        if (obj2.getString("ccy").equals("USD")) {
            price = parseExc(obj2.getString("buy")).concat("-").concat(parseExc(obj2.getString("sale")));
        }

        return price;
    }

    static private String parseExc(String s) {
        Double db = Double.parseDouble(s);
        return String.format("%.2f", db);
    }
}