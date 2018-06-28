/* Eric Paulz
 * CPSC 2121-004
 * Lab 1
 * Goddard
 */

#include <iostream>
using namespace std;

class NumberCount{
private:
  int *numCounts;
  int maximum, minimum;
public:
  NumberCount(int min, int max);
  void addElement(int num);
  void removeElement(int num);
  int getCount();
  void display();
};

// get number range and create new array with it
NumberCount::NumberCount(int min, int max){
  maximum = max;
  minimum = min;
  numCounts = new int[(max-min)+1];
}

void NumberCount::addElement(int num){
  numCounts[num-1]++;
  if(num < minimum || num > maximum){ // if number is out of range,
    cout << num << " out of range; "; // print message
  }
  if(numCounts[num-1] < 0){           // if number is already negative,
    cout << num << " not present; ";  // print message
  }
}

void NumberCount::removeElement(int num){
  numCounts[num-1]--;
  if(num < minimum || num > maximum){ // print same messages as ^^
    cout << num << " out of range; ";
  }
  if(numCounts[num-1] < 0){
    cout << num << " not present; ";
  }
}

int NumberCount::getCount(){
  int i;
  int count = 0;
  for(i = minimum-1; i < maximum; i++){ // loop through array and get sum
    if(numCounts[i] >= 0)               // of the values
      count += numCounts[i];
  }
  return count; // return sum
}

void NumberCount::display(){
  int i, j;
  cout << endl << endl;
  for(i = minimum-1; i < maximum; i++){ // loop through array and print
    cout << i+1 << ":";                 // out a histogram
    for(j = 0; j < numCounts[i]; j++){
      cout << "*";
    }
    cout << endl;
  }
}

int main(){
  // create object of NumberCount
  NumberCount N(1, 6);
  N.addElement(2); N.addElement(2); N.addElement(2); N.addElement(4);
  N.addElement(7); N.addElement(3); N.removeElement(1); N.removeElement(3);
  // print histogram based on input
  N.display();

  int total = N.getCount();
  // print total count
  cout << endl << "The total count is " << total << ".\n\n";

  return 0;
}
