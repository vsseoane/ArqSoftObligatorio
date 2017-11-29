package com.roi.supplying.auth;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class RestAuthenticationBean {
    
    public boolean postMethod(String token1, String token2, String completeUrl) {
        AuthDto auth = new AuthDto();
        auth.setToken1(token1);
        auth.setToken2(token2);
        
        URL url;
        Gson gson = new Gson();
        HttpURLConnection conn = null;
        String json = gson.toJson(auth);
        String response = "";
        try {

            url = new URL(completeUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1;"
                    + " WOW64) AppleWebKit/537.11 (KHTML, like Gecko) "
                    + "Chrome/23.0.1271.95 Safari/537.11");

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = json;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush(); 
            response = conn.getResponseMessage();
            BufferedReader br;
            br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String output;
            while ((output = br.readLine()) != null) {
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            conn.disconnect();
            if (response.equals("OK")) {
                return true;
            } else {
                return false;
            }
        }
    }
}
