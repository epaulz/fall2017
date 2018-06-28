/* Eric Paulz
 * CPSC 2121-004
 * Lab 2
 */

#include "Polynomial.h"

int main(){
  Polynomial A, B;
  int cof, powr;
  cout << "Enter first polynomial " << endl;
  do{
    cout << "Enter pair (0 0 to finish) ";
    cin >> cof >> powr;
    A.incrementBy(cof, powr);
  } while (!(cof==0 && powr==0));

  cout << "Enter second polynomial " << endl;
  do{
    cout << "Enter pair (0 0 to finish) ";
    cin >> cof >> powr;
    B.incrementBy(cof, powr);
  } while(!(cof==0 && powr==0));

  cout << endl;
  cout << "A is " << A << endl;
  cout << "B is " << B << endl;
  cout << (A==B ? "Equal" : "Not equal") << endl;

  return 0;
}
