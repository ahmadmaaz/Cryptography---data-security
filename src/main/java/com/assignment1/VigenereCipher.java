package com.assignment1;

public class VigenereCipher {

    String decrypt(String cipher, String key){
        return crypt(cipher,key,false);
    }

    String encrypt(String plain, String key) {

        return crypt(plain,key,true);
    }

    private String crypt(String text, String key,Boolean encrypt) {
        /*
            Following DRY (DONT REPEAT YOURSELF) PRINCIPLE ;)
         */
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        int keyIndex=0;
        for(char c : text.toCharArray()) {
            if(c!= ' '){ //In case it was a space
                int shift = key.charAt(keyIndex++) - 'A';
                int base = c - 'A';
                int newChar;
                if (encrypt) {
                    newChar = (base + shift) % 26;
                } else {
                    newChar = (base - shift + 26) % 26;
                }

                result.append((char) (newChar + 'A'));
            }else{
                result.append(key);
            }
            if(keyIndex==key.length()){
                keyIndex=0;
            }
        }

        return result.toString();
    }

}
