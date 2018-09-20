package modulo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author grahamm
 */
public class modulo {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a file name to read from: ");
        String filename = keyboard.nextLine();

        try {
            Scanner myFile = new Scanner(new File(filename));
            int lineCount = 0;
            while (myFile.hasNextLine()) {
                lineCount++;
                String line = myFile.nextLine();
                String[] data = line.split(" ");
                if (data.length == 2) {
                    try {
                        int a = Integer.parseInt(data[0]);
                        int b = Integer.parseInt(data[1]);
                        int result = getModulo(a, b);
                        System.out.println("The result of " + a + " modulo " + b + " is " + result);
                    } catch (NumberFormatException e) {
                        System.out.println("An exception occurred while parsing numbers from line " + lineCount + " : " + line);
                    }
                }
                else
                {
                    System.out.println("Line " + lineCount + " was not formatted properly: \"" + line + "\"");
                }
            }
        } catch (FileNotFoundException fe) {
            System.out.println("That file was not found in the system, sorry.");
        }
        
        
    }
    
    public static int getModulo(int a, int b)
    {
        double ceil;
        int multi;
        double negative;
        
        if(a >=0)
        {
            multi = a/b;
            return a - b * multi;
        }
        else
        {
            negative = -1 * a;
            ceil = Math.ceil(negative / b);
            return a + (int)ceil * b;
        }
        
    }
    
//    public static int getModulo(int a, int b)
//    {
//        System.out.println("the ans: " + a%b);
//        System.out.println("MY output: ");
//        boolean check = false;
//        int count = 1;
//        int currentNum = b;
//        if(a / b == 0)
//        {
//            return 0;
//        }
//        else
//        {
//            while(!check)
//            {
//                currentNum *= count;
//                if(currentNum + b > a || currentNum + a == 0)
//                {
//                    check = true;
//                    return a - currentNum;
//                }
//                count++;
//            }
//            
//        }
//        
//        return -1;
//    }

}
