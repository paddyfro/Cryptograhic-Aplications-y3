package crypto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Patrick McDonnell & Graham
 */
public class Modulo {

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
                if (data.length == 3) {
                    try {
                        int a = Integer.parseInt(data[0]);
                        int b = Integer.parseInt(data[1]);
                        int c = Integer.parseInt(data[2]);
//                        int result = getModulo(a, b);
                        int result = getModPower(a, b, c);
                        System.out.println("");
                        if (result != -1) {
                            System.out.println("The result of " + a + "^" + b + " Mod " + c + " is " + result);
                        } else {
                            System.out.println("There was an error whilst calcuating, cannot mod by 0 or a negative exponent");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("An exception occurred while parsing numbers from line " + lineCount + " : " + line);
                    }
                } else {
                    System.out.println("Line " + lineCount + " was not formatted properly: \"" + line + "\"");
                }
            }
        } catch (FileNotFoundException fe) {
            System.out.println("That file was not found in the system, sorry.");
        }

    }

    public static int getModulo(int a, int b) {
        double ceil;
        int multi;
        double negative;

        if (a >= 0) {
            multi = a / b;
            return a - b * multi;
        } else {
            negative = -1 * a;
            ceil = Math.ceil(negative / b);
            return a + (int) ceil * b;
        }

    }

    public static int getModPower(int a, int b, int c) {

        String binaryNum;
        int result = -1;
        int number;
        int answer = 1;

        //convert power to a binary string
        binaryNum = Integer.toBinaryString(b);
        //create an array and cal a method that returns an array of the binary string broken up into its component elemnets
        int binArray[] = binaryNumArray(binaryNum);

        //create a new array to store each number whilst working through calcuations
        int[] numArray = new int[binArray.length];
        if (c > 0 && b > 0) {
            // getting the first number to work with
            //modding it and storing it in
//            number = a % c;
//get the mod of a mod c
            number = getModulo(a, c);
            //store it as number
            numArray[0] = number;
            //runs through length of binarystring array
            for (int i = 1; i < binArray.length; i++) {
                //using the fast modular exponientan formula to find the fnum for multiplying by powers
                number = getModulo((number * number), c);
                //store it in another array
                numArray[i] = number;
            }

            //get answer, multiply 1's in binArrray by answers in numArray keep running total
            for (int i = 0; i < binArray.length; i++) {
                //for every 1 in the binary array 
                if (binArray[i] == 1) {
                    //multiply the answer for the number reultd array by the answer creating the number you need to mod by
                    answer *= numArray[i];
                }
            }

            result = getModulo(answer, c);

        }
        //checks for debugging
//        System.out.println(": " + a + "^" + b + " Mod " + c);
//        System.out.println("To binaryString : " + Integer.toBinaryString(b));
//        System.out.println("----------------------");
//        System.out.println("numbers: ");
//        System.out.println("int base: " + a
//                + ", power: " + b
//                + ", mod: " + c + ".");
//        System.out.println("To binaryString : " + Integer.toBinaryString(b));
//        System.out.print("binary array: ");
//        printArray(binArray);
//        System.out.println("");
//        System.out.print("Number array: ");
//        printArray(numArray);
//        System.out.println("\nANSWER IS: " + answer);
//        System.out.println("Result: " + result);
//        System.out.println("------------------------\n");
//        System.out.println(": " + answer + " Mod " + c);
//
//        System.out.println("/n/n Final answer: " + result);

        return result;
    }

    /**
     * method to break up a binary string into its componenet elements and store
     * them in an array
     *
     * @param binNum string of a binary number
     * @return int array of binary number
     */
    public static int[] binaryNumArray(String binNum) {
        //create new int array of the same length as the string
        int binArray[] = new int[binNum.length()];
        //work through each element in the string
        for (int i = 0; i < binNum.length(); i++) {
            //take out the element(char) and get its numeric value
            int num = Character.getNumericValue(binNum.charAt(i));
            //store it in an array, storing at the end and working back as it will be easier to calcuate that way
            binArray[binArray.length - (1 + i)] = num;

        }
        //returns array
        return binArray;
    }

    /**
     * prints out elements in an int array
     *
     * @param array int array to be printed
     */
    public static void printArray(int[] array) {
//        System.out.println("Array of binary num");
        System.out.println("");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
    }

}
