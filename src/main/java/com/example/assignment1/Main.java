package com.example.assignment1;

public class Main {
    public static void main(String[] args) {
        AffineCipher affineCipher = new AffineCipher(5,8);
        String plain="The quick brown fox jumps over lazy dogs";
//        String cipher=affineCipher.encrypt(plain);
//        System.out.println(cipher);
//        String decipher=affineCipher.decrypt(cipher);
//        System.out.println(decipher);
        VigenereCipher vigenereCipher= new VigenereCipher();

        String cipher = vigenereCipher.encrypt("cryptographic","eng");
        System.out.println(cipher);
        System.out.println(vigenereCipher.decrypt(cipher,"eng"));

    }
}