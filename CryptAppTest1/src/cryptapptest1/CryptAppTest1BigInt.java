/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptapptest1;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author paddy
 */
public class CryptAppTest1BigInt {

    public static void main(String[] args) {
        //algorithm 1.1
        Scanner kb = new Scanner(System.in);
        //BigInteger a, b;
        BigInteger zero = new BigInteger("0");
        /*
        System.out.println("Enter a: ");
        a = kb.nextBigInteger();
        while (comparing(a, zero)) {
            System.out.println("Enter non negative value of a: ");
            a = kb.nextBigInteger();
        }

        System.out.println("enter b: ");
        b = kb.nextBigInteger();
        Boolean lessThanA = comparing(a, b);
        while (b.equals(zero) || lessThanA) {
            System.out.println("Enter non negative value of b that is greater than a: ");
            b = kb.nextBigInteger();
            lessThanA = comparing(a, b);
        }
        */
        BigInteger a = new BigInteger("4864");
        BigInteger b = new BigInteger("3458");
        /*
        if(b.compareTo(zero) > 0)                
        {
            System.out.println("bigger than zero");
            
        }else{
            System.out.println("less than");
        }
        b = b.negate();
*/
        System.out.println("b : " + b);
        
        BigInteger gcd1 = euclidianAlgorithm(a, b, zero);
        System.out.println("algo1 => gcd: " + gcd1 + "\n\n");
        
        String answer = extendedEuclidianAlgorithm(a, b, zero);
        System.out.println(answer);
        
        answer = calculate(a, b);
        System.out.println(answer);
    }

    public static Boolean comparing(BigInteger a, BigInteger b) {
        //System.out.println("compare: " + a.compareTo(b));
        return a.compareTo(b) <= 0;
    }

    public static BigInteger euclidianAlgorithm(BigInteger a, BigInteger b, BigInteger zero) {
        System.out.println("\nAlogrithm 1.1 Euclidean Alogrithm");
        BigInteger r;
        BigInteger[] divRem;
        
        while(!b.equals(zero))
        {
            divRem = a.divideAndRemainder(b);
            r = divRem[1];
            a = b;
            b = r;
        }
        return a;
    }
    
    public static String extendedEuclidianAlgorithm(BigInteger a, BigInteger b,BigInteger zero)
    {
        System.out.println("alogrithm 1.2: extended eudlidean alogrithm");
        BigInteger d, q, r, x, y, x1, y1, x2, y2;
        BigInteger one = new BigInteger("1");
        String answer = "";
        BigInteger[] divRem;
        
        if(b.equals(zero))
        {
            d = a;
            x = one;
            y = zero;
            return  "(" + d + "," + x + "," + y + ")";            
        }
        x2 = one;
        x1 = zero;
        y2 = zero;
        y1 = one;
        System.out.println("-----------------------------");
        while(b.compareTo(zero) > 0)
        {
            //q
            divRem = a.divideAndRemainder(b);
            q = divRem[0];
            System.out.println("q: " + q);
            System.out.println("div rem 0 :" + q);
            r = a.subtract(q.multiply(b));
            System.out.println("r: " + r);
            x = x2.subtract(q.multiply(r.multiply(x1)));
            System.out.println("x: " + x);
            y = y2.subtract(q.multiply(y1));
            System.out.println("y: " + y);
            a = b;
            System.out.println("a: " + a);
            b = r;
            System.out.println("b: " + b);
            x2 = x1;
            System.out.println("x2: " + x2);
            x1 = x;
            System.out.println("x1: " + x1);
            y2 = y1;
            System.out.println("y2: " + y2);
            y1 = y;
            System.out.println("y1: " + y1);
            System.out.println("--------------------------------------");
        }
        d = a;
        x = x2;
        y = y2;
        return  "(" + d + "," + x + "," + y + ")";
    }

    
    public static String calculate(BigInteger a, BigInteger b)
    {
        BigInteger x = BigInteger.ZERO;
        BigInteger lastx = BigInteger.ONE;
        BigInteger y = BigInteger.ONE;
        BigInteger lasty = BigInteger.ZERO;
        while (!b.equals(BigInteger.ZERO))
        {
            BigInteger[] quotientAndRemainder = a.divideAndRemainder(b);
            BigInteger quotient = quotientAndRemainder[0];

            BigInteger temp = a;
            a = b;
            b = quotientAndRemainder[1];

            temp = x;
            x = lastx.subtract(quotient.multiply(x));
            lastx = temp;

            temp = y;
            y = lasty.subtract(quotient.multiply(y));
            lasty = temp;
        }

        //BigIntEuclidean result = new BigIntEuclidean();
       // result.x = lastx;
        //result.y = lasty;
       // result.gcd = a;
        //return result;
        return "x: " + lastx + " y: " + lasty + " gcd: " + a;
    }
}
