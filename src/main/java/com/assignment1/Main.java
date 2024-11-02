package com.assignment1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        AffineCipher affineCipher = new AffineCipher(5,8);
//        String plain="The quick brown fox jumps over lazy dogs";
//        String cipher=affineCipher.encrypt(plain);
//        System.out.println(cipher);
//        String decipher=affineCipher.decrypt(cipher);
//        System.out.println(decipher);
        VigenereCipher vigenereCipher= new VigenereCipher();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plainText: ");
        String plainText = scanner.nextLine();
        System.out.print("Enter key: ");
        String key = scanner.nextLine();
        String cipher = vigenereCipher.encrypt(plainText,key);
        System.out.println(cipher);

    }
}