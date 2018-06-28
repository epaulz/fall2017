/*
  Eric Paulz (epaulz)
  CPSC 2121
  Lab 9
*/

#include "Disc.h"

Disc::Disc() : width(0) {}
Disc::Disc(int w) : width(w) {}

void Disc::setWidth(int w){
  width = w;
}

int Disc::getWidth(){
  return width;
}

bool Disc::compare(Disc B){
  return (this->width > B.width);
}
