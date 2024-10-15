import org.json.JSONObject;

public class AnalizadorJson {
    public static void analizarYMostrarTasas(JSONObject json) {
        JSONObject tasas = json.getJSONObject("conversion_rates");
        System.out.println("Tasas de cambio:");

        for (String clave : tasas.keySet()) {
            System.out.println(clave + ": " + tasas.getDouble(clave));
        }
    }
}

