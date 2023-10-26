package com.ncsist.sstp.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsist.sstp.vo.Course;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class HttpClientGetData {
    public static String sendGetRequest(String serverUrl) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(serverUrl);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
//            String sessionId = "JSESSIONID=<" + SessionStorage.getSessionId() + ">";
            String sessionId = "JSESSIONID=" + SessionStorage.getSessionId();
            connection.setRequestProperty("Cookie" , sessionId);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("Response Data: " + response.toString());

            connection.disconnect();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverUrl;
    }
}