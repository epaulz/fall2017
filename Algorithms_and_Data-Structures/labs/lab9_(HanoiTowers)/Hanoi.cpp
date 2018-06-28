/*
  Eric Paulz (epaulz)
  CPSC 2121
  Lab 9
*/

#include "Stack.h"
#include "Disc.h"

#include <cstdlib>
using namespace std;

int main(int argc, char *argv[]){
  int n = atoi(argv[1]);
  Stack<Disc> stax[3];   // 3 towers
	
  // load discs on first tower
  Disc X;
  for(int i = n; i > 0; i--){
    X.setWidth(i);
    stax[0].push(X);
  }

  // move all discs to the third tower randomly
  int steps=0;
  srand (time(NULL));
  while(stax[2].getCount() < n){
    int from = rand() % 3;
    int to = rand() % 3;

    if(!stax[from].isEmpty() && from != to){
      if(stax[to].isEmpty() || stax[to].peek().compare(stax[from].peek())){
        cout << "Moving disc '" << stax[from].peek().getWidth() << "' from Pole ";
        cout << from+1 << " to Pole " << to+1 << endl;
        stax[to].push(stax[from].pop());
        steps++;
      }
    }
  }

  // print the result
  cout << "\nSteps taken: " << steps << endl;
  cout << "Pole 3 (top to bottom): ";
  for(int i = 0; i < n; i++){
    cout << stax[2].pop().getWidth() << " ";
  }
  cout << endl;

  return 0;
}
