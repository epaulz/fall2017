/*
	Eric Paulz (epaulz)
	CPSC 2120
	Assignment 3: Phylogeny Tree
*/

#include <iostream>
using namespace std;
#include "FiloTree.h"

int main( )
{
  // empty tree
  string empty[] = {""};
  FiloTree A(empty, 0);
  cout << endl;
  A.dump();
  cout << endl;
  
  // tree with only one node
  string one[1] = {"root_only"};
  FiloTree B(one, 0);
  B.dump();

  string test[] = {"Art", "Business", "Chemistry", "Dentistry"};
  FiloTree F( test, 2);
  F.dump( );
  cout << endl;

  string testToo[] = {"Art", "Chemistry", "Business", "Dentistry"};
  FiloTree I( testToo, 2);
  cout << boolalpha << FiloTree::compare(F,I) << endl;  // should be false
  cout << endl;

  FiloTree G("ABCD.txt");
  G.dump( );
  cout << boolalpha << FiloTree::compare(F,G) << endl; // should be true
  cout << endl;

  FiloTree H("ABCDtoo.txt");
  H.dump( );
  cout << boolalpha << FiloTree::compare(F,H) << endl << endl; // should be false

  FiloTree X("first.txt");
  X.dump();
  FiloTree Y("second.txt");
  Y.dump();
  cout << boolalpha << FiloTree::compare(X,Y) << endl; // should be true

  cout << endl << boolalpha << FiloTree::compare(A,X) << endl;  // should be false
  cout << boolalpha << FiloTree::compare(B,X) << endl;  // should be false
  return 0;
}

/************* OUTPUT SHOULD BE LIKE
X
...X
......Art
......Business
...X
......Chemistry
......Dentistry

false

3
...1
......Chemistry
......Dentistry
...2
......Art
......Business
true

3
...Art
...2
......Business
......1
.........Chemistry
.........Dentistry
false
**********************************/
