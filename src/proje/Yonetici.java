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
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author muhem
 */
public class Yonetici extends Kullanici implements SilmeIslemi, SatislariListeleme {
    
    @Override
    public void secenekleriGoster(Kullanici temp) {
        String string_islem;
        int islem;
        
        Scanner in = new Scanner(System.in);
        while(true) {
            try {
                System.out.print("\n\n1 -> Kitaplari Listele\n"
                        + "2 -> Kitap ara\n"
                        + "3 -> Kullanicilari Listele\n"
                        + "4 -> Satis Islemlerinin Ozetini Listele\n"
                        + "5 -> Cikis\n\n"
                        + "Yapilacak Islemi Seciniz(1-5): ");
                string_islem = in.nextLine();
                islem = Integer.parseInt(string_islem);

                switch(islem){
                    case 1:  kitaplariListele();
                             kitapSec();
                             break;
                    case 2:  kitapAra();
                             kitapSec();
                             break;
                    case 3:  kullanicilariListele();
                             break;
                    case 4:  kitapSatisIslemleriniListele();
                             break;
                    case 5:  System.out.println("Hesaptan cikis yapiliyor...");
                             return;
                    default: System.out.println("Boyle bir secim yapilamaz!");
                             break;
                }
            } catch (Exception e) {
                System.out.println("Boyle bir secim yapilamaz!");
            }
        }
    }
    
    @Override
    public boolean kitapSil() {
        Scanner in = new Scanner(System.in);
        System.out.print("Silmek istediginiz kitabin numarasini giriniz: ");
        String id_girilen = in.nextLine();
        ArrayList<String> arrList = new ArrayList<String>();
        int flag = 0;
        try {
            File file = new File("KitapListesi.txt");
            if (!file.exists()) {
                System.out.println("Kitap listesinde silinebilecek bir kitap bulunmamaktadir.");
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
                System.out.println("Kitap listesinde girdiginiz id numarasinda bir kitap bulunmamaktadir.");
                return true;
            }
           
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
       
        try {
            File file = new File("KitapListesi.txt");
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            for (int i = 0; i < arrList.size(); i++) {
                br.write(arrList.get(i) + "\r\n");
            }
            System.out.println("Kitap basariyla kitap listesinden silinmistir.");
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
                System.out.print("\n1 -> Kitap Ekle\n"
                        + "2 -> Kitap Sil\n"
                        + "3 -> Kitap Guncelle\n"
                        + "4 -> Ana Menuye Don\n\n"
                        + "Seciminiz(1-3):    ");
                String secim = in.nextLine();
                int int_secim = Integer.parseInt(secim);
                switch (int_secim) {
                    case 1:  kitapEkle();
                             kitaplariListele();
                             break;
                    case 2:  if (kitapSil()) {
                                break;
                             }
                             kitaplariListele();
                             break;
                    case 3:  if (kitapGuncelle()) {
                                break;
                             }
                             kitaplariListele();
                             break;
                    case 4:  return;
                    default:  System.out.println("Gecersiz secim Yapilmistir.");
                              break;
                }
            } catch (Exception e) {
                System.out.println("Boyle bir secim yapilamaz!");
            }
        }
    }
    
