package atmproject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Options extends Account{
    DecimalFormat moneyFormat = new DecimalFormat("'£'###,##0.00");
    boolean flag=true;
    Scanner input = new Scanner(System.in);
    HashMap<Integer,Integer>data = new HashMap<>();

    /*
    ***Uygulamaların 3 özellikli yönü vardır.
1)User Interface / Arayüz ==> Kullanıcının etkileşinde olduğu her yer.
2)Data base / Veri bankası ==> Uygulamada girilen bilgilerin depolandığı yer.
3)API ==> Uygulamalar'ın birbileri arasındaki Ağ'ı kurar

     */
    public void login(){//User Interface:Kullanicinin etkilesimde oldugu yer : UI,Database: Veritabani deposu, API: uygulamalar arasi bagi kuran sistem

        System.out.println("TechProed ATM'e hosgeldiniz!");
        do{
            data.put(12345,1234);
            data.put(23456,2345);
            data.put(34567,3456);
            data.put(45678,4567);

            try{
                System.out.println("Hesap numaranizi giriniz: ");
                setAccountNumber(input.nextInt());
                System.out.println("Pin numaranizi giriniz");
                setPinNumber(input.nextInt());

            }catch(Exception e){
                System.out.println("Yanlis karakter girdiniz!Lutfen sadece rakam giriniz veya Q ya basip cikabilirsiniz");

                input.nextLine();//Isleminizin arasina bosluk koyar
                String exit = input.next();

                if(exit.equalsIgnoreCase("q")){
                    flag=false;
                }

            }

            int count =0;
            for(Map.Entry<Integer,Integer> w:data.entrySet()){
                if(w.getKey().equals(getAccountNumber()) && w.getValue().equals(getPinNumber())){

                    getAccountTypes();//Hesap islemlerine gidiniz.
                }else{
                    count++;
                }
            }

            if(count==data.size()){
                System.out.println("Yanlis hesap numarasi veya pin numarasi girdiniz.");
            }


        }while(flag);


    }




    //Kisi kendi hesabina girdiginde birden fazla islem yapabilmeli.
    //Checking Hesap islemleri(Vadesiz Hesap Islemleri )
    public void checkingOperations() {
        do {
           displayMessage();

            int option = input.nextInt();

            if(option==4){
                break;
            }
            switch(option) {
                case 1:
                    System.out.println("Checking Hesabinizda kalan bakiye: "+ moneyFormat.format(getCheckingBalance()));
                    break;
                    case 2:
                        getCheckingWithDraw();
                        break;
                        case 3:
                            getCheckingDeposit();
                            break;
                default:
                    System.out.println("Yanlis secenek !Lutfen 1,2,3 veya 4'u kullaniniz.");
            }

        }while(true);//Dongu dogru oldugu surece donmeli
        getAccountTypes();
    }
    //Saving account operations(Vadeli hesap Islemleri)
    public void savingOperations() {

        do {

            displayMessage();
            int option = input.nextInt();
            if(option==4){
                break;
            }

            switch(option){
                case 1:
                    System.out.println("Saving Hesabinizda kalan bakiye: "+ moneyFormat.format(getSavingBalance()));
                    break;
                case 2:getSavingWithDraw();
                break;
                case 3:
                    getSavingDeposit();
                    break;
                default:
                    System.out.println("Yanlis secenek !Lutfen 1,2,3 veya 4'u kullaniniz.");
            }

        }while(true);
        getAccountTypes();

    }
    //Ilgili hesabi seciniz!
        public void getAccountTypes(){

            System.out.println("Işlem yapmak istediginiz hesabi seciniz");
            System.out.println("1: Checking account");//Vadesiz hesap
            System.out.println("2: Saving account");//Vadeli hesap
            System.out.println("3 :Quit");

           int option = input.nextInt();
           switch(option){
               case 1:
                   System.out.println("Checking/Vadesiz hesabinizdasiniz");
                   checkingOperations();
                   break;
               case 2 :
                   System.out.println("Saving/Vadeli hesabinizdasiniz");
                   savingOperations();
                   break;
                   case 3 :
                       System.out.println("ATM makinemizi kullandiginiz icin tesekkur ederiz.");
                       flag = false;
                       break;
               default:
                   System.out.println("Yanlis secenek !Lutfen 1,2 veya 3'u kullaniniz.");

           }

        }

//Kisi icin secenekleri goruntule
    public void displayMessage(){
            System.out.println("Opsiyon Seciniz!");
            System.out.println("1 : View Balance");//Bakiyenizi kontrol ediniz,goruntuleyiniz
            System.out.println("2 : Withdraw");//Para cekme islemi
            System.out.println("3: Deposit ");//Para yatirma
            System.out.println("4:Exit");//Islemi sonlandir

        }
}
