import java.io.*;
import java.util.*;


class Ogrenci implements Serializable {
    String ad, soyad, numara, bolum, cinsiyet, dogumYeri, telefon;
    int yas;
}

class OgrenciKayit {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Ogrenci> ogrenciler = new ArrayList<Ogrenci>();

    public static void main(String[] args) {
        boolean devam = true;

        while (devam) {
            System.out.println("\nMENU\n");
            System.out.println("1-Kayıt Ekle");
            System.out.println("2-Kayıtları Listele");
            System.out.println("3-Kayıt Ara");
            System.out.println("4-Kayıt Düzenle");
            System.out.println("5-Çıkış");

            System.out.print("\nLütfen seçim yapınız: ");
            int secim = scanner.nextInt();

            switch (secim) {
                case 1:
                    kayitEkle();
                    break;
                case 2:
                    kayitlariListele();
                    break;
                case 3:
                    kayitAra();
                    break;
                case 4:
                    kayitDuzenle();
                    break;
                case 5:
                    devam = false;
                    break;
                default:
                    System.out.println("Hatalı seçim!");
                    break;
            }
        }
    }

    static void kayitEkle() {
        Ogrenci ogrenci = new Ogrenci();

        System.out.print("\nAdı: ");
        ogrenci.ad = scanner.next();

        System.out.print("Soyadı: ");
        ogrenci.soyad = scanner.next();

        System.out.print("Numarası: ");
        ogrenci.numara = scanner.next();

        System.out.print("Bölümü: ");
        ogrenci.bolum = scanner.next();

        System.out.print("Cinsiyeti: ");
        ogrenci.cinsiyet = scanner.next();

        System.out.print("Doğum Yeri: ");
        ogrenci.dogumYeri = scanner.next();

        System.out.print("Yaşı: ");
        ogrenci.yas = scanner.nextInt();

        System.out.print("Telefon Numarası: ");
        ogrenci.telefon = scanner.next();

        ogrenciler.add(ogrenci);

        try {
            FileOutputStream fos = new FileOutputStream("ogrenciler.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ogrenciler);
            oos.close();
            fos.close();
            System.out.println("Öğrenci başarıyla kaydedildi.");
        } catch (IOException e) {
            System.out.println("Kaydedilemedi: " + e.getMessage());
        }
    }

    static void kayitlariListele() {
        try {
            FileInputStream fis = new FileInputStream("ogrenciler.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ogrenciler = (ArrayList<Ogrenci>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Okunamadı: " + e.getMessage());
        }

        if (ogrenciler.size() == 0) {
            System.out.println("Kayıt bulunamadı.");
        }
        else
        {
            System.out.println("\n--- Tüm Öğrenciler ---");
            for (Ogrenci ogrenci : ogrenciler) {
                System.out.println("Adı: " + ogrenci.ad);
                System.out.println("Soyadı: " + ogrenci.soyad);
                System.out.println("Numarası: " + ogrenci.numara);
                System.out.println("Bölümü: " + ogrenci.bolum);
                System.out.println("Cinsiyeti: " + ogrenci.cinsiyet);
                System.out.println("Doğum Yeri: " + ogrenci.dogumYeri);
                System.out.println("Yaşı: " + ogrenci.yas);
                System.out.println("Telefon Numarası: " + ogrenci.telefon + "\n");
            }


        }

    }

    static void kayitAra() {
        try {
            FileInputStream fis = new FileInputStream("ogrenciler.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Ogrenci> ogrenciler = (ArrayList<Ogrenci>) ois.readObject();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Aranacak herhangi bir değer giriniz: ");
            String kriter = scanner.next();

            boolean bulundu = false;

            for (Ogrenci ogrenci : ogrenciler) {
                if (ogrenci.ad.equalsIgnoreCase(kriter) || ogrenci.soyad.equalsIgnoreCase(kriter) || ogrenci.numara.equalsIgnoreCase(kriter) || ogrenci.bolum.equalsIgnoreCase(kriter)) {
                    bulundu = true;
                    System.out.println("Adı: " + ogrenci.ad);
                    System.out.println("Soyadı: " + ogrenci.soyad);
                    System.out.println("Numarası: " + ogrenci.numara);
                    System.out.println("Bölümü: " + ogrenci.bolum);
                    System.out.println("Cinsiyeti: " + ogrenci.cinsiyet);
                    System.out.println("Doğum Yeri: " + ogrenci.dogumYeri);
                    System.out.println("Yaşı: " + ogrenci.yas);
                    System.out.println("Telefon Numarası: " + ogrenci.telefon + "\n");
                }
            }

            if (!bulundu) {
                System.out.println("Öğrenci bulunamadı!");
            }

            ois.close();
            fis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    static void kayitDuzenle() {
        try {
            FileInputStream fis = new FileInputStream("ogrenciler.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Ogrenci> ogrenciler = (ArrayList<Ogrenci>) ois.readObject();
            ois.close();
            fis.close();

            Scanner scanner = new Scanner(System.in);

            System.out.print("Düzenlenecek öğrencinin numarasını girin: ");
            String numara = scanner.next();

            boolean bulundu = false;

            for (Ogrenci ogrenci : ogrenciler) {
                if (ogrenci.numara.equalsIgnoreCase(numara)) {
                    bulundu = true;
                    System.out.print("Adı : ");
                    String ad = scanner.next();
                    ogrenci.ad = ad.equals("") ? ogrenci.ad : ad;

                    System.out.print("Soyadı : ");
                    String soyad = scanner.next();
                    ogrenci.soyad = soyad.equals("") ? ogrenci.soyad : soyad;

                    System.out.print("Bölümü : ");
                    String bolum = scanner.next();
                    ogrenci.bolum = bolum.equals("") ? ogrenci.bolum : bolum;

                    System.out.print("Cinsiyeti : ");
                    String cinsiyet = scanner.next();
                    ogrenci.cinsiyet = cinsiyet.equals("") ? ogrenci.cinsiyet : cinsiyet;

                    System.out.print("Doğum Yeri : ");
                    String dogumYeri = scanner.next();
                    ogrenci.dogumYeri = dogumYeri.equals("") ? ogrenci.dogumYeri : dogumYeri;
                    System.out.print("Yaşı : ");
                    String yas = scanner.next();
                    ogrenci.yas = yas.equals("") ? ogrenci.yas : Integer.parseInt(yas);
                    System.out.print("Telefon Numarası : ");
                    String telefon = scanner.next();
                    ogrenci.telefon = telefon.equals("") ? ogrenci.telefon : telefon;

                    System.out.println("\nÖğrenci başarıyla düzenlendi:");
                    System.out.println("Adı: " + ogrenci.ad);
                    System.out.println("Soyadı: " + ogrenci.soyad);
                    System.out.println("Numarası: " + ogrenci.numara);
                    System.out.println("Bölümü: " + ogrenci.bolum);
                    System.out.println("Cinsiyeti: " + ogrenci.cinsiyet);
                    System.out.println("Doğum Yeri: " + ogrenci.dogumYeri);
                    System.out.println("Yaşı: " + ogrenci.yas);
                    System.out.println("Telefon Numarası: " + ogrenci.telefon + "\n");
                    break;
                }
            }

            if (!bulundu) {
                System.out.println("Öğrenci bulunamadı!");
            }

            FileOutputStream fos = new FileOutputStream("ogrenciler.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ogrenciler);
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

