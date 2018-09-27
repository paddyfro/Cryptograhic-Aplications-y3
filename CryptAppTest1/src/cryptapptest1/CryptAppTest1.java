package cryptapptest1;

import java.util.Scanner;

/**
 *
 * @author Patrick McDonnell D00006968 Cryptographic Applications 20/09/2018
 */
public class CryptAppTest1 {

    public static void main(String[] args) {
        //algorithm 1.1
        Scanner kb = new Scanner(System.in);
        int a, b;
        System.out.println("Enter a: ");
        a = kb.nextInt();
        if (a <= 0) {
            System.out.println("Enter non negative value of a: ");
            a = kb.nextInt();
        }

        System.out.println("enter b: ");
        b = kb.nextInt();
        if (b <= 0 || a <= b) {
            System.out.println("Enter non negative value of b that is greater than a: ");
            b = kb.nextInt();
        }
        int gcd1 = algo1(a, b);

        System.out.println("algo1 => gcd: " + gcd1 + "\n\n");

        algo2(a, b);

    }

    public static int algo1(int a, int b) {

        System.out.println("\nAlogrithm 1.1 Euclidean Alogrithm");
        int r;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        //System.out.println("gcd: "  + a + "\n\n");
        return a;
    }

    public static void algo2(int a, int b) {
        //algorithm 1.2
        System.out.println("alogrithm 1.2: extended eudlidean alogrithm");
        int d, q, r, x, y, x1, y1, x2, y2;
        String answer = "";

        if (b == 0) {
            d = a;
            x = 1;
            y = 0;
            answer = "(" + d + "," + x + "," + y + ")";
        }
        x2 = 1;
        x1 = 0;
        y2 = 0;
        y1 = 1;
        while (b > 0) {

            q = Math.floorDiv(a, b);
            r = a - (q * b);
            x = x2 - (q * r * x1);
            y = y2 - (q * y1);
            a=b;
            b = r;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;
        }
        d = a;
        x = x2;
        y = y2;
        answer = "(" + d + "," + x + "," + y + ")";

        System.out.println("answer: " + answer);
    }

}
