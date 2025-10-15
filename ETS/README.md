# Proyek Vending Coffe

Pada ETS kali ini diberikan sebuah proyek tentang Vending Coffe.
<br>

Untuk fitur-fitur yang saya buat terdapat 2 menu utama yaitu :
1. Beli Produk
   Dalam fitur ini, diberikan pilihan kopi yang telah dimasukkan

   <img width="641" height="500" alt="image" src="https://github.com/user-attachments/assets/58e7fa5c-0537-4695-a803-2c0cd55012fb" />

   Lalu lanjut memilih ukuran, memasukkan saldo, lalu status berhasil/tidak
   
   <img width="641" height="500" alt="image" src="https://github.com/user-attachments/assets/4a9853fd-9378-4e4f-8ea1-a5d0fdb10344" />

2. Mode Admin
   Mode ini menambah jumlah stok dan melihat riwayat transaksi. Untuk memasuki mode ini saya bentuk membutuhkan sebuah password. Pada projek ini saya set
   passwordnya berupa `admin123`.

   Contoh menambah jumlah stok

   <img width="641" height="500" alt="image" src="https://github.com/user-attachments/assets/b31e1e48-32d4-4ec8-a102-8eac34904eae" />

   Contoh meilhat riwayat transaksi :

   <img width="641" height="500" alt="image" src="https://github.com/user-attachments/assets/34386405-ba13-4c02-ab09-1d73ca69f10b" />

   
<br>


Gambar rancangan Class

<img width="641" height="500" alt="image" src="https://github.com/user-attachments/assets/5d4149ce-0d69-4f7d-afcf-b34e69278f31" />

<br>

## Demo Video

### [Link Demo](https://youtu.be/NRQW9Houi9Q)

## Code

Product
```java
import java.util.*;

public class Product
{
    private int id;
    private String namaProduk;
    private int hargaKopi;
    private int stok;

    public Product(int id, String namaProduk,int hargaKopi,int stok)
    {
        this.id = id;
        this.namaProduk = namaProduk;
        this.hargaKopi = hargaKopi;
        this.stok = stok;
    }
    
    public int getId(){
        return id;
    }
    
    public String getNama(){
        return namaProduk;
    }
    
    public int getHarga(){
        return hargaKopi;
    }
    
    public int getStok(){
        return stok;
    }
    
    public boolean sediaStok(){
        if(stok > 0){
            return true;
        }
        return false;
    }
    
    public void penguranganStok(){
        if(stok > 0){
            stok--;
        }
    }
    
    public void penambahanStok(int jumlah){
        stok += jumlah;
    }
    
    public String toString() {
        return id + ". " + namaProduk + " - Rp" + hargaKopi + " (stok: " + stok + ")";
    }

}
```

Transaksi
```java
import java.util.*;

public class Transaksi
{
    private Date tgl;
    private int harga;
    private String namaKopi;
    private String ukuranKopi;
    private String status;

    public Transaksi(String namaKopi, String ukuranKopi,int harga)
    {
        this.tgl = new Date();
        this.namaKopi = namaKopi;
        this.status = status;
        this.ukuranKopi = ukuranKopi;
        this.harga = harga;
    }

    public String toString()
    {
        return "[" + tgl + "]" + " -- " + namaKopi + " ( Ukuran : " + ukuranKopi + ")" + " Total : Rp. " + harga;
    }
}
```

TambahanUkuran
```java
public class TambahanUkuran
{
    private int id;
    private int harga;
    private String ukuran;
    
    public TambahanUkuran(int id, String ukuran,int harga)
    {
        this.id = id;
       this.ukuran = ukuran;
       this.harga = harga;
    }
    
    public int getId(){
        return id;
    }
    
    public int getHarga(){
        return harga;
    }

    public String getUkuran(){
        return ukuran;
    }
    
    public String toString() {
        return id + ". " + ukuran + " - Rp" + harga;
    }
}
```

