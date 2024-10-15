import javax.swing.*;
import org.json.JSONObject;

public class MostrarResultados {
    public static void mostrarOpciones(JSONObject json) {
        JSONObject tasas = json.getJSONObject("conversion_rates");

        // Obtener las claves de las tasas como un array
        String[] monedasDisponibles = tasas.keySet().toArray(new String[0]);

        // Mostrar un menú desplegable para seleccionar la moneda de origen
        String monedaOrigen = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la moneda de origen:",
                "Conversor de Monedas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                monedasDisponibles,
                monedasDisponibles[0] // Selección predeterminada
        );

        // Mostrar un menú desplegable para seleccionar la moneda de destino
        String monedaDestino = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la moneda de destino:",
                "Conversor de Monedas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                monedasDisponibles,
                monedasDisponibles[0] // Selección predeterminada
        );

        // Pedir al usuario que ingrese la cantidad a convertir
        String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad en " + monedaOrigen + ":");
        double cantidad = Double.parseDouble(cantidadStr);

        // Realizar la conversión
        double tasaOrigen = tasas.getDouble(monedaOrigen);
        double tasaDestino = tasas.getDouble(monedaDestino);
        double cantidadConvertida = (cantidad * tasaDestino) / tasaOrigen;

        // Mostrar el resultado
        JOptionPane.showMessageDialog(null, cantidad + " " + monedaOrigen + " es equivalente a " + cantidadConvertida + " " + monedaDestino);
    }
}


