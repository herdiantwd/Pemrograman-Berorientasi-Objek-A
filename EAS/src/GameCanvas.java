import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    public Point startDrag, endDrag;
    public boolean isGameOver = false;
    public int timeLeft = 60;

    private GameGrid grid;
    private GameLogic logic;

    public GameCanvas(GameGrid grid, GameLogic logic) {
        this.grid = grid;
        this.logic = logic;

        setPreferredSize(new Dimension(
            GameGrid.GRID_SIZE * GameGrid.TILE_SIZE,
            GameGrid.GRID_SIZE * GameGrid.TILE_SIZE
        ));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawGame(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int[][] arr = grid.get();

        // Draw grid
        for (int r = 0; r < GameGrid.GRID_SIZE; r++) {
            for (int c = 0; c < GameGrid.GRID_SIZE; c++) {

                int x = c * GameGrid.TILE_SIZE;
                int y = r * GameGrid.TILE_SIZE;

                g2d.setColor(new Color(245, 245, 245));
                g2d.fillRect(x, y, GameGrid.TILE_SIZE, GameGrid.TILE_SIZE);

                g2d.setColor(new Color(220, 220, 220));
                g2d.drawRect(x, y, GameGrid.TILE_SIZE, GameGrid.TILE_SIZE);

                g2d.setColor(Color.DARK_GRAY);
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 24));

                String text = String.valueOf(arr[r][c]);
                FontMetrics fm = g2d.getFontMetrics();
                int textX = x + (GameGrid.TILE_SIZE - fm.stringWidth(text)) / 2;
                int textY = y + (GameGrid.TILE_SIZE - fm.getHeight()) / 2 + fm.getAscent();

                g2d.drawString(text, textX, textY);
            }
        }

        // Selection overlay
        if (startDrag != null && endDrag != null && !isGameOver) {

            int r1 = Math.min(startDrag.y, endDrag.y);
            int r2 = Math.max(startDrag.y, endDrag.y);
            int c1 = Math.min(startDrag.x, endDrag.x);
            int c2 = Math.max(startDrag.x, endDrag.x);

            int x = c1 * GameGrid.TILE_SIZE;
            int y = r1 * GameGrid.TILE_SIZE;
            int w = (c2 - c1 + 1) * GameGrid.TILE_SIZE;
            int h = (r2 - r1 + 1) * GameGrid.TILE_SIZE;

            int currentSum = 0;
            for (int r = r1; r <= r2; r++) {
                for (int c = c1; c <= c2; c++) {
                    currentSum += arr[r][c];
                }
            }

            Color overlayColor;
            if (currentSum == 10)
                overlayColor = new Color(0, 255, 0, 100);
            else if (currentSum > 10)
                overlayColor = new Color(255, 0, 0, 100);
            else
                overlayColor = new Color(0, 0, 255, 50);

            g2d.setColor(overlayColor);
            g2d.fillRoundRect(x, y, w, h, 10, 10);

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
            String infoText = "Sum:  " + currentSum;

            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(infoText);
            int infoX = x + (w - textWidth) / 2; // Center text horizontally
            int infoY = y - 5;

            // If selection is near top, draw text below the selection box
            if (y < 25) {
                infoY = y + h + 20;
            }
            g2d.drawString(infoText, infoX, infoY);
            //g2d.drawString("Sum: " + currentSum, x + 5, y - 5);
        }

        // Game Over
        if (isGameOver) {

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            g2d.setColor(new Color(0, 0, 0, 150));
            g2d.fillRect(0, 0, panelWidth, panelHeight);

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            String msg = "TIME'S UP!";
            String sub = "Click to Restart";

            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(msg, (panelWidth - fm.stringWidth(msg)) / 2, panelHeight / 2 - 20);

            g2d.setFont(new Font("Arial", Font.PLAIN, 16));
            fm = g2d.getFontMetrics();
            g2d.drawString(sub, (panelWidth - fm.stringWidth(sub)) / 2, panelHeight / 2 + 20);
        }
    }
}
