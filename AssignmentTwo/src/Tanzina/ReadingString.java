package Tanzina;

import java.io.*;

class BRReadLIne {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'Tanzina' to quit");
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("Tanzina"));
    }
}
