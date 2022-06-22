package com.madalore.accountmicroserviceML.utils;

import java.util.Random;

public class BankAccountUtils {

    private String accountNumber;

    public BankAccountUtils() {
    }

    public String generateIban(){
        accountNumber = "";
        String alfa = "QWERTYUIOPLKJHGFDSAZXCVBNM";
        String iban = "IT";
        Random random = new Random();

        for(int i = 2; i < 27; i++){
            if(i == 4){
                iban +=  alfa.charAt(random.nextInt(25));
                continue;
            }
            int randomNumber = random.nextInt(1,9);
            if(i >= 15){
                accountNumber += String.valueOf(randomNumber);
            }
            iban += String.valueOf(randomNumber);
        }

        return iban;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

}
