/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author muhem
 */
public class Misafir extends Misafir_KayitliKullanici implements SiparisIslemleri, SilmeIslemi {
    private static String email = "Misafir";
    Scanner in = new Scanner(System.in);

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Misafir.email = email;
    }
    @Override
    public String[] kullaniciBilgileriniOlustur() {
        Scanner in = new Scanner(System.in);
        String[] arr = new String[7];
        System.out.print("\nAdinizi giriniz:      ");
        String ad = in.nextLine();
        System.out.print("Soyadinizi giriniz:   ");
        String soyad = in.nextLine();
        System.out.print("Adresini giriniz:     ");
        String adres = in.nextLine();
        System.out.print("\n1 -> MNG Kargo\n2 -> Yurtici Kargo\n3 -> Aras Kargo\nseciminizi giriniz(1-3): ");
        String string_secim;
        int secim = 0;
        String kargo = "";
        while(secim != 1 && secim != 2 && secim != 3) {
            try {    
                string_secim = in.nextLine();
                secim = Integer.parseInt(string_secim);
                switch(secim) {
                    case 1:
                        kargo = "MNG Kargo";
                        break;
                    case 2:
                        kargo = "Yurtici Kargo";
                        break;
                    case 3:
                        kargo = "Aras Kargo";
                        break;
                    default:
                        System.out.print("Boyle bir secim yapilamaz, seciminizi tekrar giriniz: ");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Boyle bir secim yapilamaz, seciminizi tekrar giriniz: ");
            }
        }
        
        System.out.print("\nLutfen Kredi Kartinizin 16 haneli hesap numarasini giriniz: ");
        String hesapNo = in.nextLine();
        System.out.print("Lutfen kredi kartinizin cvc numarasini giriniz: ");
        String cvc = in.nextLine();
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        arr[0] = ad;
        arr[1] = soyad;
        arr[2] = adres;
        arr[3] = kargo;
        arr[4] = hesapNo;
        arr[5] = cvc;
        arr[6] = date;
        System.out.println("Girdiginiz bilgiler dogrulanmistir.");
        return arr;
    }
    
    @Override
    public void secenekleriGoster(Kullanici temp) {
        String string_islem;
        int islem;
        
        Scanner in = new Scanner(System.in);
        while(true) {
            try {
                System.out.print("\n\n1 -> Yeni Uyelik\n"
                        + "2 -> Kullanici-Admin Girisi\n"
                        + "3 -> Kitaplari Listele\n"
                        + "4 -> Kitap ara\n"
                        + "5 -> Sepeti Goruntule\n"
                        + "6 -> Siparis Gecmisini Goruntule\n"
                        + "7 -> Cikis\n\n"
                        + "Yapilacak Islemi Seciniz(1-7): ");
                string_islem = in.nextLine();
                islem = Integer.parseInt(string_islem);
                switch(islem){
                    case 1:  temp = yeniKayit();
                             Polimorfizm.secenekler(temp);
                             temp = new Misafir();
                             break;

                    case 2:  temp = kullaniciGirisi();
                             Polimorfizm.secenekler(temp);
                             temp = new Misafir();
                             break;
                    case 3:  kitaplariListele();
                             kitapSec();
                             break;
                    case 4:  kitapAra();
                             kitapSec();
                             break;
                    case 5:  if (sepetiListele()) {
                                break;
                             }
                             sepetiListelemeSonrasiSecenekleri();
                             break;
                    case 6:  siparisGecmisiGoruntule();
                             break;
                    case 7:  System.out.println("Programdan cikis yapiliyor...");
                             File file1 = new File("MisafirSepeti.txt");
                             file1.delete();
                             File file2 = new File("MisafirSiparisGecmisi.txt");
                             file2.delete();
                             System.exit(0);
                             break; 
                    default: System.out.println("Boyle bir secim yapilamaz!");
                             break;
                }
            } catch (Exception e) {
                System.out.println("Boyle bir secim yapilamaz!");
            }
                
        }
    }

    public KayitliKullanici yeniKayit() {
        String satir = "";
        String email = "";
        String ad = "";
        String soyad = "";
        String adres = "";
        while(true) {
            System.out.print("\n\t\tYENI KAYIT\n"
                    + "--------------------------------------------\n");
            // AYNI EMAILDE BIR KISI OLABILECEGI ICIN DOSYA ICERISINDEKI KAYITLI DIGER KISILERLE KARSILASTIRMA YAPMA GEREGI DUYGUMADIM.
            System.out.print("Email:    ");
            email = in.nextLine();
            System.out.print("Sifre:    ");
            String sifre = in.nextLine();
            System.out.print("Ad:       ");
            ad = in.nextLine();
            System.out.print("Soyad:    ");
            soyad = in.nextLine();
            System.out.print("Adres:    ");
            adres = in.nextLine();
            if (email.equals("") || sifre.equals("") || ad.equals("") || soyad.equals("") || adres.equals("")) {
                System.out.print("Bos alan birakilamaz! Bilgileri tekrar giriniz..\n\n");
                continue;
            }
            satir = email + "-" + sifre + "-" + ad + "-" + soyad + "-" + adres;
            break;
        }
        
        try {
            File dosya = new File("UyeListesi.txt");
            if (!dosya.exists()) {
                dosya.createNewFile();
            }
            FileWriter fw = new FileWriter(dosya, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(satir + "\r\n");
                bw.close();
                fw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Misafir.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Kayit Basarili! Giris yapiliyor...");
        
        KayitliKullanici temp = new KayitliKullanici(email, ad, soyad, adres);
        return temp;
    }
    public Kullanici kullaniciGirisi() {
        Scanner in = new Scanner(System.in);
        System.out.print("\n\t\tKullanici Girisi\n"
                    + "--------------------------------------------\n");
        while(true) {
            System.out.println("Admin olarak girmek icin email ve sifreyi admin olarak giriniz.");
            System.out.print("Email:     ");
            String email_girilen = in.nextLine();
            System.out.print("Sifre:     ");
            String sifre = in.nextLine();
            if (email_girilen.equals("") || sifre.equals("")) {
                System.out.print("Bos alan birakilamaz! Bilgileri tekrar giriniz..\n\n");
                continue;
            }
            try {
            File dosya = new File("UyeListesi.txt");
            if (!dosya.exists()) {
                System.out.println("Kayitli kullanici bulunmamaktadir");
                Misafir temp = new Misafir();
                return temp;
            }
            FileReader fr = new FileReader(dosya);
            BufferedReader br = new BufferedReader(fr);
            
            String satir;
            String username = "";
            String password;
            String name = "";
            String surname = "";
            String adress = "";
            int sonuc = 0;
            while ((satir = br.readLine()) != null) {
		String[] parts = satir.split("-");
                username = parts[0];
                password = parts[1];
                name = parts[2];
                surname = parts[3];
                adress = parts[4];
                if (email_girilen.equals(username) && sifre.equals(password)) {
                    sonuc = 1;
                    break;
                }
                if (email_girilen.equals("admin") && sifre.equals("admin")) {
                    sonuc = 2;
                    break;
                }
            }
            if (sonuc == 1) {
                System.out.println("Giris Basarili!");
                KayitliKullanici temp = new KayitliKullanici(username, name, surname, adress);
                return temp;
            } else if (sonuc == 2) {
                System.out.println("---___---   !!!   ADMIN GIRISI YAPILMISTIR   !!!   ---___---");
                Yonetici temp = new Yonetici();
                return temp;
            } else {
                System.out.println("Kayit Bulunamadi! Bilgileri kontrol edip tekrar deneyiniz...");
            }
            br.close();
            fr.close();
            }catch(FileNotFoundException e) {
                System.out.println("Dosya Bulunamadi!");
            }catch(IOException e) {
                System.out.println("Bir hata olustu: " + e);
            }
            
                
            
            Misafir temp = new Misafir();
            return temp;
        }
    }
}
