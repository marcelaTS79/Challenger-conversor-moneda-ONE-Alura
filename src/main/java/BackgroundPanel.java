import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        try {
            // Cargar la imagen de fondo desde los recursos
            backgroundImage = ImageIO.read(getClass().getResource("/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
