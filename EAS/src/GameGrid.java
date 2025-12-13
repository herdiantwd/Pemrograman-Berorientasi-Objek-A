import java.util.Random;

public class GameGrid {

    public static final int GRID_SIZE = 8;
    public static final int TILE_SIZE = 60;

    private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    private Random random = new Random();

    public GameGrid() {
        fillGrid();
    }

    public void fillGrid() {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                grid[r][c] = random.nextInt(9) + 1;
            }
        }
    }

    public int[][] get() {
        return grid;
    }

    public void setRandom(int r, int c) {
        grid[r][c] = random.nextInt(9) + 1;
    }
}
