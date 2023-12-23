package com.example.location.Service;

import com.example.location.LocationResponse;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class LocationService {

    private static final String BASE_URL = "https://ipapi.co/";

    public LocationResponse findLocationByIp(String ipAddress) throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + ipAddress + "/json/")
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        if (!response.isSuccessful()) {
            response.close();
            throw new IOException("Failed to fetch location data: " + response);
        }

        String locationResponse = Objects.requireNonNull(response.body()).string();
        response.close();

        JSONObject json = new JSONObject(locationResponse);
        String country = (String) json.get("country");
        String city = (String) json.get("city");
        return new LocationResponse(ipAddress, city, country);
    }


}
