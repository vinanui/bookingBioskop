import java.util.*;

class Film {
    private String judul;
    private double harga;
    private int kursiTersedia;
    
    public Film(String judul, double harga, int kursiTersedia) {
        this.judul = judul;
        this.harga = harga;
        this.kursiTersedia = kursiTersedia;
    }
    
    public String getJudul() {
        return judul;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public int getKursiTersedia() {
        return kursiTersedia;
    }
    
    public void kurangiKursi(int jumlah) {
        this.kursiTersedia -= jumlah;
    }
}

public class BioskopBooking {
    static List<Film> daftarFilm = new ArrayList<>();
    
    public static void main(String[] args) {
        // data film yang sedang tayang
        daftarFilm.add(new Film("Attack on Titan", 50000, 43));
        daftarFilm.add(new Film("1 Kakak 7 Ponakan", 45000, 30));
        daftarFilm.add(new Film("Perayaan Mati Rasa", 3000, 20));
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== Aplikasi Pemesanan Tiket Bioskop ===");
            System.out.println("1. Lihat Film yang Sedang Tayang");
            System.out.println("2. Pesan Tiket");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = scanner.nextInt();
            switch (pilihan) {
                case 1:
                    tampilkanFilm();
                    break;
                case 2:
                    pesanTiket(scanner);
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }
    
    // menampilkan daftar film
    public static void tampilkanFilm() {
        System.out.println("\nDaftar Film yang Sedang Tayang:");
        for (int i = 0; i < daftarFilm.size(); i++) {
            Film film = daftarFilm.get(i);
            System.out.println((i + 1) + ". " + film.getJudul() 
                    + " - Harga: Rp" + film.getHarga() 
                    + " (Kursi Tersedia: " + film.getKursiTersedia() + ")");
        }
    }
    
    // proses pemesanan tiket
    public static void pesanTiket(Scanner scanner) {
        tampilkanFilm();
        System.out.print("\nMasukkan nomor film yang dipilih: ");
        int pilihanFilm = scanner.nextInt();
        
        if (pilihanFilm < 1 || pilihanFilm > daftarFilm.size()) {
            System.out.println("Film tidak ditemukan.");
            return;
        }
        
        Film filmTerpilih = daftarFilm.get(pilihanFilm - 1);
        
        System.out.print("Masukkan jumlah tiket: ");
        int jumlahTiket = scanner.nextInt();
        
        if (jumlahTiket > filmTerpilih.getKursiTersedia()) {
            System.out.println("Kursi tidak mencukupi.");
            return;
        }
        
        // Penerapan voucher diskon
        System.out.print("Masukkan kode voucher (ketik 'tidak' jika tidak ada): ");
        String kodeVoucher = scanner.next();
        double diskon = 0;
        if (kodeVoucher.equalsIgnoreCase("DISKON10")) {
            diskon = 0.10;
        } else if (kodeVoucher.equalsIgnoreCase("DISKON20")) {
            diskon = 0.20;
        }
        
        double totalHarga = jumlahTiket * filmTerpilih.getHarga() * (1 - diskon);
        System.out.println("Total harga: Rp" + totalHarga);
        
        // Konfirmasi pembayaran
        System.out.print("Konfirmasi pembayaran? (ya/tidak): ");
        String konfirmasi = scanner.next();
        if (konfirmasi.equalsIgnoreCase("ya")) {
            filmTerpilih.kurangiKursi(jumlahTiket);
            System.out.println("\nPembayaran berhasil. Berikut tiket elektronik Anda:");
            System.out.println("====================================");
            System.out.println("Film         : " + filmTerpilih.getJudul());
            System.out.println("Jumlah Tiket : " + jumlahTiket);
            System.out.println("Total Harga  : Rp" + totalHarga);
            System.out.println("====================================");
        } else {
            System.out.println("Pemesanan dibatalkan.");
        }
    }
}