# Pong Game

### 1. `PongGame.java`
Ini adalah **Main Class** atau titik masuk (entry point) dari aplikasi.
- **Fungsi Utama**:
  - `main(String[] args)`: Memulai aplikasi dengan membuat instance `GameFrame`.

```java
public class PongGame {
    public static void main(String[] args) {
        new GameFrame();
    }
}
```

### 2. `GameFrame.java`
Kelas ini mewakili **Jendela Aplikasi** (Window).
- **Fungsi Utama**:
  - `GameFrame()`: Mengatur properti jendela (judul, resize, close operation) dan menambahkan `GamePanel`.

```java
public GameFrame() {
    this.add(new GamePanel());
    this.setTitle("Pong Game");
    this.setResizable(false);
    this.setBackground(Color.black);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
}
```

### 3. `GamePanel.java`
Kelas ini adalah **Inti Permainan** (Game Engine). Mengatur loop permainan, rendering, dan fisika.
- **Game Loop (`run`)**: Menjalankan update 60 kali per detik.

```java
public void run() {
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    while (true) {
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;
        if (delta >= 1) {
            move();
            checkCollision();
            repaint();
            delta--;
        }
    }
}
```

- **Collision Detection (`checkCollision`)**: Menangani pantulan bola dan skor.

```java
public void checkCollision() {
    // Pantulan bola dengan dinding atas/bawah
    if(ball.y <= 0)
        ball.setYDirection(-ball.yVelocity);
    if(ball.y >= GAME_HEIGHT-BALL_DIAMETER)
        ball.setYDirection(-ball.yVelocity);
        
    // Pantulan bola dengan paddle
    if(ball.intersects(player1)) {
        ball.xVelocity = Math.abs(ball.xVelocity);
        // ... (logika kecepatan bertambah)
    }
    // ...
}
```

### 4. `Paddle.java`
Kelas yang merepresentasikan **Raket Pemain**.
- **Kontrol Independen (`keyPressed`)**:

```java
public void keyPressed(KeyEvent e) {
    switch(id) {
    case 1:
        if(e.getKeyCode() == KeyEvent.VK_W) {
            setYDirection(-speed);
        }
        // ...
        break;
    case 2:
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            setYDirection(-speed);
        }
        // ...
        break;
    }
}
```

### 5. `Ball.java`
Kelas yang merepresentasikan **Bola**.
- **Pergerakan & Acak Arah Awal**:

```java
public Ball(int x, int y, int width, int height) {
    super(x, y, width, height);
    random = new Random();
    int randomXDirection = random.nextInt(2);
    if(randomXDirection == 0)
        randomXDirection--;
    setXDirection(randomXDirection * initialSpeed);
    // ...
}

public void move() {
    x += xVelocity;
    y += yVelocity;
}
```

### 6. `Score.java`
Kelas utilitas untuk **Tampilan Skor**.
- **Menggambar Skor**:

```java
public void draw(Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Consolas", Font.PLAIN, 60));
    
    g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
    
    g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
    g.drawString(String.valueOf(player2/10) + String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);
}
```

[SOURCE CODE!!!](src/)


