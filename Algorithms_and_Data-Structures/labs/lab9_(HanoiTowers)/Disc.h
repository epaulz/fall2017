/*
  Eric Paulz (epaulz)
  CPSC 2121
  Lab 9
*/

#ifndef DISC_H
#define DISC_H

#include <iostream>

class Disc{
private:
  int width;
public:
  Disc();
  Disc(int w);
  void setWidth(int w);
  int getWidth();
  bool compare(Disc B);
};

#endif
