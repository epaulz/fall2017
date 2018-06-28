/*
    Eric Paulz (epaulz)
    CPSC 2151
    Lab 7
 */

package cpsc2150.lab7;

/**
 * Correspondence: this = myQ[0...depth-1]
 * Correspondence: size = depth
 *
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class ArrayQueueImp implements IntegerQueueI {
    private Integer [] myQ;
    private int depth;

    /**
     * @ensures [myQ will be an array of size MAX_DEPTH]
     * @ensures depth = 0
     */
    ArrayQueueImp(){
        myQ = new Integer[MAX_DEPTH];
        depth = 0;
    }

    // add x to end of queue and increment depth
    public void add(Integer x){
        myQ[depth] = x;
        depth++;
    }

    // pop off first element
    public Integer pop(){
        Integer val = myQ[0];   // store first element

        // shift everything left by 1
        for(int i = 0; i < depth-1; i++){
            myQ[i] = myQ[i+1];
        }

        depth--;   // decrement depth temporarily
        add(val);  // add first element back to end of queue

        return val;
    }

    // return size of queue
    public int size(){
        return depth;
    }
}
