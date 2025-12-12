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





