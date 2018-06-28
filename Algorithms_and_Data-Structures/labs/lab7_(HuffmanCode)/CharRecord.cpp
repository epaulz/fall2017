/*
  Eric Paulz (epaulz)
  CPSC 2121
  Lab 7
*/

#include "CharRecord.h"

// default constructor
CharRecord::CharRecord() : letter(" "), count(0){}

// overloaded constructor
CharRecord::CharRecord(std::string s, int c) : letter(s), count(c){}

// copy constructor
CharRecord::CharRecord(const CharRecord& x) : letter(x.letter), count(x.count){}

// string getter
std::string CharRecord::getCharString(){
  return letter;
}

// count getter
int CharRecord::getCount(){
  return count;
}

// overload <
bool operator<(const CharRecord& a, const CharRecord& b){
  return (a.count < b.count);
}

// overload >
bool operator>(const CharRecord& a, const CharRecord& b){
  return (a.count > b.count);
}
