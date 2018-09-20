/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task3;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Patrick McDonnell D00006968
 */
public class CryptoTask3 {

    public static void main(String args[]) {
        //initalise variables
        Scanner kb = new Scanner(System.in);
        int a = -1;
        int b = -1;
        int q;
        int x;
        Stack<Integer> stacker = new Stack<>();
        int ai;
        //inouts a, b a>=0, b>=2
        while (a < 0 || b < 2) {
            if (a < 0) {
                System.out.println("Please enter number for a(>= 0): ");
                a = kb.nextInt();
            }
            if (b < 2) {
                System.out.println("Please enter number for b(>= 2");
                b = kb.nextInt();
            }
        }

        int i = 0;
        x = a;
        q = Math.floorDiv(x, b);
        ai = x - q * b;
        stacker.add(ai);

        while (q > 0) {
            i++;
            x = q;
            q = Math.floorDiv(x, b);
            ai = x - q * b;
            stacker.add(ai);
        }

        while (!stacker.empty()) {
            System.out.print(stacker.pop());
        }
        System.out.println("");
    }

}
