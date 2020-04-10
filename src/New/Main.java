package New ;

import  java.io.*;
import  java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        System.out.println("insert in command line  E for Encryption or D for Decryption ");
        //System.out.println(args[0] );
        while(args.length!=2){}


        if (args[0].equals("e") || args[0].equals("E"))
        {
            Encrption E = new Encrption();
            File myObj = new File(args[1]);
            E.EncryptionFunc(myObj);

        }//end if enc
        else if (args[0].equals("d") || args[0].equals("D")) {
            Encrption E = new Encrption();
            File myObj = new File(args[1]);
            E.DecryptionFunc(myObj);

        }// end if for dec
        else System.out.println("please insert E for Encryption or D for Decryption");


    }
}
