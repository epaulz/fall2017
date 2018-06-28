/*
    Eric Paulz (epaulz)
    CPSC 2151-003
    Lab 8
 */

package cpsc2150.Lab8;

/**
 * Correspondence: this = myQ[0...depth-1]
 * Correspondence: size = depth
 *
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class QueueImp<T> extends AbstractQueueWPeek<T> {
    private T [] myQ;
    private int depth;

    /**
     * @ensures [myQ will be an array of size MAX_DEPTH]
     * @ensures depth = 0
     */
    QueueImp(){
        myQ = (T[]) new Object[MAX_DEPTH];
        depth = 0;
    }

    // add x to end of queue and increment depth
    public void add(T x){
        myQ[depth] = x;
        depth++;
    }

    // pop off first element
    public T pop(){
        T val = myQ[0];

        for(int i = 0; i < depth-1; i++){
            myQ[i] = myQ[i+1];
        }

        depth--;
        add(val);

        return val;
    }

    // return size of queue
    public int size(){
        return depth;
    }
}
