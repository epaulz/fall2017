/*
    Eric Paulz (epaulz)
    CPSC 2151-003
    Lab 9
 */

package cpsc2150.Lab3;

public class ArrayUtil {
    /**
     * @param arr an array of integers
     * @return sum
     * @requires arr.length > 0
     * @ensures sum = sum of the array
     */
    public static int funct1(int[] arr ) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += arr[i];

        }
        return sum;
    }

    /**
     * @param arr an array of integers
     * @return num
     * @requires arr.length > 0
     * @ensures num = median of the array
     */
    public static double funct2(int[] arr)
    {
        int sum = funct1(arr);
        double num =  sum / arr.length;
        return num;
    }

    /**
     * @param arr an array of integers
     * @return x
     * @requires arr.length > 0 and [arr does not contain a zero]
     * @ensures x = arr[0]/arr[n-1] + arr[1]/arr[n-2] . . .
     */
    public static double funct3(int[] arr)
    {

        double x = 0.0;
        for(int i=0; i < arr.length; i++)  // changed <= to < bc 10 is out of the array range
        {
            x += (double)arr[i]/arr[(arr.length-1) - i]; //arr.length-0 would be 10 so added -1
        }
        return x;
    }

}
