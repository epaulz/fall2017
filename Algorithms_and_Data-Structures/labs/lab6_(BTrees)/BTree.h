// BTree.h wdg 2014
#ifndef BTREE_H
#define BTREE_H
#include <iostream>
using namespace std;
#include "BTreeNode.h"

class BTree
{
  private:
    BTreeNode *root;
    // destructors etc omitted

  public:
    BTree( );
    void insert(int item);
    void dump();
};
#endif
