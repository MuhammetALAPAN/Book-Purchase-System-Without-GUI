/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author muhem
 */
public class KayitliKullanici extends Misafir_KayitliKullanici implements SiparisIslemleri, SilmeIslemi {
    private static String email;
    private static String ad;
    private static String soyad;
    private static String adres;
    
    public KayitliKullanici(String email, String ad, String soyad, String adres) {
        KayitliKullanici.email = email;
        KayitliKullanici.ad = ad;
        KayitliKullanici.soyad = soyad;
        KayitliKullanici.adres = adres;
    }
    
    @Override
    public String emailAl() {
        return email;
    }
    
    @Override
    public void secenekleriGoster(Kullanici temp) {
        KayitliKullanici temp1 = (KayitliKullanici)temp;
        KayitliKullanici.email = temp1.getEmail();
        KayitliKullanici.ad = temp1.getAd();
        KayitliKullanici.soyad = temp1.getSoyad();
        KayitliKullanici.adres = temp1.getAdres();
        
        String string_islem;
        int islem;
        
        Scanner in = new Scanner(System.in);
        while(true) {
            try {
                System.out.print("\n\n1 -> Kitaplari Listele\n"
                        + "2 -> Kitap ara\n"
                        + "3 -> Sepeti Goruntule\n"
                        + "4 -> Siparis Gecmisini Goruntule\n"
                        + "5 -> Hesaptan Cikis\n\n"
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
                    case 3:  if (sepetiListele()) {
                                break;
                             }
                             sepetiListelemeSonrasiSecenekleri();
                             break;
                    case 4:  siparisGecmisiGoruntule();
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
    public String[] kullaniciBilgileriniOlustur() {
        Scanner in = new Scanner(System.in);
        String[] arr = new String[7];
        System.out.printf("\nAdiniz:    %s", ad);
        System.out.printf("\nSoyadiniz:   %s", soyad);
        System.out.printf("\nAdresiniz:    %s", adres);
        System.out.print("\n\n1 -> MNG Kargo\n"
                + "2 -> Yurtici Kargo\n"
                + "3 -> Aras Kargo\n"
                + "Seciminizi giriniz(1-3): ");
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
    
    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        KayitliKullanici.email = email;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        KayitliKullanici.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        KayitliKullanici.soyad = soyad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        KayitliKullanici.adres = adres;
    }
    
    public void hesaptanCikis() {
        
    }
}
