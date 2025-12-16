# Abstract Class

<img width="848" height="506" alt="image" src="https://github.com/user-attachments/assets/9bc42ce8-6d5d-4d08-9661-45c9c94147b9" />

## Penjelasan

### Class MakhlukHidup

Class MakhlukhHidup bertindak sebagai **kerangka dasar (blueprint)**.

- Menyimpan data umum: Memiliki variabel nama yang dimiliki oleh semua makhluk hidup.
- Menetapkan aturan wajib: Memiliki metode abstrak bernafas() dan tumbuh() Artinya, setiap kelas turunan WAJIB membuat cara bernafas dan tumbuh mereka sendiri.
- Metode umum: Memiliki metode info() yang sudah jadi (bukan abstrak) untuk sekadar menampilkan nama.

### Pengembangan ke Kelas Turunan (Implementasi)
Setiap kelas turunan "menandatangani kontrak" dengan MakhlukHidup untuk melengkapi detail yang belum ada:

- Manusia
  - Bernafas: Diimplementasikan menggunakan paru-paru.
  - Tumbuh: Diimplementasikan dari bayi hingga dewasa.
  - Fitur Khusus: Menambahkan kemampuan unik berpikir() yang tidak dimiliki makhluk hidup lain di kode ini.
  - 
- Hewan
  - Bernafas: Diimplementasikan sesuai organ pernafasannya (bergantung jenis).
  - Tumbuh: Secara fisik dan insting.
  - Data Tambahan: Punya variabel ekstra jenis (misal: Mamalia).
  - 
- Tumbuhan
  - Bernafas: Diimplementasikan lewat stomata.
  - Tumbuh: Melalui proses fotosintesis.




## Code

`MakhlukHidup.java`
```java
public abstract class MakhlukHidup {
    protected String nama;

    public MakhlukHidup(String nama) {
        this.nama = nama;
    }

    public abstract void bernafas();
    public abstract void tumbuh();

    public void info() {
        System.out.println("Nama: " + nama);
    }
}
```

`Main.java`
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Implementasi Abstract Class Makhluk Hidup ===\n");

        MakhlukHidup manusia = new Manusia("Budi");
        MakhlukHidup hewan = new Hewan("Kucing", "Mamalia");
        MakhlukHidup tumbuhan = new Tumbuhan("Bunga Mawar");

        // Manusia
        manusia.info();
        manusia.bernafas();
        manusia.tumbuh();
        if (manusia instanceof Manusia) {
            ((Manusia) manusia).berpikir();
        }
        System.out.println();

        // Hewan
        hewan.info();
        hewan.bernafas();
        hewan.tumbuh();
        System.out.println();

        // Tumbuhan
        tumbuhan.info();
        tumbuhan.bernafas();
        tumbuhan.tumbuh();
        System.out.println();
    }
}
```

`Manusia.java`
```java
public class Manusia extends MakhlukHidup {
    
    public Manusia(String nama) {
        super(nama);
    }

    @Override
    public void bernafas() {
        System.out.println(nama + " bernafas menggunakan paru-paru.");
    }

    @Override
    public void tumbuh() {
        System.out.println(nama + " tumbuh berkembang dari bayi hingga dewasa.");
    }

    public void berpikir() {
        System.out.println(nama + " memiliki akal budi untuk berpikir.");
    }
}
```

`Tumbuhan.java`
```java
public class Tumbuhan extends MakhlukHidup {
    
    public Tumbuhan(String nama) {
        super(nama);
    }

    @Override
    public void bernafas() {
        System.out.println(nama + " bernafas melalui stomata dan lentisel.");
    }

    @Override
    public void tumbuh() {
        System.out.println(nama + " tumbuh dengan proses fotosintesis.");
    }
}
```

`Hewan.java`
```java
public class Hewan extends MakhlukHidup {
    private String jenis;

    public Hewan(String nama, String jenis) {
        super(nama);
        this.jenis = jenis;
    }

    @Override
    public void bernafas() {
        System.out.println(nama + " (" + jenis + ") bernafas sesuai organ pernafasannya.");
    }

    @Override
    public void tumbuh() {
        System.out.println(nama + " tumbuh secara fisik dan insting.");
    }
}
```
