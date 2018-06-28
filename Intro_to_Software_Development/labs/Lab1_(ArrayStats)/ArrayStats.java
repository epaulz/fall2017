/* Eric Paulz
 * CPSC 2151-003
 * Lab 1
 */
package cpsc2150.Lab1;

import java.util.Scanner;

public class ArrayStats {

    public static void main(String[] args) {
        int[] var2 = new int[10];
        System.out.println("Enter 10 integers, separated by space: ");
        Scanner var3 = new Scanner(System.in);

        for(int var1 = 0; var1 < var2.length; ++var1) {
            var2[var1] = var3.nextInt();
        }

        int var4 = evenNums(var2);
        int var5 = oddNums(var2);
        double var6 = average(var2);
        System.out.print("Your set of numbers contains ");
        System.out.print(var4 + " even numbers, " + var5);
        System.out.print(" odd numbers, and has an average of ");
        System.out.println(var6 + ".");
    }

    private static int evenNums(int[] var0) {
        int var2 = 0;

        for(int var1 = 0; var1 < var0.length; ++var1) {
            if(var0[var1] % 2 == 0) {
                ++var2;
            }
        }

        return var2;
    }

    private static int oddNums(int[] var0) {
        int var2 = 0;

        for(int var1 = 0; var1 < var0.length; ++var1) {
            if(var0[var1] % 2 != 0) {
                ++var2;
            }
        }

        return var2;
    }

    private static double average(int[] var0) {
        double var2 = 0.0D;

        for(int var1 = 0; var1 < var0.length; ++var1) {
            var2 += (double)var0[var1];
        }

        var2 /= (double)var0.length;
        return var2;
    }
}
