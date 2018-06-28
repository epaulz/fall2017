/* Eric Paulz
 * CPSC 2121-004
 * Lab 2
 */

#ifndef PAIR_H
#define PAIR_H

class Pair{
private:
  int coeff, pwr;
public:
  Pair(int, int);
  void setCoeff(int c);
  int getCoeff() const;
  int getPower() const;
  bool operator!=(const Pair& other) const;
};

#endif
