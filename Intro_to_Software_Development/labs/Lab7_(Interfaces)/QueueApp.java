/*
    Eric Paulz (epaulz)
    CPSC 2151
    Lab 7
 */

package cpsc2150.lab7;

public class QueueApp {
    public static void main(String [] args){
        IntegerQueueI q = new ArrayQueueImp();
        Integer x = 42;

        q.add(x);
        x = 17;
        q.add(x);
        x = 37;
        q.add(x);
        x = 36;
        q.add(x);
        x = 12;
        q.add(x);

        // loop through queue and pop front off each time
        for(int i = 0; i < q.size(); i++){
            System.out.print(q.pop() + " ");
        }
        System.out.println();

    }
}
