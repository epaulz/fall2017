#include <iostream>
using namespace std;

#include "stringQueueVec.h"
#include "radixSortVec.h"

int main()
{
    string inString;
    vector<string> list = { "bands", "candy", "dandy", "apple", "caned" };
    radixSort( list );
    stringQueueVec Q;
    for( string S : list )
         Q.enqueue( S );
    for(int i=0; i<5; i++)
         cout << Q.dequeue( ) << endl;
    cout << boolalpha << Q.isEmpty( ) << endl;
    return 0;
}
