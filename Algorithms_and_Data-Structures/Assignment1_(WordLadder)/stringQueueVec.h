/*
  Eric Paulz
  CPSC 2120-
  Assignment 1 - Word Ladders
  */

#ifndef STRING_QUEUE_VEC_H
#define STRING_QUEUE_VEC_H

#include <string>
#include <vector>
using namespace std;

class stringQueueVec
{
private:
  vector<string> list;
public:
  stringQueueVec();
  ~stringQueueVec();
  bool isEmpty();
  void enqueue(string elem);
  string dequeue();
};

#endif
