/*
    Eric Paulz
    CPSC 2151-003
    Lab6
 */

package cpsc2150.lab6;

import java.util.*;

import static cpsc2150.lab6.myUtil.*;

public class ListApp {
    public static void main(String [] args){
        //create new list of Integers
        List<Integer> myList = new ArrayList<>();

        //prompt user and read in initial value
        System.out.println("Enter a list of numbers ('d' to stop): ");
        Scanner sc = new Scanner(System.in);
        String usr = sc.next();

        //continue reading in until condition is met
        while(!usr.equals("d")){
            myList.add(Integer.parseInt(usr));
            usr = sc.next();
        }

        //print the list
        System.out.println();
        for (Integer myInt : myList){
            System.out.print(myInt + " ");
        }
        System.out.println();

        //use myUtil functions to return max, min, odds, evens, and average
        System.out.println("Minimum: " + minimum(myList));
        System.out.println("Maximum: " + maximum(myList));
        System.out.println("Odd count: " + odds(myList));
        System.out.println("Even count: " + evens(myList));
        System.out.println("Average: " + average(myList));
    }
}
