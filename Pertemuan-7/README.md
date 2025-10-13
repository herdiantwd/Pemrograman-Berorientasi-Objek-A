# Latihan TechSupport

Pada pertemuan ke-7 ini diberikan sebuah materi tentang `More‑Sophisticated Behavior` pada java. Untuk latihannya, mengimplementasikan TechSupport pada contoh yang
diberikan namun diganti dalam ranah FRS/pengambilan mata kuliah. Sehingga, saya bentuk code bernama `AcademicSupport`

<br>

AcademicSupport
```java
import java.util.Map;  
import java.util.HashMap;  
import java.util.Random;
import java.util.Scanner;
  
public class AcademicSupport {  
   private Map<String, String> responses;  
   private Random random;
   
   public AcademicSupport() {  
    responses = new HashMap<>();  
    random = new Random();  
       
    responses.put("frs", "FRS bisa diisi melalui portal akademik. Pastikan jadwal pengisian sudah dibuka.");
    responses.put("dosen", "Silakan cek nama dosen pengampu di jadwal mata kuliah atau hubungi bagian akademik.");
    responses.put("jadwal", "Jadwal kuliah dapat dilihat di portal mahasiswa atau papan pengumuman jurusan.");
    responses.put("bentrok", "Jika jadwal bentrok, coba ubah salah satu mata kuliah ke kelas paralel lain.");
    responses.put("batal", "Pembatalan mata kuliah bisa dilakukan dengan memilih drop di mata kuliah yang telah diambil");
    responses.put("prasyarat", "Pastikan Anda sudah memenuhi mata kuliah prasyarat sebelum mengambil yang ini.");
    responses.put("penuh", "Jika kelas penuh, hubungi dosen pengampu atau bagian akademik untuk solusi.");
    responses.put("tambah", "Penambahan mata kuliah bisa dilakukan selama periode revisi FRS.");
    responses.put("kuota", "Kuota kelas diatur oleh sistem. Coba cek kembali di jam berbeda.");
    responses.put("nilai", "Nilai akan muncul setelah dosen menginput di sistem akademik.");
   }  
   
   public String getResponse(String userInput) {  
     String[] words = userInput.toLowerCase().split("\\s+");  
     for (String w : words) {  
       if (responses.containsKey(w)) {  
         return responses.get(w);  
       }  
     }  
      
     String[] generic = {  
       "Coba jelaskan lebih rinci.",  
       "Saya belum mengerti, bisa ulangi?",  
       "Bisakah Anda memberi detail lebih lanjut?"  
     };  
     return generic[random.nextInt(generic.length)];  
   }  
   
   public static void main(String[] args) {  
    AcademicSupport as = new AcademicSupport(); 
    String report;
    Scanner scanner = new Scanner(System.in);
    
    do{
        System.out.println("Apa permasalahan yang sedang dihadapi? (ketik 'exit' jika telah selesai)");
        report = scanner.nextLine();
        if(!report.equals("exit")){
            System.out.println(as.getResponse(report));   
        }
    } while(!report.equals("exit"));
    System.out.println("Terima kasih telah menghubungi layanan kami");
     
   }  
 }  
```
