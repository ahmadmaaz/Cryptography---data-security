package com.assignment1;

public class AffineCipher {

    private final Integer a;
    private final Integer b ;

    public AffineCipher() {
        this(17,20);
    }
    public AffineCipher(Integer a, Integer b) {
        this.a=a;
        this.b=b;
    }

    public String encrypt(String plain)
    {
        plain = plain.toUpperCase();
        StringBuilder cipher = new StringBuilder();
        for(char c : plain.toCharArray()){
            if (c != ' ')
            {
                cipher.append((char) ((((a * (c - 'A')) + b) % 26) + 'A'));
            } else {
                cipher.append(c);
            }
        }

        return cipher.toString();
    }

    public String decrypt(String cipher)
    {
        StringBuilder msg = new StringBuilder();
        Integer moduloInverse = findModuloInverse();

        for (char c : cipher.toCharArray()){
            /*Applying decryption formula a^-1 ( x - b ) mod m
            {here x is cipher[i] and m is 26} and added 'A'
            to bring it in range of ASCII alphabet[ 65-90 | A-Z ] */
            if (c != ' ')
            {
                msg.append((char) (((moduloInverse *
                        ((c + 'A' - b)) % 26)) + 'A'));
            }
            else //else simply append space character
            {
                msg.append(c);
            }
        }


        return msg.toString();
    }

    private Integer findModuloInverse(){
        int moduloInverse = 0;

        for (int i = 0; i < 26; i++)
        {
            int moduloRes = (a * i) % 26;

            if (moduloRes == 1)
            {
                moduloInverse = i;
            }
        }
        return moduloInverse;
    }

}
