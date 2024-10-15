import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRateService {

    private static final String API_KEY = "7411faf4e9f5f03d4a2bb81f"; // Tu API Key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public String getExchangeRates() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString(); // Retorna la respuesta en formato JSON
            } else {
                System.out.println("Error en la conexión: " + status);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

