package atmproject;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {
    private int accountNumber;//hesap numarasi
    private int pinNumber;//sifre

    private double checkingBalance;//Vadesiz hesap bakiyesi
    private double savingBalance;//Vadeli hesap bakiyesi

    DecimalFormat moneyFormat = new DecimalFormat("'£'###,##0.00");

    Scanner input = new Scanner(System.in);

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    //Para cekme=> (Checking hesabimiz icin)Para cekme isleminden sonra kalan parayi hesaplama
    private double calculateCheckingBalanceAfterWithdraw(double amount) {//double amount= Kullanicinin cekmek istedigi miktar

        checkingBalance = checkingBalance - amount;
        return checkingBalance;
    }

    //para yatirma=>(checking hesabimiz icin)Para yatirma isleminden sonra kalan bakiyeyi hesaplama
    private double calculateCheckingBalanceAfterDeposit(double amount) {
        checkingBalance = checkingBalance + amount;
        return checkingBalance;
    }

    //Para cekme => saving hesabindan para cekildikten sonra kalan bakiyeyi hesapla
    private double calculateSavingBalanceAfterWithdraw(double amount) {

        savingBalance = savingBalance - amount;
        return savingBalance;
    }

    //Para yatirma=> saving hesabina para yatirdiktan sonra kalan bakiyeyi hesaplayiniz
    private double calculateSavingBalanceAfterDeposit(double amount) {

        savingBalance = savingBalance + amount;
        return savingBalance;
    }

    //Musteri ile para cekmek icin etkilesime gecelim.Checking Hesap
    public void getCheckingWithDraw() {//checking hesabindan para cekme islemi.

       displayCurrentAmount(checkingBalance);
        System.out.println("Cekmek istediginiz miktari giriniz: ");
        double amount = input.nextDouble();

        if(amount<=0) {
            System.out.println("Sifir ve eksi rakamlar gecersizdir");
            getCheckingWithDraw();//recursive(methodu tekrardan cagirma) method.
        }else if(amount<=checkingBalance){
            calculateCheckingBalanceAfterWithdraw(amount);
            displayCurrentAmount(checkingBalance);
        }else{
            System.out.println("Yetersiz bakiye");
        }
    }

    //Para yatirma(checking):Musteri ile para yatirmak icin etkilesime gecelim.Checking Hesap
    public void getCheckingDeposit(){
      displayCurrentAmount(checkingBalance);
        System.out.println("Yatirmak istediginiz miktari giriniz: ");
        double amount = input.nextDouble();
        if(amount<=0) {
            System.out.println("Sifir ve eksi rakamlar gecersizdir");
            getCheckingDeposit();//recursive(methodu tekrardan cagirma) method.

        }else{
           calculateSavingBalanceAfterDeposit(amount);
            System.out.println();
          displayCurrentAmount(checkingBalance);
        }

    }

    //Musteri ile para cekmek icin etkilesime gecelim.Saving Hesap
    public void getSavingWithDraw() {//saving hesabindan para cekme islemi.

        displayCurrentAmount(savingBalance);
        System.out.println("Cekmek istediginiz miktari giriniz: ");
        double amount = input.nextDouble();
        if(amount<=0) {
            System.out.println("Sifir ve eksi rakamlar gecersizdir");
            getSavingWithDraw();//recursive(methodu tekrardan cagirma) method.
        }else if(amount<=savingBalance){
            calculateSavingBalanceAfterWithdraw(amount);
          displayCurrentAmount(savingBalance);//Vadeli hesabindaki bakiyeyi gosteriyor
        }else{
            System.out.println("Yetersiz bakiye");
        }


    }


    //Para yatirma(saving Account):Musteri ile para yatirmak icin etkilesime gecelim.Saving Hesap
    public void getSavingDeposit(){
        displayCurrentAmount(savingBalance);
        System.out.println("Yatirmak istediginiz miktari giriniz: ");
        double amount = input.nextDouble();
        if(amount<=0) {
            System.out.println("Sifir ve eksi rakamlar gecersizdir");
            getSavingDeposit();//recursive(methodu tekrardan cagirma) method.

        }else{
            calculateSavingBalanceAfterDeposit(amount);
            displayCurrentAmount(savingBalance);
        }

    }



        //Son bakiyeyi goster
    public void displayCurrentAmount(double balance){

        System.out.println("Hesabinizda bulunan bakiye: " + moneyFormat.format(balance));
    }










}