MesinKopi
```java
import java.util.*;

public class MesinKopi
{
    private ArrayList<Product> menuKopi;
    private ArrayList<TambahanUkuran> ukuran;
    private ArrayList<Transaksi> transaksi;
    private int saldo;
    
    public MesinKopi()
    {
        this.menuKopi = new ArrayList();
        this.ukuran = new ArrayList();
        this.transaksi = new ArrayList();
        this.saldo = 0;
    }
    
    public void tambahStok(Product p){
        menuKopi.add(p);
    }
    
    public void tambahUkuran(TambahanUkuran tu){
        ukuran.add(tu);
    }
    
    public void displayMenu(){
        System.out.println("\nMENU VENDING COFFE");
        for (Product p : menuKopi) {
            System.out.println(p);
        }
    }
    
    public void displayUkuran(){
        System.out.println("\nUkuran Kopi");
        for (TambahanUkuran tu : ukuran) {
            System.out.println(tu);
        }
    }
    
    public void masukkanSaldo(int jumlah){
        saldo = saldo + jumlah;
    }
    
    public Product pilihProduk(int id){
        for(Product p : menuKopi){
            if(p.getId()==id) return p;
        } 
        return null;
    }
    
    public TambahanUkuran pilihUkuran(int id){
        for(TambahanUkuran tu : ukuran){
            if(tu.getId()==id) return tu;
        } 
        return null;
    }
    
    public void prosesTransaksi(Product p, TambahanUkuran add){
        if(p==null){
            System.out.println("Produk belum dimasukkan!");
            return;
        }
        
        if(p.sediaStok()==false){
            System.out.println("Maaf, stok " + p.getNama() + " habis!");
            return;
        }
        
        if(saldo < p.getHarga() + add.getHarga() ){
            System.out.println("Uang Anda tidak cukup!");
            return;
        }
        
        saldo -= p.getHarga() + add.getHarga();
        p.penguranganStok();
        System.out.println("\nProduk " + p.getNama() + " dikeluarkan...");
        System.out.println("Kembalian Anda: Rp" + saldo);
        transaksi.add(new Transaksi(p.getNama(),add.getUkuran(), p.getHarga() + add.getHarga()));
        saldo = 0;
        
    }
    
    public void tampilTransaksi() {
        System.out.println("\n--- Log Transaksi ---");
        if (transaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (Transaksi t : transaksi) {
                System.out.println(t);
            }
        }
    }
    
    public void refillProduk(int id, int jumlah) {
        Product p = pilihProduk(id);
        if (p != null) {
            p.penambahanStok(jumlah);
            System.out.println("Stok " + p.getNama() + " berhasil ditambah " + jumlah + " buah.");
        } else {
            System.out.println("Produk tidak ditemukan!");
        }
    }
}
```

Main
```java
import java.util.*;
public class Main
{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        MesinKopi mch = new MesinKopi();
        
        mch.tambahStok(new Product(1, "Cappucino", 15000, 5));
        mch.tambahStok(new Product(2, "Americano", 17000, 1));
        mch.tambahStok(new Product(3, "Kopi Aren", 16000, 10));
        
        mch.tambahUkuran(new TambahanUkuran(1, "Kecil", 0));
        mch.tambahUkuran(new TambahanUkuran(2, "Sedang", 2000));
        mch.tambahUkuran(new TambahanUkuran(3, "Besar", 5000));

        while (true) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Beli Produk");
            System.out.println("2. Mode Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            int pilihan = sc.nextInt();

            if (pilihan == 1) {
                mch.displayMenu();
                System.out.print("\nMasukkan jenis produk: ");
                int id = sc.nextInt();
                Product p = mch.pilihProduk(id);

                if (p == null) {
                    System.out.println("Produk tidak ditemukan!");
                    continue;
                }
                
                mch.displayUkuran();
                System.out.print("\nMasukkan Ukuran Kopi: ");
                int idukuran = sc.nextInt();
                TambahanUkuran tu = mch.pilihUkuran(idukuran);
                
                int totalHarga = p.getHarga() + tu.getHarga();
                System.out.println("Anda memilih: " + p.getNama() + " (Rp. " + p.getHarga() + ", Ukuran : " + tu.getUkuran() + ", RP. " + tu.getHarga() + ")");
                System.out.print("Total Harga: Rp. " + totalHarga);
                System.out.print("\nMasukkan uang Anda: Rp");
                int uang = sc.nextInt();

                mch.masukkanSaldo(uang);
                mch.prosesTransaksi(p,tu);

            } else if (pilihan == 2) {
                System.out.print("Masukkan password admin: ");
                String pass = sc.next();

                if (!pass.equals("admin123")) {
                    System.out.println("Password salah!");
                    continue;
                }

                int opsi;
                do {
                    System.out.println("\n=== MODE ADMIN ===");
                    System.out.println("1. Tambah Stok");
                    System.out.println("2. Lihat Log Transaksi");
                    System.out.println("3. Kembali");
                    System.out.print("Pilih: ");
                    opsi = sc.nextInt();

                    if (opsi == 1) {
                        mch.displayMenu();
                        System.out.print("Masukkan ID produk: ");
                        int id = sc.nextInt();
                        System.out.print("Masukkan jumlah stok tambahan: ");
                        int jumlah = sc.nextInt();
                        mch.refillProduk(id, jumlah);

                    } else if (opsi == 2) {
                        mch.tampilTransaksi();
                    }

                } while (opsi != 3);

            } else if (pilihan == 3) {
                System.out.println("Terima kasih telah menggunakan Vending Coffe Machine!");
                break;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }

        sc.close();
    }
        
        
}
```




