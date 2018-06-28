/*
  Eric Paulz (epaulz)
  CPSC 2121
  Lab 7
*/

#ifndef CHARRECORD_H
#define CHARRECORD_H

#include <string>

class CharRecord{
private:
  std::string letter;
  int count;

public:
  CharRecord();
  CharRecord(std::string s, int c);
  CharRecord(const CharRecord& x);
  std::string getCharString();
  int getCount();
  friend bool operator<(const CharRecord& a, const CharRecord& b);
  friend bool operator>(const CharRecord& a, const CharRecord& b);
};

#endif
