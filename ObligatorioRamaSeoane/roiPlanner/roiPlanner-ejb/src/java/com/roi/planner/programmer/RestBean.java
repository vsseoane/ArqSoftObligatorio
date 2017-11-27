
package com.roi.planner.programmer;

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
import javax.ws.rs.core.Response;

@Stateless
@LocalBean
public class RestBean {
    
    public Response postMethod(String completeUrl, ActuatorProgrammingRequest request) {
        URL url;
        Gson gson = new Gson();
        HttpURLConnection conn = null;
        String json = gson.toJson(request);
        int response = HttpURLConnection.HTTP_CREATED;
        try {

            url = new URL(completeUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            conn.setRequestMethod("POST");
         //   conn.setRequestProperty("Content-Type", "application/json");

         //connection.connect();       
            String input = json;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            response = conn.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(
             (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            conn.disconnect();
            return Response
                    .status(response)
                    .entity(gson.toJson(""))
                    .build();

        }

    }
}
