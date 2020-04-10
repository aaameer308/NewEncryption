package New;
import  java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.lang.String;

public class Encrption
{


    public void EncryptionFunc(File src) throws IOException
    {
        InputStream is = null;
        OutputStream os = null;
        String destfile , destfile_key ; //to save the new files names
        byte[] buffer = new byte[1024];
        String doing="encrypted";
        int length=src.getPath().length();  // get the length of the PATH
        String KEYFILE ;;
        int key=0,fileforkey=0,fileforother=1;


        int rand_int1;
        Random rand = new Random();
        rand_int1 = rand.nextInt(6);
        System.out.println("the key is "+rand_int1);


        try {
            is = new FileInputStream(src);

            /// create the decrypted file and the key file
            destfile=createnameforthefile(src.getAbsolutePath().toString(),doing,fileforother);
            File dest = new File(destfile);///////////
            destfile_key=createnameforthefile(src.getAbsolutePath().toString(),doing,fileforkey);
            File dest_key = new File(destfile_key);///////////
            /*  if (dest_key.createNewFile())
            {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

            if (dest.createNewFile())
            {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }


             */

            //encrypting and  writing to the encryption  file
            os = new FileOutputStream(dest);
            while ((length = is.read(buffer)) > 0)
            {
                encryptBytes(buffer,rand_int1);
                os.write(buffer, 0, length);
            }

            // writing to the key file
            PrintWriter pw =new PrintWriter(dest_key);
            pw.println(rand_int1);
            pw.close();


        }finally
        {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }

        }

    }


    public void DecryptionFunc(File src) throws IOException {

        InputStream is = null;
        OutputStream os = null; // FOR THE DECRYPTION FILE
        String destfile ; //help to write the dest name file  (we save the new filepath in TEMP
        String destfile_key ;
        byte[] buffer = new byte[1024];
        String doing="decrypted";
        int length=src.getPath().length();  // get the length of the PATH
        int key=0,fileforkey=0,fileforother=1;

        //////////////////get the key from  the user
        System.out.print("insert  key for decryption :  ");
        Scanner userinput= new Scanner(System.in);
        String intnum = userinput.nextLine();  //get the key from the user
        key=Integer.parseInt(intnum);  //

        try {
            is = new FileInputStream(src);   ////obtains input bytes from a file in a file system

            /// create the decrypted file and the key file
            destfile=createnameforthefile(src.getAbsolutePath().toString(),doing,fileforother);  // here we get the name
            File dest = new File(destfile);   // creating the file
            destfile_key=createnameforthefile(src.getAbsolutePath().toString(),doing,fileforkey);
            File dest_key = new File(destfile_key);

            //decrypting and  writing to the decryption  file
            os = new FileOutputStream(dest);
            while ((length = is.read(buffer)) > 0)
            {
                decryptBytes(buffer,key);
                os.write(buffer, 0, length);
            }

            // writing to the key file
            PrintWriter pw =new PrintWriter(dest_key);
            pw.println(key);
            pw.close();


        }finally
        {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }

        }

    }

    public  String createnameforthefile(String sourcename, String activity,int typefile)  // the activity could be encryption or decryption
    {
        String destfile; //help to write the dest name file
        String ForFuncBetween = sourcename;
        int length = sourcename.length();
        ForFuncBetween = ForFuncBetween.substring(length - 1);// we get the last character  from the path name
        String dest1,dest2;
        if (typefile == 1)// file is not for key
        {
            destfile = between(sourcename, ".", ForFuncBetween);  // here we get the last world (txt or jbg ..) }
            dest1 = "." + destfile;  //  here we add . before the name
            dest2 = "_" + activity + "." + destfile;  // adding _encrypted to the name of the new file
        }
        else {
            destfile = between(sourcename, ".", ForFuncBetween);  // here we get the last world (txt or jbg ..) }
            dest1 = "." + destfile;  //  here we add . before the name
            dest2 = "_key.txt"  ;  // adding _encrypted to the name of the new file
        }

        String TEMP = sourcename;
        TEMP = TEMP.replaceAll(dest1, dest2);  // change the name rom file.txt to file_encrypted.txt
        if (typefile == 1)
            System.out.println(activity + "file  wil be in   " + TEMP);
        else
            System.out.println( "key file  wil be in   " + TEMP);


        return  TEMP;
    }


    private void encryptBytes(byte[] data , int key) // Encryption Algorithm
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = (byte) ( data[i] +  key);
        }
    }

    private void decryptBytes(byte[] data, int key) // decryption Algorithm
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = (byte) ( data[i] -  key);
        }
    }


    public String between(String value, String a, String b)  // help in creating the new name  // Return a substring between the two strings.
    {

        int posA = value.lastIndexOf(a);
        if (posA == -1) {
            return "";
        }
        int posB = value.lastIndexOf(b);
        if (posB == -1) {
            return "";

        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= posB) {
            return "";
        }
        return value.substring(adjustedPosA, posB+1);
    }
}



