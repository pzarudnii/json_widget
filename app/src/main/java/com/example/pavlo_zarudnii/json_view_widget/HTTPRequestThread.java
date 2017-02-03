package com.example.pavlo_zarudnii.json_view_widget;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

class HTTPRequestThread extends Thread{
    private static final String urlString = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    String getInfoString() {
        return output;
    }

    private String output = "";

    private void requestPrice() {

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            output = "USD:" + JSONParser.getPrice(response.toString());

        } catch (Exception e) {
            output = e.toString();
        }
    }

    @Override
    public void run() {
        requestPrice();
    }

    private String getTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.MINUTE)>9) {

            return "Time: " + calendar.get(Calendar.HOUR_OF_DAY)
                    + ":" + calendar.get(Calendar.MINUTE);
        } else {
            return "Time: " + calendar.get(Calendar.HOUR_OF_DAY)
                    + ":0" + calendar.get(Calendar.MINUTE);
        }

    }
}