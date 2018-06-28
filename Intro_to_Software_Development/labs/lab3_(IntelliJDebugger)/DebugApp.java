/*
    Eric Paulz (epaulz)
    CPSC 2151-003
    Lab 9
 */

package cpsc2150.Lab3;
import java.util.Scanner;


public class DebugApp {

    public static void main(String[] args) {
	// write your code here
        int [] arr = new int[10];
        Scanner reader = new Scanner(System.in);
        for(int i = 0; i < 10; i++)
        {
            System.out.println("Give me a number that is NOT zero");
            String input = reader.nextLine();
            arr[i] = Integer.parseInt(input);
        }

        int a = 0;
        double b = 0, c = 0;

        if(arr.length == 10) {
            a = ArrayUtil.funct1(arr);
        }
        else
            System.out.println("funct1 could not be run due to invalid input.");

        if(arr.length > 0) {
            b = ArrayUtil.funct2(arr);
        }
        else
            System.out.println("funct2 could not be run due to invalid input.");

        boolean zero = false;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0)
                zero = true;
        }
        if(!zero) {
            c = ArrayUtil.funct3(arr);
        }
        else
            System.out.println("funct3 could not be run due to invalid input.");

        System.out.println("Mystery function 1 returns: " + a);
        System.out.println("Mystery function 2 returns: " + b);
        System.out.println("Mystery function 3 returns: " + c);

    }
}
