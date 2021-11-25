package Tanzina;

import java.io.*;

class ShowFIleOne {
    public static void main(String[] args) {
        int i;
        FileInputStream fin = null;
        if (args.length != 1) {
            System.out.println("Usage: ShowFile filename");
            return;
        }

        try {
            fin = new FileInputStream(args[0]);
            do {
                i = fin.read();
                if (i != -1) ;
                System.out.print((char) i);

            } while (i != -1);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found. ");
        } catch (IOException e) {
            System.out.println("An I/O Error Occurred");
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException e) {
                System.out.println("Error Closing File");
            }
        }

    }
}
