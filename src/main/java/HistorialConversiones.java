import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class HistorialConversiones {
    private List<String> historial;

    public HistorialConversiones() {
        historial = new ArrayList<>();
    }

    public void agregarConversion(String conversion) {
        historial.add(LocalDateTime.now() + " - " + conversion); // Agrega la conversión con la marca de tiempo
    }

    public void mostrarHistorial(JFrame frame) {
        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay conversiones recientes.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String conversion : historial) {
                sb.append(conversion).append("\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(frame, scrollPane, "Historial de Conversiones", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

