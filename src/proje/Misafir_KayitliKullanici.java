/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author muhem
 */
public abstract class Misafir_KayitliKullanici extends Kullanici implements SiparisIslemleri, SilmeIslemi {
    private static String email = "Misafir";

    public String emailAl() {
        return email;
    }
    
    @Override
    public abstract void secenekleriGoster(Kullanici temp);
    
    public abstract String[] kullaniciBilgileriniOlustur();
    
    public boolean sepetiListele() {
        String dosyaIsmi = emailAl();
        int flag = 0;
        try {    
            File dosya = new File(dosyaIsmi + "Sepeti.txt");
            if (!dosya.exists()) {
                dosya.createNewFile();
            }
            FileReader fr = new FileReader(dosya);
            BufferedReader br = new BufferedReader(fr);
            
            String satir;
            System.out.printf("\n%-32s%-32s%-32s%-32s%-32s%-32s\n", "Kitap", "Yazar", "Sayfa", "Yayinevi", "Kitap No", "Fiyat");
            System.out.println("-------------------------------------------------------------------------------------------------------------"
                    + "---------------------------------------------------------");
            while ((satir = br.readLine()) != null) {
                String[] parts = satir.split("-");
                String kitapNo = parts[0];
                String kitapAdi = parts[1];
                String yazar = parts[2];
                String sayfaSayisi = parts[3];
                String yayinevi = parts[4];
                String fiyat = parts[5];
                System.out.printf("%-32s%-32s%-32s%-32s%-32s%-32s\n", kitapAdi, yazar, sayfaSayisi, yayinevi, kitapNo, fiyat);
                flag++;
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------"
                    + "---------------------------------------------------------");
            br.close();
            fr.close();
            if (flag == 0) {
                System.out.println("Sepetinizde mevcut kitap yoktur.");
                return true;
            }
        }catch(FileNotFoundException e){
            System.out.println("Hatanin Sebebi: " + e);
        }catch(IOException | ArrayIndexOutOfBoundsException e){
            System.out.println("Hatanin Sebebi: " + e);
        }
        return false;
    }
    public void sepetiListelemeSonrasiSecenekleri() {
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.print("\n1 -> Siparis Ver\n2 -> Sepetten Kitap Sil\n3 -> Ana Menuye Don\n\nSeciminiz(1-3):    ");
            int secim;
            try {
                secim = in.nextInt();
                switch(secim) {
                    case 1:  siparisVer();
                             return;
                    case 2:  if (kitapSil()) {
                                return;
                             }
                             sepetiListele();
                             return;
                    case 3:  return;
                    default:  System.out.println("Gecersiz secim Yapilmistir.");
                              break;
                }
            } catch (Exception e) {
                System.out.print("Gecersiz secim yapilmistir, seciminizi tekrar giriniz: ");
            }
        }
    }
    public void sepeteEkle(String kitapNo) {
        String dosyaIsmi = emailAl();
        String satir = "";
        boolean bool = false;
        try {
            FileReader fr = new FileReader("KitapListesi.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((satir = br.readLine()) != null) {
                String[] parts = satir.split("-");
                String kitapNumarasi = parts[0];
                if (kitapNumarasi.equals(kitapNo)) {
                    bool = true;
                    break;
                }
            }
            if (bool == false) {
                System.out.println("Girdiniz kitap numarasina ait bir kayit bulunamamistir.");
                return;
            }
            br.close();
            fr.close();
        }catch(FileNotFoundException e){
            System.out.println("Hatanin Sebebi: " + e);
        }catch(IOException | ArrayIndexOutOfBoundsException e){
            System.out.println("Hatanin Sebebi: " + e);
        }

        try {
            File dosya = new File(dosyaIsmi + "Sepeti.txt");
            if (!dosya.exists()) {
                dosya.createNewFile();
            }
            FileWriter fw = new FileWriter(dosya, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(satir + "\r\n");
                bw.close();
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Hatanin sebebi: " + e);
        }
        System.out.println("Kitap sepetinize eklenmistir.");
        
    }
    
    @Override
    public void siparisGecmisiGoruntule() {
        String dosyaIsmi = emailAl();
        try {    
            File dosya = new File(dosyaIsmi + "SiparisGecmisi.txt");
            if (!dosya.exists()) {
                System.out.println("Size ait bir siparis gecmisi bulunamadi.");
                return;
            }
            FileReader fw = new FileReader(dosya);
            BufferedReader bw = new BufferedReader(fw);
            String satir;
            String tarih;
            String ad;
            String soyad;
            String adres;
            String kargo;
            String krediKarti;
            String kitaplar;
            System.out.print("\nSiparis Gecmisi\n"
                    + "------------------------------------------------");
            while ((satir = bw.readLine()) != null) {
                String[] parts = satir.split("-");
                tarih = parts[0];
                ad = parts[1];
                soyad = parts[2];
                adres = parts[3];
                kargo = parts[4];
                krediKarti = parts[5];
                kitaplar = parts[6];
                String[] books = kitaplar.split("~");
                System.out.printf("\nTarih:   %-10s\nAd:  %-10s\nSoyad:   %-10s\nAdres:   %-10s"
                        + "\nKargo:    %-10s\nIslem Turu:   %-10s"
                        + "\nSatin Alinan Kitaplar:\n", tarih, ad, soyad, adres, kargo, krediKarti);
                for (int i = 0; i < books.length; i++){
                    System.out.println(books[i]);
                }
            }
            System.out.print("------------------------------------------------");
            
            bw.close();
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
    }
    @Override
    public boolean kitapSil() {
        String dosyaIsmi = emailAl();
        Scanner in = new Scanner(System.in);
        System.out.print("Silmek istediginiz kitabin numarasini giriniz: ");
        String id_girilen = in.nextLine();
        ArrayList<String> arrList = new ArrayList<String>();
        int flag = 0;
        try {
            File file = new File(dosyaIsmi + "Sepeti.txt");
            if (!file.exists()) {
                System.out.println("Sepetinizde silinebilecek bir kitap bulunmamaktadir.");
                return true;
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] chunk = line.split("-");
                if (id_girilen.equals(chunk[0])) {
                    flag++;
                    continue;
                }
                arrList.add(line);
            }
            br.close();
            fr.close();
            if (flag == 0) {
                System.out.println("Sepetinizde girdiginiz id numarasinda bir kitap bulunamamistir");
                return true;
            }
           
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
       
        try {
            File file = new File(dosyaIsmi + "Sepeti.txt");
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            for (int i = 0; i < arrList.size(); i++) {
                System.out.println(arrList.get(i));
                br.write(arrList.get(i) + "\r\n");
            }
            System.out.println("Kitap basariyla sepetinizden silinmistir.");
            br.close();
            fr.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
        
        return false;
    }
    @Override
    public void kitapSec() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {    
                System.out.print("\n1 -> Sepete Kitap Ekle\n"
                        + "2 -> Sepeti Goruntule\n"
                        + "3 -> Ana Menuye Don\n\n"
                        + "Seciminiz(1-3):    ");
                String secim = in.nextLine();
                int int_secim = Integer.parseInt(secim);
                String kitapNo;
                switch (int_secim) {
                    case 1:  System.out.print("Sepete eklenecek olan kitabin kitap numarasini giriniz: ");
                             kitapNo = in.nextLine();
                             sepeteEkle(kitapNo);
                             break;
                    case 2:  if (sepetiListele()) {
                                break;
                             }
                             sepetiListelemeSonrasiSecenekleri();
                             return;
                    case 3:  return;
                    default:  System.out.println("Gecersiz secim Yapilmistir.");
                              break;
                }
            } catch (Exception e) {
                System.out.println("Boyle bir secim yapilamaz!");
            }
        }
    }
    
    public void siparisVer() {    
        String kitapBilgileri = sepettekiKitapBilgileriniAl();
        String[] arr = kullaniciBilgileriniOlustur();
        odemeYap(arr, kitapBilgileri);
    }
    public void odemeYap(String[] arrKullaniciBilgileri, String kitapBilgileri) {
        String dosyaIsmi = emailAl();
        Yonetici admin = new Yonetici();
        Scanner in = new Scanner(System.in);
        System.out.print("\nSiparisinizi tamamlamak icin telefonunuza gelen 6 haneli kodu giriniz: ");
        String kod = in.nextLine();
        
        //Kullanicinin satin alacagi kitaplar "MisafirSepeti.txt" ten alinir.
        
        admin.kitapSatisIslemlerineYaz(arrKullaniciBilgileri, kitapBilgileri);
        kullanicininSiparisGecmisineYaz(arrKullaniciBilgileri, kitapBilgileri);
        
        
        System.out.print("Siparisiniz basariyla alinmistir.\n\n");
        System.out.print("1 -> Siparis Ozetini Goruntule\n2 -> Ana Menuye Don\n\nLutfen seciminizi yapiniz(1-2): ");
        int secim = 0;
        while (secim != 1 && secim != 2) {
            secim = in.nextInt();
            switch (secim) {
                case 1: System.out.printf("\nTarih:   %-10s\nAd:  %-10s\nSoyad:   %-10s\nAdres:   %-10s"
                                + "\nKargo:    %-10s\nIslem Turu:   Kredi Karti"
                                + "\nSatin Alinan Kitaplar:\n", arrKullaniciBilgileri[6],
                                arrKullaniciBilgileri[0], arrKullaniciBilgileri[1], arrKullaniciBilgileri[2],
                                arrKullaniciBilgileri[3]);
                        String[] books = kitapBilgileri.split("~");
                        for (int i = 0; i < books.length; i++){
                            System.out.println(books[i]);
                        }
                        System.out.print("Ana menuye donmek icin bir karakter giriniz: ");
                        in.next();
                        File dosya = new File(dosyaIsmi + "Sepeti.txt");
                        dosya.delete();
                        return;
                case 2: return;
                default: System.out.print("Boyle bir secim yapilamaz seciminizi tekrar giriniz: ");
                         break;
                         
            }
        }
    }
    @Override
    public void kullanicininSiparisGecmisineYaz(String[] arr, String kitaplar) {
        String dosyaIsmi = emailAl();
        try {    
            File dosya = new File(dosyaIsmi + "SiparisGecmisi.txt");
            if (!dosya.exists()) {
                dosya.createNewFile();
            }
            FileWriter fw = new FileWriter(dosya, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String satir = arr[6] + "-" + arr[0] + "-" + arr[1] + "-" + arr[2] + "-" + 
                    arr[3] + "-KrediKarti-" + kitaplar;
            bw.write(satir + "\r\n");
            
            bw.close();
            fw.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (IOException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
    }
    public String sepettekiKitapBilgileriniAl() {
        String dosyaIsmi = emailAl();
        String kitaplar = "";
        try {
            File dosya = new File(dosyaIsmi + "Sepeti.txt");
            FileReader fr = new FileReader(dosya);
            BufferedReader br = new BufferedReader(fr);
            String satir;
            
            while ((satir = br.readLine()) != null) {
                String[] parts = satir.split("-");
                kitaplar = kitaplar + parts[1] + "  " + parts[5] + "~";
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
        return kitaplar;
    }
}
