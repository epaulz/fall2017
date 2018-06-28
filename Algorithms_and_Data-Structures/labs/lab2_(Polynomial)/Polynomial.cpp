/* Eric Paulz
 * CPSC 2121-004
 * Lab 2
 */

#include "Polynomial.h"

// set counter to zero.  vector 'num' is still empty.
Polynomial::Polynomial() : counter(0) {}

void Polynomial::incrementBy(int c, int p){
  Pair nextPair(c, p);

  // check if the power 'p' already exists in the polynomial
  // if so, add the coefficient to existing pair
  for(unsigned int i = 0; i < nums.size(); i++){
    if(nums.at(i).getPower() == p){
      nums.at(i).setCoeff(nums.at(i).getCoeff() + c);
      return;
    }
  }

  // if power not found, create a new term then sort
  nums.push_back(nextPair);
  for(int i = nums.size() - 1; i > 0; i--){
    if(nums.at(i).getPower() >= nums.at(i-1).getPower()) // keep 'nums' sorted
      swap(nums.at(i), nums.at(i-1));
  }

  counter = nums.size() - 1;  // set counter equal to number of terms
                              // subtract by 1 to discount the 0 0 pair
}

bool Polynomial::operator==(const Polynomial& other) const{
  for(unsigned int i = 0; i < nums.size(); i++){
    if(nums.at(i) != other.nums.at(i))  // compare Pairs (i created a boolean
      return false;                     // (equality test inside Pair, too)
  }
  return true;                          // return true if equal
}

ostream &operator<<(ostream &out, const Polynomial& poly){
  // print first term
  out << poly.nums.at(0).getCoeff() << "x^" << poly.nums.at(0).getPower();

  // print the rest of the terms with '+' in front
  for(unsigned int i = 1; i < poly.nums.size(); i++){
    if(poly.nums.at(i).getCoeff() != 0){
      out << " + " << poly.nums.at(i).getCoeff() << "x^";
      out << poly.nums.at(i).getPower();
    }
    else
      out << "";
  }

  return out; // return ostream
}
