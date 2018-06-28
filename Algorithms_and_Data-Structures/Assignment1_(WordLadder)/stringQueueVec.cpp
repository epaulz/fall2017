/*
  Eric Paulz
  CPSC 2120-
  Assignment 1 - Word Ladders
  */

#include "stringQueueVec.h"

stringQueueVec::stringQueueVec(){}
stringQueueVec::~stringQueueVec(){} //did not 'new' anything

bool stringQueueVec::isEmpty(){
  return (list.empty());
}

//adds an element to back of the queue
void stringQueueVec::enqueue(string elem){
  list.push_back(elem);
}

//removes an element from front of queue
string stringQueueVec::dequeue(){
  string temp = list.at(0);
  list.erase(list.begin()+0);
  return temp;
}
