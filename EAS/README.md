# Tentrix Game 


### Anggota Kelompok 
| Name           | NRP        | 
| ---            | ---        | 
| Cornelius Andika | 5025241123 | 
| Derryell Josua Ekklesio Pandiangan | 5025241175 | 
| Herdian Tri Wardhana | 5025241229 |


## Deskripsi 

**TenTrix** adalah sebuah permainan berbasis Java Swing yang menggabungkan elemen puzzle dan logika matematika. Player diminta memilih area persegi pada sebuah grid berisi angka acak 1–9, kemudian menjumlahkan nilai dalam area tersebut.

Pada game ini dibuat aturan jika jumlah area yang dipilih tepat 10, area tersebut akan <br>
– dibersihkan (angka diganti secara acak), <br>
– dan pemain mendapatkan skor berdasarkan jumlah cell yang dihapus.

Game berlangsung selama 60 detik, dilengkapi dengan:
- Grid 8×8 dengan angka acak
- Sistem skor
- Timer hitung mundur
- Tampilan Game Over dan Restart

Game terdiri atas empat kelas utama:
`TenTrixGame`, `GameCanvas`, `GameGrid`, dan `GameLogic`.

## Rancangan Class

<img width="600" height="480" alt="image" src="https://github.com/user-attachments/assets/d3eaca1d-5c1d-4a76-b51a-9832c147a671" />

### `TenTrixGame.java`

Class ini menjadi entry point dan pusat koordinasi komponen game (Main Class)

Fungsi-fungsi : <br>
- Membuat window game (JFrame)
- Membuat HUD (score + timer)
- Menghubungkan grid, logic, dan canvas
- Mengelola event mouse
- Mengatur timer permainan
- Melakukan reset game

Variabel State 
```java
private int timeLeft = 60;
private int score = 0;
private boolean isGameOver = false;
```

Komponen utama dari class lain
```java
private GameGrid grid;
private GameLogic logic;
private GameCanvas canvas;
```

Event Mouse 
```java
public void mousePressed(MouseEvent e) {
    canvas.startDrag = getGridPos(e);
}
public void mouseDragged(MouseEvent e) {
    canvas.endDrag = getGridPos(e);
}
public void mouseReleased(MouseEvent e) {
    logic.processSelection(canvas.startDrag, canvas.endDrag);
}
```

Dari penerapan code tersebut, mouse memiliki event/fungsi yang dipakai yakni :
| Event   | Fungsi                                |
| ------- | ------------------------------------- |
| Press   | Menentukan titik awal drag            |
| Drag    | Menentukan area seleksi               |
| Release | Mengecek apakah area valid (sum = 10) |

### `GameCanvas.java`

Fungsi Class : <br>

- Menggambar: <br>
    - grid 8×8
    - angka di setiap sel
    - area seleksi drag
    - teks Sum
    - overlay Game Over

- Menyimpan status visual: <br>
    - posisi drag (startDrag, endDrag)
    - status game over

Menggambar grid 
```java
g2d.fillRect(x, y, TILE_SIZE, TILE_SIZE);
g2d.drawString(text, textX, textY);
```

Menggambar area yang dipilih
```java
int r1 = Math.min(startDrag.y, endDrag.y);
int r2 = Math.max(startDrag.y, endDrag.y);
int c1 = Math.min(startDrag.x, endDrag.x);
int c2 = Math.max(startDrag.x, endDrag.x);
```

Lalu, pemberian warna berdasarkan konidisi area yang dipilih
```java
if (currentSum == 10) green
else if (currentSum > 10) red
else blue
```

### `GameGrid.java`

Fungsi-fungsi :
- Menyimpan grid 2D (int[8][8])
- Mengisi grid dengan angka acak (1–9)
- Mengubah nilai sel tertentu

Deklarasi variabel yang digunakan untuk menyimpan grid
```java
private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
```

Mengacak angka yang akan digunakan didalam grid 
```java
public void fillGrid() {
    for (int r = 0; r < GRID_SIZE; r++) {
        for (int c = 0; c < GRID_SIZE; c++) {
            grid[r][c] = random.nextInt(9) + 1;
        }
    }
}
```

Merubah nilai dari area yang telah dipilih dan sesuai sum yang diset
```java
public void setRandom(int r, int c) {
    grid[r][c] = random.nextInt(9) + 1;
}
```

### `GameLogic.java`

Fungsi-fungsi : <br>
- Menghitung jumlah angka pada area seleksi
- Mengecek apakah hasil seleksi = 10
- Mengganti angka setelah seleksi benar
- Mengelola skor pemain

Bagian code :

Mengkalkulasi penjumlahan dari area yang telah dipilih oleh Player
```java
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
```

Pengecekan apakah `Sum` hasil seleksi Player dan memanggil fungsi dari `GameGrid.java` berupa `setRandom`

```java
int sum = calculateSum(start, end);
if (sum != 10) return false;

```

```java
for (int r = r1; r <= r2; r++) {
    for (int c = c1; c <= c2; c++) {
        grid.setRandom(r, c); //Mengambil dari GameGrid.java 
        cellsCleared++;
    }
}

```

Penjelesan Game :

Area drag : <br>
Untuk tiap-tiap area drag memiliki representasi warna dalam proses drag : <br>

- Biru : Kurang dari 10 ( < 10) <br>
<img width="565" height="655" alt="image" src="https://github.com/user-attachments/assets/3ea3dc5b-6449-47df-82cc-81f47afe631d" />

- Merah : Lebih dari 10 ( > 10) <br>
<img width="559" height="652" alt="image" src="https://github.com/user-attachments/assets/3e15be46-811c-4589-9adb-d8009705511b" />
 
- Hijau : Tepat 10 ( = 10) <br>
<img width="564" height="654" alt="image" src="https://github.com/user-attachments/assets/d83a67bc-d882-4902-b628-8de1eb0d4d3b" />

<br>
<br>

Total Score didapatkan dari seberapa banyak grid yang kita pilih dalam mendapatkan jumlah 10 <br>

<img width="567" height="655" alt="image" src="https://github.com/user-attachments/assets/f083d908-0445-49c6-9bd3-2a862146ecad" />

## Source Code : 

[Source Code!!!](src/)

## Demo 

[Demo](https://youtu.be/queQPgfjBMc)


