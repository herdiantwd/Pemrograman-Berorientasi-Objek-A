import java.awt.Point;

public class GameLogic {

    private GameGrid grid;
    private int score = 0;

    public GameLogic(GameGrid grid) {
        this.grid = grid;
    }

    public int getScore() {
        return score;
    }

    public int calculateSum(Point start, Point end) {
        
        if (start == null || end == null) return -1;

        int r1 = Math.min(start.y, end.y);
        int r2 = Math.max(start.y, end.y);
        int c1 = Math.min(start.x, end.x);
        int c2 = Math.max(start.x, end.x);

        int sum = 0;
        int[][] g = grid.get();

        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                sum += g[r][c];
            }
        }
        return sum;
    }

    public boolean processSelection(Point start, Point end) {
        
        if (start == null || end == null) return false;

        int sum = calculateSum(start, end);
        if (sum != 10) return false;

        int r1 = Math.min(start.y, end.y);
        int r2 = Math.max(start.y, end.y);
        int c1 = Math.min(start.x, end.x);
        int c2 = Math.max(start.x, end.x);

        int cellsCleared = 0;

        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                grid.setRandom(r, c);
                cellsCleared++;
            }
        }

        score += cellsCleared * 10;
        return true;
    }
    
    
}
