import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {
    private static final String API_KEY = "7411faf4e9f5f03d4a2bb81f"; // Reemplaza con tu clave API
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD"; // Cambia 'USD' si es necesario

    public static JSONObject getExchangeRates() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            return new JSONObject(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

