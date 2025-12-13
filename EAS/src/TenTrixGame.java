import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TenTrixGame extends JFrame {

    private int timeLeft = 60;
    private int score = 0;
    private boolean isGameOver = false;

    private Timer gameTimer;
    private JLabel statusLabel;

    private GameGrid grid;
    private GameLogic logic;
    private GameCanvas canvas;

    public TenTrixGame() {

        setTitle("Ten-Trix: Sum to 10!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Window close cleanup
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (gameTimer != null) gameTimer.stop();
            }
        });

        grid = new GameGrid();
        logic = new GameLogic(grid);
        canvas = new GameCanvas(grid, logic);

        // HUD
        JPanel hudPanel = new JPanel(new BorderLayout());
        hudPanel.setBackground(Color.DARK_GRAY);
        hudPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statusLabel = new JLabel("Score: 0 | Time: 60");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hudPanel.add(statusLabel, BorderLayout.CENTER);
        add(hudPanel, BorderLayout.NORTH);

        // Canvas mouse controls
        MouseAdapter mouseHandler = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (canvas.isGameOver) {
                    resetGame();
                    return;
                }
                canvas.startDrag = getGridPos(e);
                canvas.endDrag = canvas.startDrag;
                canvas.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!canvas.isGameOver) {
                    canvas.endDrag = getGridPos(e);
                    canvas.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!canvas.isGameOver) {
                    canvas.endDrag = getGridPos(e);

                    boolean success = logic.processSelection(canvas.startDrag, canvas.endDrag);

                    canvas.startDrag = null;
                    canvas.endDrag = null;

                    score = logic.getScore();
                    updateHUD();

                    canvas.repaint();
                }
            }
        };

        canvas.addMouseListener(mouseHandler);
        canvas.addMouseMotionListener(mouseHandler);
        add(canvas, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        startTimer();
    }

    private Point getGridPos(MouseEvent e) {
        int col = e.getX() / GameGrid.TILE_SIZE;
        int row = e.getY() / GameGrid.TILE_SIZE;

        col = Math.max(0, Math.min(col, GameGrid.GRID_SIZE - 1));
        row = Math.max(0, Math.min(row, GameGrid.GRID_SIZE - 1));

        return new Point(col, row);
    }

    private void startTimer() {
        gameTimer = new Timer(1000, e -> {
            timeLeft--;
            updateHUD();

            if (timeLeft <= 0) {
                canvas.isGameOver = true;
                gameTimer.stop();
                canvas.repaint();
            }
        });
        gameTimer.start();
    }

    private void updateHUD() {
        statusLabel.setText("Score: " + score + " | Time: " + timeLeft);
    }

    private void resetGame() {
        timeLeft = 60;
        score = 0;

        grid.fillGrid();
        logic = new GameLogic(grid);

        canvas.isGameOver = false;
        updateHUD();
        startTimer();
        canvas.repaint();
    }

    public static void main(String[] args) {
        new TenTrixGame().setVisible(true);
    }
}
