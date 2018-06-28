/* Eric Paulz
 * CPSC 2121-004
 * Lab 2
 */

#ifndef POLYNOMIAL_H
#define POLYNOMIAL_H

#include "Pair.h"
#include <iostream>
#include <vector>
using namespace std;

class Polynomial{
private:
  int counter;
  vector<Pair> nums;
public:
  Polynomial();
  void incrementBy(int c, int p);
  bool operator==(const Polynomial& other) const;
  friend ostream &operator<<(ostream &out, const Polynomial& poly);
};

#endif
