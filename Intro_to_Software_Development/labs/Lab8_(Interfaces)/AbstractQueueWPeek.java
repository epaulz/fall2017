/*
    Eric Paulz (epaulz)
    CPSC 2151-003
    Lab 8
 */

package cpsc2150.Lab8;

public abstract class AbstractQueueWPeek<T> implements IQueueWPeek<T> {

    // generic code for the peek function
    public T peek(){
        // store first element in the queue
        T peekVal = pop();

        // pop all remaining elements of queue to get it back in original order
        for(int i = 0; i < size()-1; i++){
            pop();
        }

        return peekVal;
    }
}
