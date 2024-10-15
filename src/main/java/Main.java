import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Crear el historial de conversiones
        HistorialConversiones historial = new HistorialConversiones();

        // Paso 1: Obtener las tasas de cambio desde la API
        JSONObject jsonResponse = ApiClient.getExchangeRates();

        if (jsonResponse != null) {
            // Crear el panel de fondo
            BackgroundPanel backgroundPanel = new BackgroundPanel();

            // Crear un JFrame
            JFrame frame = new JFrame("Conversor de Monedas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 350);
            frame.setContentPane(backgroundPanel);
            frame.setLayout(new BorderLayout());

            // Obtener las tasas de cambio
            JSONObject tasas = jsonResponse.getJSONObject("conversion_rates");
            Iterator<String> keys = tasas.keys();

            // Crear los modelos de las JComboBox
            DefaultComboBoxModel<String> monedaOrigenModel = new DefaultComboBoxModel<>();
            DefaultComboBoxModel<String> monedaDestinoModel = new DefaultComboBoxModel<>();
            while (keys.hasNext()) {
                String moneda = keys.next();
                monedaOrigenModel.addElement(moneda);
                monedaDestinoModel.addElement(moneda);
            }

            // Crear componentes
            JTextField montoField = new JTextField(10);
            JComboBox<String> monedaOrigenBox = new JComboBox<>(monedaOrigenModel);
            JComboBox<String> monedaDestinoBox = new JComboBox<>(monedaDestinoModel);
            JButton convertirButton = new JButton("Convertir");
            JButton historialButton = new JButton("Ver Historial");

            // Personalizar botones
            convertirButton.setBackground(new Color(173, 216, 230));
            convertirButton.setFont(new Font("Arial", Font.BOLD, 14));
            historialButton.setBackground(new Color(144, 238, 144)); // Verde claro

            // Etiquetas
            JLabel labelMonto = new JLabel("Monto:");
            JLabel labelMonedaOrigen = new JLabel("Moneda Origen:");
            JLabel labelMonedaDestino = new JLabel("Moneda Destino:");

            // Panel para las entradas
            JPanel inputPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            inputPanel.setOpaque(false);

            // Posicionar componentes
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.WEST;

            gbc.gridx = 0;
            gbc.gridy = 0;
            inputPanel.add(labelMonto, gbc);
            gbc.gridx = 1;
            inputPanel.add(montoField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            inputPanel.add(labelMonedaOrigen, gbc);
            gbc.gridx = 1;
            inputPanel.add(monedaOrigenBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            inputPanel.add(labelMonedaDestino, gbc);
            gbc.gridx = 1;
            inputPanel.add(monedaDestinoBox, gbc);

            // Centrar el botón "Convertir"
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            inputPanel.add(convertirButton, gbc);

            // Añadir el botón de historial debajo del de convertir
            gbc.gridy = 4;
            inputPanel.add(historialButton, gbc);

            frame.add(inputPanel, BorderLayout.CENTER);

            // Acción del botón "Convertir"
            convertirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        double monto = Double.parseDouble(montoField.getText());
                        String monedaOrigen = (String) monedaOrigenBox.getSelectedItem();
                        String monedaDestino = (String) monedaDestinoBox.getSelectedItem();

                        if (tasas.has(monedaOrigen) && tasas.has(monedaDestino)) {
                            double tasaConversion = tasas.getDouble(monedaDestino) / tasas.getDouble(monedaOrigen);
                            double resultado = monto * tasaConversion;

                            // Agregar la conversión al historial
                            String conversion = monto + " " + monedaOrigen + " a " + resultado + " " + monedaDestino;
                            historial.agregarConversion(conversion);

                            JOptionPane.showMessageDialog(frame, "Resultado: " + resultado + " " + monedaDestino);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Moneda no válida.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Ingrese un monto válido.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error al realizar la conversión.");
                    }
                }
            });

            // Acción del botón "Ver Historial"
            historialButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    historial.mostrarHistorial(frame);
                }
            });

            frame.setVisible(true);
        } else {
            System.out.println("Error al obtener los datos de la API.");
        }
    }
}
