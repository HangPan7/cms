package com.xs.sw.veh.module.ias.enums;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeoCoding {

    public static void main(String[] args) {
        String address = "江苏省盐城市亭湖区";
        String apiKey = "04ef2fd17b896937caecab1aeafb5b6e";
        String requestUrl = String.format("https://restapi.amap.com/v3/geocode/geo?key=%s&address=%s", apiKey, address);

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}