# Practice Grouping Object

Pada pertemuan ke-5 ini, saya diberikan latihan utnuk membuat sebuah sistem sederhana tentang pengambilan mata kuliah. Lalu pada hasil latihan saya, diisi 4 class yaitu `Dosen`, `Mahasiswa`, `MataKuliah`, dan `Main` class untuk menghubungkan semuanya.

Dokumentasi :

**`Main`**

```java
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
    
        Dosen d1 = new Dosen("Dr. Sarwosri, S.Kom., MT.");
        Dosen d2 = new Dosen("Ir. Suhadi Lili, M.T.I.");
        Dosen d3 = new Dosen("Prof. Tohari Ahmad, S.Kom., M.IT., Ph.D.");
        Dosen d4 = new Dosen("Imam Mustafa Kamal, S.ST, Ph.D.");
        
        MataKuliah mk1 = new MataKuliah("IF001","Dasar Pemrograman",4,20,d2);
        MataKuliah mk2 = new MataKuliah("IF002","Konsep Pengembangan Perangkat Lunak",2,30,d1);
        MataKuliah mk3 = new MataKuliah("IF003","Konse Kecerdasan Perangkat Lunak",3,40,d4);
        MataKuliah mk4 = new MataKuliah("IF004","Sistem Digital",3,20,d3);
        
        System.out.println("Masukkan nama : ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan NRP : ");
        String nrp = scanner.nextLine();
        
        System.out.println("Hai, " + nama + "!");
        
        System.out.println("=== Daftar Mata Kuliah ===");
        System.out.println(mk1);
        System.out.println(mk2);
        System.out.println(mk3); 
        System.out.println(mk4); 
        
        Mahasiswa mhs = new Mahasiswa(nrp, nama);
        
        
        while(true){
            System.out.print("\nMasukkan kode mata kuliah yang ingin diambil (atau ketik 'selesai'): ");
            String kode = scanner.nextLine();
            
            if (kode.equalsIgnoreCase("selesai")) {
                break;
            }
            
            MataKuliah mkDipilih = null;
            if (mk1.getId().equalsIgnoreCase(kode)) {
                mkDipilih = mk1;
            } else if (mk2.getId().equalsIgnoreCase(kode)) {
                mkDipilih = mk2;
            } else if (mk3.getId().equalsIgnoreCase(kode)) {
                mkDipilih = mk3;
            } else if (mk4.getId().equalsIgnoreCase(kode)) {
                mkDipilih = mk4;
            }

            if (mkDipilih != null) {
                mhs.ambilMatkul(mkDipilih);
            } else {
                System.out.println("Kode matakuliah tidak ditemukan!");
            }
        }
        
        System.out.println("\n=== FRS Mahasiswa ===");
        mhs.tampilanFRS();

        scanner.close();
    }
    
}
```

**`MataKuliah`**

```java
import java.util.*;

public class MataKuliah {
    private String id;
    private String namakelas;
    private int sks;
    private int kapasitas;
    private int jumlah;
    private Dosen dosenPengampu;
    private ArrayList<Mahasiswa> peserta;

    public MataKuliah(String kode, String nama, int sks, int kapasitas, Dosen dosenPengampu) {
        this.id = kode;
        this.namakelas = nama;
        this.sks = sks;
        this.jumlah = 0;
        this.kapasitas = kapasitas;
        this.dosenPengampu = dosenPengampu;
        this.peserta = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNamaKelas() {
        return namakelas;
    }

    public int getSks() {
        return sks;
    }
    
    public int getKapasitas() {
        return kapasitas;
    }
    
    public int getJumlah() {
        return jumlah;
    }

    public Dosen getDosenPengampu() {
        return dosenPengampu;
    }
    
    public ArrayList<Mahasiswa> getPeserta() {
        return peserta;
    }
    
    public boolean adaKursiKosong() {
        if(jumlah < kapasitas){
            return true;
        } else {
            return false;
        }
    }

    public boolean tambahPeserta(Mahasiswa m) {
        if (adaKursiKosong()) {
            peserta.add(m);
            jumlah++;
            return true;
        } else {
            return false;
        }
    }
    public String toString() {
        return id + " - " + namakelas + " (" + sks + " SKS) | Dosen: " 
               + dosenPengampu.getNama() + " | Kapasitas: " 
               + peserta.size() + "/" + kapasitas;
    }

}

```

**`Dosen`**

```java
import java.util.*;

public class Dosen{
    private String nama;
    
    public Dosen(String nama){
        this.nama = nama;
    }
    
     public String getNama() {
        return nama;
    }
}
```

**`Mahasiswa`**

```java
import java.util.*;

public class Mahasiswa
{
    private String nama;
    private String nrp;
    private ArrayList<MataKuliah> frs;
    
    public Mahasiswa(String nrp, String nama) {
        this.nrp = nrp;
        this.nama = nama;
        this.frs = new ArrayList<>();
    }
    
    public String getNrp() {
        return nrp;
    }

    public String getNama() {
        return nama;
    }
    
    public void ambilMatkul(MataKuliah mk){
        if(frs.contains(mk)){ 
            System.out.println("Kelas telah diambil!");
        } else if(mk.tambahPeserta(this)){
            frs.add(mk);
            System.out.println(nama + " berhasil mengambil " + mk.getNamaKelas());     
        } else {
            System.out.println("Kelas " + mk.getNamaKelas() + " sudah penuh, " + nama + " tidak bisa daftar.");
        }
    }
    
    public void tampilanFRS(){
        System.out.println("FRS Mahasiswa: " + nama + " (" + nrp + ")");
        for(MataKuliah mk : frs){
            System.out.println(" - " + mk);
        }
    }
}
```

Bentuk hubungan Class :

<img width="500" height="600" alt="image" src="https://github.com/user-attachments/assets/b954dcff-8c48-4343-8ebb-77aae2e0d8d7" />

<img width="1919" height="1014" alt="image" src="https://github.com/user-attachments/assets/224c007c-d0a8-47f9-9134-71890df68dd5" />




