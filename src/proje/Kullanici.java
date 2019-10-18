/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author muhem
 */
public abstract class Kullanici {
    public void kitaplariListele() {
        Scanner in = new Scanner(System.in);
        try {    
            File dosya = new File("KitapListesi.txt");
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
                
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------"
                    + "---------------------------------------------------------");
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
    }
    public abstract void secenekleriGoster(Kullanici temp);
    public abstract void kitapSec();
    public void kitapAra() {
        Scanner in = new Scanner(System.in);
        System.out.print("Aratmak istediginiz kitabin ismini giriniz(Buyuk harf kullanimina dikkat ediniz): ");
        String bookName = in.nextLine();
        
        try {    
            File dosya = new File("KitapListesi.txt");
            FileReader fr = new FileReader(dosya);
            BufferedReader br = new BufferedReader(fr);

            String satir;
            System.out.printf("\n%-32s%-32s%-32s%-32s%-32s%-32s\n", "Kitap", "Yazar", "Sayfa", "Yayinevi", "Kitap No", "Fiyat");
            System.out.println("-------------------------------------------------------------------------------------------------------------"
                    + "---------------------------------------------------------");
            while ((satir = br.readLine()) != null) {
                String[] parts = satir.split("-");
                if (bookName.equals(parts[1])) {
                    String kitapNo = parts[0];
                    String kitapAdi = parts[1];
                    String yazar = parts[2];
                    String sayfaSayisi = parts[3];
                    String yayinevi = parts[4];
                    String fiyat = parts[5];
                    System.out.printf("%-32s%-32s%-32s%-32s%-32s%-32s\n", kitapAdi, yazar, sayfaSayisi, yayinevi, kitapNo, fiyat);                
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------"
                    + "---------------------------------------------------------");
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Hatanin Sebebi: " + e);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Hatanin Sebebi: " + e);
        }
    }
}
