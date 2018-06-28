/* Eric Paulz
 * CPSC 2121-004
 * Lab 2
 */

#include "Pair.h"

Pair::Pair(int c, int p) : coeff(c), pwr(p) {}

void Pair::setCoeff(int c){
  coeff = c;
}

int Pair::getCoeff() const{
  return coeff;
}

int Pair::getPower() const{
  return pwr;
}

bool Pair::operator!=(const Pair& other) const{
  // return true if the Pairs are not equal
  return (this->coeff != other.coeff || this->pwr != other.pwr);

}
