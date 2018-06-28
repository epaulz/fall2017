/*
	Eric Paulz (epaulz)
	CPSC 2121-004
	Lab 3
*/

#ifndef RECURSION_H
#define RECURSION_H

#include <iostream>
#include <fstream>
using namespace std;

class Recursion{
private:
  int grid[10][10];
public:
  Recursion(ifstream& inFile);
  void print();
  void printGroupWith(int row, int col);
};

#endif
