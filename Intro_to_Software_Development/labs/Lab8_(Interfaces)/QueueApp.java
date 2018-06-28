/*
    Eric Paulz (epaulz)
    CPSC 2151-003
    Lab 8
 */

package cpsc2150.Lab8;

public class QueueApp {
    public static void main(String args[]){
        IQueueWPeek<Integer> q1 = new QueueImp();

        q1.add(42);
        q1.add(17);
        q1.add(37);
        q1.add(36);
        q1.add(12);

        System.out.println("Peek 1: " + q1.peek());
        System.out.println("Peek 2: " + q1.peek());

        for(int i = 0; i < q1.size(); i++){
            System.out.print(q1.pop() + " ");
        }
        System.out.println("\n");


        IQueueWPeek<String> q2 = new QueueImp();

        q2.add("Look");
        q2.add("a");
        q2.add("Queue");
        q2.add("of");
        q2.add("Strings");

        System.out.println("Peek 1: " + q2.peek());
        System.out.println("Peek 2: " + q2.peek());

        for(int i = 0; i < q2.size(); i++){
            System.out.print(q2.pop() + " ");
        }
        System.out.println();
    }
}
