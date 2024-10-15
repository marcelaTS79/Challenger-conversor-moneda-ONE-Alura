import org.json.JSONObject;

public class FiltroMonedas {
    public static void mostrarMonedasFiltradas(JSONObject json) {
        JSONObject tasas = json.getJSONObject("conversion_rates");

        // Monedas de interés que queremos filtrar
        String[] monedasInteres = {"USD", "EUR", "COP", "JPY", "GBP"};

        System.out.println("Tasas de cambio filtradas:");
        for (String moneda : monedasInteres) {
            if (tasas.has(moneda)) {
                System.out.println(moneda + ": " + tasas.getDouble(moneda));
            }
        }
    }
}

