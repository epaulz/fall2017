/*
  Eric Paulz
  CPSC 2121-004
  Lab 4
*/

#include "Blister.h"

#include <string>

Blister::Blister(std::string init){
  start = new Bode(init.at(0));
  Bode *cursor = start;

  for(size_t i = 1; i < init.length(); i++){
    cursor->next = new Bode(init.at(i));
    cursor = cursor->next;
  }
}

Blister::~Blister(){
  while(start != nullptr){
    Bode *hold = start->next;
    delete start;
    start = hold;
  }
}

std::ostream & operator<<(std::ostream & out, const Blister & BL){
  for(Bode *cursor = BL.start; cursor; cursor = cursor->next)
    out << cursor->base;
  return out;
}

int Blister::length() const {
  int len = 0;
  for(Bode *cursor = start; cursor; cursor = cursor->next)
    len++;

  return len;
}

bool Blister::isSubstitutionOf(const Blister & other) const{
  int count = 0;
  if(this->length() != other.length())
    return false;
  
  Bode *cursorA = other.start;
  Bode *cursorB = this->start;

  while(cursorA && cursorB){
    if(cursorA->base != cursorB->base)
      count++;
    cursorA = cursorA->next;
    cursorB = cursorB->next;
  }
  
  return (count == 1);
} 

bool Blister::isInsertionOf(const Blister & other) const{
  int different = 0, same = 0;
  if(this->length() != other.length()+1)
    return false;

  Bode *cursorA = other.start;
  Bode *cursorB = this->start;

  while(cursorA && cursorB){
    if(cursorB->base != cursorA->base){
      cursorB = cursorB->next;
      different++;
    }
    else{
      cursorA = cursorA->next;
      cursorB = cursorB->next;
      same++;
    }
  }

  return ((different + same == this->length()) && (different == 1));
}