    @Override
    public void kitapSatisIslemleriniListele() {
        try {    
            File dosya = new File("SatisIslemleri.txt");
            if (!dosya.exists()) {
                System.out.println("Sistemde mevcut bir kitap satis kaydi bulunmamaktadir.");
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
            String hesapNo;
            String cvc;
            String kitaplar;
            System.out.print("Siparis Gecmisi\n"
                    + "------------------------------------------------\n\n");
            while ((satir = bw.readLine()) != null) {
                String[] parts = satir.split("-");
                tarih = parts[0];
                ad = parts[1];
                soyad = parts[2];
                adres = parts[3];
                kargo = parts[4];
                hesapNo = parts[5];
                cvc = parts[6];
                kitaplar = parts[7];
                String[] books = kitaplar.split("~");
                System.out.printf("Tarih:   %-10s\nAd:  %-10s\nSoyad:   %-10s\nAdres:   %-10s"
                        + "\nKargo:    %-10s\nKredi karti hesap numarasi:   %-10s\nCvc:    %-10s"
                        + "\nSatin Alinan Kitaplar:\n", tarih, ad, soyad, adres, kargo, hesapNo, cvc);
                for (int i = 0; i < books.length; i++){
                    System.out.println(books[i]);
                }
                System.out.println();
            }
            System.out.print("------------------------------------------------");
            
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
    public void kullanicilariListele() {
        try {    
            File dosya = new File("UyeListesi.txt");
            FileReader fr = new FileReader(dosya);
            BufferedReader br = new BufferedReader(fr);

            String satir;
            System.out.printf("\n%-32s%-32s%-32s%-32s%-32s\n", "Kullanici Adi", "Sifre", "Ad", "Soyad", "Adres");
            System.out.println("------------------------------------------------------------------------------------"
                    + "------------------------------------------------------------------------");
            while ((satir = br.readLine()) != null) {
                String[] parts = satir.split("-");
                String email = parts[0];
                String sifre = "****";
                String ad = parts[2];
                String soyad = parts[3];
                String adres = parts[4];
                System.out.printf("%-32s%-32s%-32s%-32s%-32s\n", email, sifre, ad, soyad, adres);
            }
            System.out.println("------------------------------------------------------------------------------------"
                    + "------------------------------------------------------------------------");
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
    }
    public void kitapEkle() {
        Scanner in = new Scanner(System.in);
        String satir = "";
        String id = "";
        int int_id;
        try {
            File dosya = new File("KitapListesi.txt");
            FileReader fr = new FileReader(dosya);
            BufferedReader br = new BufferedReader(fr);
            while ((satir = br.readLine()) != null) {
                String[] parts = satir.split("-");
                int_id = Integer.parseInt(parts[0]);
                int_id++;
                id = Integer.toString(int_id);
            }
            br.close();
            fr.close();
        }catch(FileNotFoundException e){
            System.out.println("Hatanin Sebebi: " + e);
        }catch(IOException | ArrayIndexOutOfBoundsException e){
            System.out.println("Hatanin Sebebi: " + e);
        }
        
        String kitapAdi = "";
        String yazar = "";
        String sayfaSayisi = "";
        String yayinevi = "";
        String fiyat = "";
        while(true) {
            System.out.print("\n\t\tYENI KITAP EKLEME SAYFASI\n"
                    + "--------------------------------------------\n");
            System.out.print("Kitap Adi(Lutfen Turkce Karakter Kullanmayiniz.):    ");
            kitapAdi = in.nextLine();
            System.out.print("Yazar:    ");
            yazar = in.nextLine();
            System.out.print("Sayfa Sayisi:       ");
            sayfaSayisi = in.nextLine();
            System.out.print("Yayinevi:    ");
            yayinevi = in.nextLine();
            System.out.print("Fiyat(**.**):    ");
            fiyat = in.nextLine();
            if (kitapAdi.equals("") || yazar.equals("") || sayfaSayisi.equals("") || yayinevi.equals("") || fiyat.equals("")) {
                System.out.print("Bos alan birakilamaz! Bilgileri tekrar giriniz..\n\n");
                continue;
            }
            satir = id + "-" + kitapAdi + "-" + yazar + "-" + sayfaSayisi + "-" + yayinevi + "-" + fiyat;
            break;
        }
        
        try {
            File dosya = new File("KitapListesi.txt");
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
            System.out.println("Hatanin Sebebi: " + e);
        }
        System.out.println("Kitap basariyla kitap listesine eklenmistir.");
    }
    
    public boolean kitapGuncelle() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Guncellemek istediginiz kitabin numarasini giriniz: ");
        String id_girilen = in.nextLine();
        ArrayList<String> arrList = new ArrayList<String>();
        int flag = 0;
        try {
            File file = new File("KitapListesi.txt");
            if (!file.exists()) {
                System.out.println("Kitap listesinde silinebilecek bir kitap bulunmamaktadir.");
                return true;
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] parts = satir.split("-");
                if (id_girilen.equals(parts[0])) {
                    String id = parts[0];
                    String kitapAdi = "";
                    String yazar = "";
                    String sayfaSayisi = "";
                    String yayinevi = "";
                    String fiyat = "";
                    while(true) {
                        System.out.print("\n\t\tKITAP GUNCELLEME SAYFASI\n"
                                + "--------------------------------------------\n");
                        System.out.print("Kitap Adi(Lutfen Turkce Karakter Kullanmayiniz.):    ");
                        kitapAdi = in.nextLine();
                        System.out.print("Yazar:    ");
                        yazar = in.nextLine();
                        System.out.print("Sayfa Sayisi:       ");
                        sayfaSayisi = in.nextLine();
                        System.out.print("Yayinevi:    ");
                        yayinevi = in.nextLine();
                        System.out.print("Fiyat(**.**):    ");
                        fiyat = in.nextLine();
                        if (kitapAdi.equals("") || yazar.equals("") || sayfaSayisi.equals("") || yayinevi.equals("") || fiyat.equals("")) {
                            System.out.print("Bos alan birakilamaz! Bilgileri tekrar giriniz..\n\n");
                            continue;
                        }
                        satir = id + "-" + kitapAdi + "-" + yazar + "-" + sayfaSayisi + "-" + yayinevi + "-" + fiyat;
                        break;
                    }
                    arrList.add(satir);
                    flag++;
                    continue;
                }
                arrList.add(satir);
            }
            br.close();
            fr.close();
            if (flag == 0) {
                System.out.println("Kitap listesinde girdiginiz id numarasinda bir kitap bulunmamaktadir.");
                return true;
            }
           
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
       
        try {
            File file = new File("KitapListesi.txt");
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            for (int i = 0; i < arrList.size(); i++) {
                System.out.println(arrList.get(i));
                br.write(arrList.get(i) + "\r\n");
            }
            System.out.println("Kitap basariyla kitap listesinden silinmistir.");
            br.close();
            fr.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
        
        return false;
    }
    
    public void kitapSatisIslemlerineYaz(String[] arr, String kitaplar) {
        try {    
            File dosya = new File("SatisIslemleri.txt");
            if (!dosya.exists()) {
                dosya.createNewFile();
            }
            FileWriter fw = new FileWriter(dosya, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String satir = arr[6] + "-" + arr[0] + "-" + arr[1] + "-" + arr[2] + "-" + 
                    arr[3] + "-" + arr[4] + "-" + arr[5] + "-" + kitaplar;
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
}
