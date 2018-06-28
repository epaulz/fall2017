/*
    Eric Paulz
    CPSC 2151-003
    Lab6
 */

package cpsc2150.lab6;

import java.util.*;

public class myUtil {
    /**
     * @param list list of Integers
     * @requires [list is a valid list of Integers] and !list.isEmpty()
     * @ensures [minimum of the list will be returned]
     * @return min
     */
    public static Integer minimum(List<Integer> list){
        Integer min = list.get(0);
        for(Integer myInt : list){
            if(myInt < min)
                min = myInt;
        }
        return min;
    }
    /**
     * @param list list of Integers
     * @requires [list is a valid list of Integers] and !list.isEmpty()
     * @ensures [maximum of the list will be returned]
     * @return max
     */
    public static Integer maximum(List<Integer> list){
        Integer max = list.get(0);
        for(Integer myInt : list){
            if(myInt > max)
                max = myInt;
        }
        return max;
    }
    /**
     * @param list list of Integers
     * @requires [list is a valid list of Integers] and !list.isEmpty()
     * @ensures [count of odd numbers will be returned]
     * @return oddCount
     */
    public static int odds(List<Integer> list){
        int oddCount = 0;
        for(Integer myInt : list){
            if(myInt % 2 != 0)
                oddCount++;
        }
        return oddCount;
    }
    /**
     * @param list list of Integers
     * @requires [list is a valid list of Integers] and !list.isEmpty()
     * @ensures [count of even numbers will be returned]
     * @return evenCount
     */
    public static int evens(List<Integer> list){
        int evenCount = 0;
        for(Integer myInt : list){
            if(myInt % 2 == 0)
                evenCount++;
        }
        return evenCount;
    }
    /**
     * @param list list of Integers
     * @requires [list is a valid list of Integers] and !list.isEmpty()
     * @ensures [average of the list will be returned]
     * @return (double)avg / list.size()
     */
    public static double average(List<Integer> list){
        Integer avg = 0;
        for(Integer myInt : list){
            avg += myInt;
        }
        return ((double)avg / list.size());
    }
}
