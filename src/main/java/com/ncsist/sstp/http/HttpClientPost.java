package com.ncsist.sstp.http;

import com.ncsist.sstp.model.UserDTO;
import com.ncsist.sstp.server.controller.NettyClientMsgController;
import com.ncsist.sstp.server.handler.NettyClientHandler;
import com.ncsist.sstp.utils.func.DTOParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientPost {
    public static String sendLoginRequest(String username, String password) {
        String apiUrl = "http://localhost:8080/user/login";
        String ctxId = NettyClientMsgController.getClientCtxId();
        String requestBody = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\", \"ctx\":\"" + ctxId+ "\"}";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(requestBody);
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static String sendLoginRequest(UserDTO userDTO) {
        System.out.println("sendLoginRequest : " + userDTO);
        String apiUrl = "http://localhost:8080/user/login";
        String requestBody = DTOParser.parseDTOToString(userDTO);
        System.out.println("requestBody : " + requestBody);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(requestBody);
                outputStream.flush();
            }

//            connection

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 取得Cookie標頭
            Map<String, List<String>> headerFields = connection.getHeaderFields();
            List<String> cookiesHeader = headerFields.get("Set-Cookie");

            if (cookiesHeader != null) {
                for (String cookie : cookiesHeader) {
                    System.out.println("cookie : " + cookie);
                    if (cookie.startsWith("JSESSIONID")) {
                        String sessionId = cookie.split(";")[0];
                        System.out.println("Session ID: " + sessionId);
                        SessionStorage.setSessionId(sessionId);
                    }
                }
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}