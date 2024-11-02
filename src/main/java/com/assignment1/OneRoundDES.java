package com.assignment1;


import java.util.Scanner;

public class OneRoundDES {

    static int[] IP ={
            58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,5,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7
    };
    static int[] PC1 = {
            57,49,41,33,25,17,9,1,58,50,42,34,26,18,10,2,59,51,43,35,27,19,11,3,60,52,44,36,63,55,47,39,31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,29,21,13,5,28,20,12,4
    };
    static int[] PC2 = {
            14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,51,45,33,48,44,49,49,56,34,53,46,42,50,36,29,32
    };
    static int[] E = {
            32,1,2,3,4,5,3,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,19,28,29,30,31,32,1
    };
    static int[] P = {
            16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,2,8,24,14,32,27,3,9,19,13,30,6,22,11,4,25
    };
    static int[][][] SBoxes = {
            {
                    {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
                    {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
                    {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
                    {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13},
            },
            {
                    {15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
                    {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
                    {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
                    {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}
            },
            {
                    {10, 0, 9,14,6,3,15,5,1,13,12,7,11,4,2,8},
                    {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
                    {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
                    {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12},
            },
            {
                    {7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
                    {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
                    {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
                    {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}
            },
            {
                    {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
                    {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
                    {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
                    {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3},
            },
            {
                    {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
                    {10,15,4,2,7,12,9,6,7,1,13,14,0,11,3,8},
                    {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
                    {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}
            },
            {
                    {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
                    {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
                    {1,4,11,13,12,3,7,14,10,15,6,8,0,6,9,2},
                    {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}
            },
            {
                    {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
                    {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
                    {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
                    {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}
            }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 16-hex digit key: ");
        String keyHex = scanner.nextLine();
        System.out.print("Enter 16-hex digit plaintext: ");
        String plaintextHex = scanner.nextLine();

        // Convert inputs to binary
        String keyBinary = hexToBinary(keyHex);
        String plaintextBinary = hexToBinary(plaintextHex);

        // Step 1: Derive K1, the first-round subkey
        String K1 = deriveK1(keyBinary);
        System.out.println("K1: " + K1);

        // Step 2: Derive L0 and R0
        String initialPermutation= permute(plaintextBinary,IP);
        String L0 = initialPermutation.substring(0, 32);
        String R0 = initialPermutation.substring(32, 64);
        System.out.println("L0: " + L0);
        System.out.println("R0: " + R0);

        // Step 3: Expand R0 to get E[R0]
        String ER0 = permute(R0, E);
        System.out.println("E[R0]: " + ER0);

        // Step 4: Calculate A = E[R0] ⊕ K1
        String A = xor(ER0, K1);
        System.out.println("A = E[R0] ⊕ K1: " + A);

        // Step 5: Group A into 6-bit blocks and substitute using S-boxes
        String B = sBoxSubstitution(A);
        System.out.println("B (after S-box substitution): " + B);

        // Step 6: Apply permutation P to get P(B)
        String PB = permute(B, P);
        System.out.println("P(B): " + PB);

        // Step 7: Calculate R1 = P(B) ⊕ L0
        String R1 = xor(PB, L0);
        System.out.println("R1 = P(B) ⊕ L0: " + R1);

        // The new left half for the next round would be R0
        String L1 = R0;
        System.out.println("L1: " + L1);

        // Step 9: Concatenate R1 and L1 to get the ciphertext for this one round
        String ciphertext = R1 + L1;
        System.out.println("Ciphertext (one round): " + binaryToHex(ciphertext));
    }

    static String hexToBinary(String hex) {
        StringBuilder binary = new StringBuilder();

        for (char hexChar : hex.toCharArray()) {

            String binarySegment = String.format("%4s", Integer.toBinaryString(Integer.parseInt(String.valueOf(hexChar), 16)))
                    .replace(' ', '0');
            binary.append(binarySegment);
        }

        return binary.toString();
    }

    static String binaryToHex(String binary) {
        StringBuilder hex = new StringBuilder();

        int paddingLength = 4 - (binary.length() % 4);
        if (paddingLength != 4) {
            binary = "0".repeat(paddingLength) + binary;
        }

        for (int i = 0; i < binary.length(); i += 4) {
            String binarySegment = binary.substring(i, i + 4);
            String hexChar = Integer.toHexString(Integer.parseInt(binarySegment, 2));
            hex.append(hexChar);
        }

        return hex.toString().toUpperCase();
    }

    static String permute(String input, int[] table) {
        StringBuilder output = new StringBuilder();
        for (int position : table) {
            output.append(input.charAt(position - 1));
        }
        return output.toString();
    }

    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    static String deriveK1(String key) {
        String permutedKey = permute(key, PC1);
        String C0 = permutedKey.substring(0, 28);
        String D0 = permutedKey.substring(28, 56);
        String C1 = leftShift(C0, 1);
        String D1 = leftShift(D0, 1);
        return permute(C1 + D1, PC2);
    }

    static String leftShift(String input, int shifts) {
        return input.substring(shifts) + input.substring(0, shifts);
    }

    static String sBoxSubstitution(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            String sixBits = input.substring(i * 6, (i + 1) * 6);
            int row = Integer.parseInt("" + sixBits.charAt(0) + sixBits.charAt(5), 2);
            int col = Integer.parseInt(sixBits.substring(1, 5), 2);
            int sBoxValue = SBoxes[i][row][col];
            String fourBits = String.format("%4s", Integer.toBinaryString(sBoxValue)).replace(' ', '0');
            output.append(fourBits);
        }
        return output.toString();
    }
}
